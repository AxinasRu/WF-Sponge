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
import wfplugin.wfplugin.storage.Mute;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

public class MuteCommand extends Command {
    @Override
    public String[] names() {
        return new String[]{"mute"};
    }

    @Override
    public String permission() {
        return "wf.chat.mute";
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{
                GenericArguments.player(Text.of("player")),
                GenericArguments.optional(GenericArguments.dateTime(Text.of("date"))),
                GenericArguments.string(Text.of("reason"))
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            Player player = args.<Player>getOne("player").get();
            Optional<LocalDateTime> optionalDate = args.getOne("date");
            long date = optionalDate.map(localDateTime -> localDateTime.toEpochSecond(ZoneOffset.UTC)).orElse(-1L);
            String reason = args.<String>getOne("reason").get();

            WFPlugin.mutes.remove(player.getName());
            WFPlugin.mutes.put(player.getName(), new Mute(date, reason));

            Text muteString = optionalDate.map(localDateTime ->
                    WFPlugin.strings.tempMutePlayer(src, player, localDateTime)
            ).orElse(WFPlugin.strings.mutePlayer(src, player));
            Sponge.getServer().getOnlinePlayers().forEach(onlinePlayer -> onlinePlayer.sendMessage(muteString));

            return CommandResult.success();
        };
    }
}
