package wfplugin.wfplugin.storage;

import lombok.AllArgsConstructor;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.HashMap;
import java.util.Optional;

public class OldPos {
    private HashMap<String, Position3d> players = new HashMap<>();

    public Location<World> getDeparsed(CommandSource player, String worldName) {
        Position3d position3d = players.get(worldName + player.getName());
        Optional<World> world = Sponge.getServer().getWorld(worldName);
        world.orElseThrow(IllegalArgumentException::new);
        if (position3d == null)
            return world.get().getSpawnLocation();
        return world.get().getLocation(position3d.x, position3d.y, position3d.z);
    }

    public void putDeparsed(CommandSource player, String worldName, Location<World> worldLocation) {
        Position3d position3d = new Position3d(worldLocation.getX(), worldLocation.getY(), worldLocation.getZ());
        players.put(worldName + player.getName(), position3d);
    }

    @AllArgsConstructor
    public static class Position3d {
        public double x, y, z;
    }
}
