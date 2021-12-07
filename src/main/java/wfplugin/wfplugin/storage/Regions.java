package wfplugin.wfplugin.storage;

import com.google.common.collect.ImmutableList;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.storage.country.Country;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Regions {
    public final ArrayList<Region2d> regions = new ArrayList<>();
    private int maxId;

    public Regions(Region2d... regions) {
        this.regions.addAll(Arrays.asList(regions));
    }

    public void add(Region2d region) {
        Region2d selection = region.getCorrected();
        int[] claimed = {selection.size()};
        System.out.println(claimed[0]);
        for (Region2d region2d : ImmutableList.copyOf(regions)) {
            Region2d intersection = region2d.getIntersection(selection);
            if (intersection != null) {
                System.out.println(intersection);
                claimed[0] -= intersection.size();
                if (region2d.equals(intersection)) regions.remove(region2d);
                else region2d.removed.add(intersection);
            }
        }
        System.out.println(claimed[0] + "\n");
        if (claimed[0] != 0)
            regions.add(selection);
    }

    public int freeBlocks(Region2d region, Country owner) {
        Region2d selection = region.getCorrected();
        int[] result = {selection.size()};
        for (Country country : WFPlugin.countries.countries)
            if (!country.name.equals(owner.name))
                if (country.regions.intersects(region))
                    for (Region2d region2d : country.regions.regions)
                        if (region2d.getIntersection(region) != null)
                            return -1;
        for (Region2d region2d : ImmutableList.copyOf(regions))
            if (region2d.getIntersection(selection) != null)
                result[0] -= region2d.getIntersection(selection).size();
        return result[0];
    }

    public int remove(Region2d region) {
        int result = 0;
        Region2d selection = region.getCorrected();
        for (Region2d region2d : ImmutableList.copyOf(regions)) {
            Region2d intersection = region2d.getIntersection(selection);
            if (intersection != null) {
                result += intersection.size();
                region2d.removed.add(intersection);
                if (region2d.isEmpty()) regions.remove(region2d);
            }
        }
        return result;
    }

    public boolean contains(Location<World> location) {
        return contains(new Position2d(location));
    }

    public boolean contains(Position2d location) {
        for (Region2d region : regions)
            if (region.contains(location))
                return true;
        return false;
    }

    public List<Region2d> getContains(Region2d location, ArrayList<Region2d> regions) {
        return regions
                .stream()
                .filter(region2d -> region2d.contains(region2d))
                .collect(Collectors.toList());
    }

    public List<Region2d> getIntersected(Region2d location, ArrayList<Region2d> regions) {
        return regions
                .stream()
                .filter(region2d -> region2d.intersected(region2d))
                .collect(Collectors.toList());
    }

    public int newId() {
        int newId = maxId + 1;
        maxId = newId;
        return newId;
    }

    public boolean contains(Region2d region2d) {
        return !getContains(region2d, regions).isEmpty();
    }

    public boolean intersects(Region2d region2d) {
        return !getIntersected(region2d, regions).isEmpty();
    }

    public int size() {
        return regions.stream().mapToInt(Region2d::size).sum();
    }

    public ArrayList<Region2d> allRegions() {
        return new ArrayList<>(regions);
    }

    public void addAll(Regions regions) {
        this.regions.addAll(regions.regions);
    }
}
