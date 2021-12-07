package wfplugin.wfplugin.utils;

import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import java.util.Optional;

public class SpongeUtils {
    private final static Server server = Sponge.getServer();

    public static boolean sendMessage(String playerName, Text msg) {
        Optional<Player> parsedPlayer = server.getPlayer(playerName);
        parsedPlayer.ifPresent(player -> player.sendMessage(msg));
        return parsedPlayer.isPresent();
    }
}
