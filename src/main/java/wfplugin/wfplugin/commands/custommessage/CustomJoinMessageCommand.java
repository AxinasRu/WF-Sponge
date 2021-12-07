package wfplugin.wfplugin.commands.custommessage;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;

import java.util.Optional;

public class CustomJoinMessageCommand extends Command {
    @Override
    public String[] names() {
        return new String[]{"join"};
    }

    @Override
    public String permission() {
        return "wf.custommessage.join";
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{
                GenericArguments.optional(
                        GenericArguments.requiringPermission(
                                GenericArguments.firstParsing(
                                        GenericArguments.string(Text.of("player")),
                                        GenericArguments.player(Text.of("player"))
                                ),
                                "wf.custommessage.set.leave"
                        )
                ),
                GenericArguments.remainingJoinedStrings(Text.of("text"))
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            Optional<Object> player = args.getOne("player");
            String consumer = src.getName();
            if (player.isPresent()) {
                if (player.get() instanceof Player)
                    consumer = ((Player) player.get()).getName();
                if (player.get() instanceof String)
                    consumer = (String) player.get();
            }

            String text = args.<String>getOne("text").get();
            WFPlugin.strings.joinMessages.put(consumer, text);
            src.sendMessage(Text.of(TextColors.GREEN, "Success"));
            WFPlugin.flushConfigs();
            return CommandResult.success();
        };
    }
}
