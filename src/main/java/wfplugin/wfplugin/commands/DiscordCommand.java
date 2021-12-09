package wfplugin.wfplugin.commands;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import wfplugin.wfplugin.utils.Discord;

public class DiscordCommand extends Command {
    @Override
    public String[] names() {
        return new String[]{"discord"};
    }

    @Override
    public String permission() {
        return "wf.discord";
    }

    @Override
    public Text description() {
        return Text.of("Discord integration command");
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{
                GenericArguments.string(Text.of("tag"))
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            String tag = args.<String>getOne("tag").orElseThrow(IllegalArgumentException::new);
            String integrate = Discord.integrate(src.getName(), tag);

            src.sendMessage(
                    Text.builder()
                            .onClick(TextActions.suggestCommand(integrate))
                            .onHover(TextActions.showText(Text.of("\u041D\u0430\u0436\u043C\u0438\u0442\u0435 \u0434\u043B\u044F \u0432\u0441\u0442\u0430\u0432\u043A\u0438 \u0438 \u043F\u043E\u0441\u043B\u0435\u0434\u0443\u044E\u0449\u0435\u0433\u043E \u043A\u043E\u043F\u0438\u0440\u043E\u0432\u0430\u043D\u0438\u044F")))
                            .append(Text.of("\u0422\u043E\u043A\u0435\u043D: ", TextColors.GOLD, integrate)).build()
            );

            return CommandResult.success();
        };
    }
}