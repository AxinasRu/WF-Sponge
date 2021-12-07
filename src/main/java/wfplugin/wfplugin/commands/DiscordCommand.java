package wfplugin.wfplugin.commands;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
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
            String tag = args.<String>getOne("tag").get();
            String integrate = Discord.integrate(src.getName(), tag);
            src.sendMessage(Text.of(integrate));
            return CommandResult.success();
        };
    }
}