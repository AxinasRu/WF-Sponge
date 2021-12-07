package wfplugin.wfplugin.storage.country;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.storage.Position2d;
import wfplugin.wfplugin.storage.Regions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

import static wfplugin.wfplugin.WFPlugin.strings;

public class Country {
    public String name;
    public String leader;
    public Regions regions = new Regions();
    public int balance = 0;
    public ArrayList<String> citizens = new ArrayList<>();
    public ArrayList<String> wars = new ArrayList<>();
    public GroupManager groups = new GroupManager();
    public ArrayList<Plot> plots = new ArrayList<>();
    public Plot defaultPlot = new Plot("default", null, "default", Collections.emptyList());
    public int freeBlocks = 5000;
    public int id = -1;

    public Country(String name, String leader) {
        this.leader = leader;
        this.name = name;
        citizens.add(leader);
    }

    public Country(String name, String leader, ArrayList<String> citizens, Regions regions, ArrayList<String> restricted, ArrayList<String> banned, ArrayList<String> ministers, ArrayList<String> wars, int balance) {
        this.name = name;
        this.leader = leader;
        this.citizens = citizens;
        this.wars = wars;
        this.regions = regions;
        this.balance = balance;
    }

    public boolean isLeader(String player) {
        return leader.equals(player);
    }

    public void setLeader(String player) {
        leader = player;
        sendToAll(strings.newLeader(leader, player));
        WFPlugin.flushConfigs();
    }

    public boolean isMinister(String player) {
        return groups.getByPlayer(player).index >= Integer.MAX_VALUE;
    }

    public boolean isCitizen(String player) {
        return citizens.contains(player);
    }

    public void addCitizen(String player) {
        citizens.add(player);
        groups.addCitizen(player);
        sendToAll(strings.citizenJoin(player, this.name));
        freeBlocks += 2000;
        WFPlugin.flushConfigs();
    }

    public void removeCitizen(String player) {
        if (player.equals(leader))
            throw new IllegalStateException("Leader can't leave nation");

        citizens.remove(player);
        groups.removeCitizen(player);
        freeBlocks -= 2000;

        WFPlugin.flushConfigs();
        sendToAll(strings.citizenLeft(player, this.name));
    }

    public void addMinister(String player) {
        groups.setGroup(player, "minister");
        sendToAll(strings.addMinister(player, this.name));
        WFPlugin.flushConfigs();
    }

    public void removeMinister(String player) {
        groups.setGroup(player, "default");
        sendToAll(strings.removeMinister(player, this.name));
        WFPlugin.flushConfigs();
    }

    public void restrictCitizen(String player) {
        groups.setGroup(player, "restricted");
        sendToAll(strings.restrictCitizen(player, this.name));
        WFPlugin.flushConfigs();
    }

    public void unRestrictCitizen(String player) {
        groups.setGroup(player, "default");
        sendToAll(strings.unRestrictCitizen(player, this.name));
        WFPlugin.flushConfigs();
    }

    public void sendToAll(Text text) {
        if (text != null)
            for (Player onlinePlayer : Sponge.getServer().getOnlinePlayers())
                if (citizens.contains(onlinePlayer.getName()))
                    onlinePlayer.sendMessage(text);
    }

    public boolean allowInteract(Player player, Location<World> interact) {
        return allowInteract(player, new Position2d(interact));
    }

    public boolean allowInteract(Player player, Position2d interact) {
        boolean result;
        String name = player.getName();

        result = defaultPlot.players.contains(player.getName()) || groups.hasRights(player.getName(), defaultPlot.group);

        for (Plot plot : plots)
            if (plot.region2d.contains(interact)) {
                result = plot.players.contains(player.getName()) || groups.hasRights(player.getName(), plot.group);
                break;
            }

        if (player.hasPermission("wf.admin.country.interact-bypass")) result = true;

        Country playerCountryName = WFPlugin.countries.getByCitizen(name);
        if (playerCountryName != null) {
            if (wars == null)
                wars = new ArrayList<>();
            for (String war : wars)
                if (war.equals(playerCountryName.name)) {
                    result = true;
                    break;
                }
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void include(Country who) {
        regions.addAll(who.regions);
        balance += who.balance;
        citizens.addAll(who.citizens);
        freeBlocks += who.freeBlocks;
    }

    public Optional<Plot> plotByLocation(Location<World> location) {
        Position2d location1 = new Position2d(location);
        for (Plot plot : plots)
            if (plot.region2d.contains(location1)) return Optional.of(plot);
        return Optional.empty();
    }
}
