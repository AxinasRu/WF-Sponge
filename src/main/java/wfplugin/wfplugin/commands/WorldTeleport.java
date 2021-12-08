package wfplugin.wfplugin.commands;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import wfplugin.wfplugin.WFPlugin;

import java.util.Locale;

public class WorldTeleport extends Command {
    @Override
    public String[] names() {
        return new String[]{"wtp", "wteleport", "worldtp", "worldteleport"};
    }

    @Override
    public String permission() {
        return "wf.world.teleport";
    }

    @Override
    public Text description() {
        return Text.of("Teleport to ", TextColors.GOLD, "mine world", TextColors.WHITE, ", ", TextColors.GOLD, "spawn", TextColors.WHITE, " and ", TextColors.GOLD, "default");
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{
                GenericArguments.string(Text.of("world"))
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            String world = args.<String>getOne("world").orElseThrow(IllegalArgumentException::new).toLowerCase(Locale.ROOT);

            if (!world.equals("world") && !world.equals("mine")) {
                src.sendMessage(WFPlugin.strings.worldIncorrect());
                return CommandResult.success();
            }

            WFPlugin.oldPos.putDeparsed(src, ((Player) src).getWorld().getName(), ((Player) src).getLocation());
            Location<World> deparsed = WFPlugin.oldPos.getDeparsed(src, world);
            ((Player) src).setLocation(deparsed);
            WFPlugin.flushConfigs();
            return CommandResult.success();
        };
    }
}
