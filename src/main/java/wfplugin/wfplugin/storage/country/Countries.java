package wfplugin.wfplugin.storage.country;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.storage.Region2d;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static wfplugin.wfplugin.WFPlugin.strings;

public class Countries {
    public HashSet<Country> countries = new HashSet<>();
    private int maxId;

    public Country getByLeader(String name) {
        return countries.stream().filter(country -> name.equals(country.leader)).findFirst().orElse(null);
    }

    public Country get(String name) {
        for (Country country : countries)
            if (country.name.equals(name))
                return country;
        return null;
    }

    public Country getByCitizen(String player) {
        Country[] result = {null};
        countries.forEach((country) -> {
            if (country.citizens.contains(player)) result[0] = country;
        });
        return result[0];
    }

    public boolean exist(String name) {
        for (Country country : countries)
            if (country.name.equals(name))
                return true;
        return false;
    }

    public boolean create(String name, Player leader) {
        if (exist(name))
            return false;
        Country e = new Country(name, leader.getName());
        e.id = newId();
        countries.add(e);
        WFPlugin.flushConfigs();
        return true;
    }

    public int newId() {
        int newId = maxId + 1;
        maxId = newId;
        return newId;
    }

    public boolean remove(String name) {
        if (get(name) == null)
            return false;
        Country country = get(name);
        country.sendToAll(strings.countryRemoved(name));
        countries.remove(country);
        WFPlugin.flushConfigs();
        return true;
    }

    public boolean allowInteract(Player player, Location<World> location) {
        boolean result = true;
        Optional<Country> optionalCountry = getByLocation(location);
        if (optionalCountry.isPresent())
            result = optionalCountry.get().allowInteract(player, location);
        return result;
    }

    public Optional<Country> getByLocation(Location<World> location) {
        return countries.stream().filter(c -> c.regions.contains(location)).findFirst();
    }

    public List<Country> getByIntersections(Region2d region2d, Country... ignored) {
        return countries.stream().filter(country -> {
            for (Country ignoredCountry : ignored)
                if (ignoredCountry.equals(country))
                    return false;
            return country.regions.intersects(region2d);
        }).collect(Collectors.toList());
    }
}
