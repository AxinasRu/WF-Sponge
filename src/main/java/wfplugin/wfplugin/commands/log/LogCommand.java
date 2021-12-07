package wfplugin.wfplugin.commands.log;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;

import java.util.Date;
import java.util.Optional;

public class LogCommand extends Command {
    @Override
    public String[] names() {
        return new String[]{"log"};
    }

    @Override
    public String permission() {
        return "wf.log";
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{
//                GenericArguments.optional(
                GenericArguments.firstParsing(
                        GenericArguments.player(Text.of("player")),
                        GenericArguments.string(Text.of("player"))
                )
//                )
//                GenericArguments.optional(
//                        GenericArguments.choices(Text.of(""))
//                ),
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            Optional<Object> player = args.getOne("player");
            String name = null;
            if (player.isPresent())
                if (player.get() instanceof Player)
                    name = ((Player) player.get()).getName();
                else if (player.get() instanceof String)
                    name = (String) player.get();

            Text.Builder builder = Text.builder();

            if (name == null)
                src.sendMessage(Text.of(TextColors.RED, "Player not selected"));
            else
                WFPlugin.log.getByPlayerStream(name).forEach(logElement -> {
                    Date date = new Date(logElement.time);
                    builder.append(Text.of(
                            TextColors.GOLD, "Date: ", TextColors.WHITE, date.toString(),
                            TextColors.GOLD, "Action: ", TextColors.WHITE, logElement.type,
                            TextColors.GOLD, "Command: ", TextColors.WHITE, logElement.command
                    ));
                });

            src.sendMessage(builder.build());
            return CommandResult.success();
        };
    }
}
