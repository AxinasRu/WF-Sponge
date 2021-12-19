package wfplugin.wfplugin.commands.chat;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;

public class UnMuteCommand extends Command {
    @Override
    public String[] names() {
        return new String[]{"unmute"};
    }

    @Override
    public String permission() {
        return "wf.admin.chat.unmute";
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{
                GenericArguments.player(Text.of("player"))
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            Player player = args.<Player>getOne("player").orElseThrow(IllegalArgumentException::new);

            Text muteString = WFPlugin.strings.unMutePlayer(src, player);
            Sponge.getServer().getOnlinePlayers().forEach(onlinePlayer -> onlinePlayer.sendMessage(muteString));

            WFPlugin.mutes.remove(player.getName());
            WFPlugin.flushConfigs();

            return CommandResult.success();
        };
    }
}
