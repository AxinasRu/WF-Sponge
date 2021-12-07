package wfplugin.wfplugin.storage;

import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.Objects;

public class Position2d {
    public int x, z;

    public Position2d(Location<World> location) {
        this.x = location.getBlockX();
        this.z = location.getBlockZ();
    }

    public Position2d(int x, int z) {
        this.x = x;
        this.z = z;
    }

    @Override
    public String toString() {
        return "x:" + x +
                ", z:" + z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position2d that = (Position2d) o;
        return x == that.x && z == that.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, z);
    }
}
