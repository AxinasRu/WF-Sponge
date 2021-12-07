package wfplugin.wfplugin.storage.country;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.format.TextColors;

import java.util.*;
import java.util.function.Consumer;

public class GroupManager {
    public ArrayList<CountryGroup> groups = new ArrayList<>(Arrays.asList(
            new CountryGroup("restricted", "Ограниченный", "", 0, TextColors.GRAY),
            new CountryGroup("default", "Житель", "", 1, TextColors.BLUE),
            new CountryGroup("military", "Военный", "", 2, TextColors.DARK_GREEN),
            new CountryGroup("minister", "Министр", "", Integer.MAX_VALUE, TextColors.RED)
    ));

    public HashMap<String, String> playerGroups = new HashMap<>();

    public CountryGroup get(String id) {
        Optional<CountryGroup> any = groups.stream().filter(countryGroup -> countryGroup.id.equals(id)).findAny();
        if (!any.isPresent())
            throw new IllegalArgumentException("Group not exist");
        return any.get();
    }

    public CountryGroup getByPlayer(String player) {
        return get(playerGroups.get(player));
    }

    public void addCitizen(String player) {
        playerGroups.put(player, "default");
    }

    public void addCitizens(String... players) {
        for (String player : players)
            playerGroups.put(player, "default");
    }

    public void removeCitizen(String player) {
        playerGroups.remove(player);
    }

    public void setGroup(String player, String group) {
        playerGroups.remove(player);
        playerGroups.put(player, group);
    }

    public List<String> playersByGroup(String groupId) {
        List<String> result = new ArrayList<>();
        playerGroups.forEach((player, group) -> {
            if (group.equals(groupId))
                result.add(player);
        });
        return result;
    }

    public List<Player> playersInstancesByGroup(String groupId) {
        List<Player> result = new ArrayList<>();
        playerGroups.forEach((playerName, group) -> {
            if (group.equals(groupId))
                Sponge.getServer().getPlayer(playerName).ifPresent(result::add);
        });
        return result;
    }

    public List<Player> applyOnlinePlayersByGroup(String groupId, Consumer<Player> supplier) {
        List<Player> result = new ArrayList<>();
        playerGroups.forEach((playerName, group) -> {
            if (group.equals(groupId))
                Sponge.getServer().getPlayer(playerName).ifPresent(supplier);
        });
        return result;
    }

    public boolean hasRights(String player, String groupName) {
        CountryGroup byPlayer = getByPlayer(player);
        CountryGroup countryGroup = get(groupName);
        return byPlayer.index >= countryGroup.index;
    }
}
