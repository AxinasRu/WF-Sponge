package wfplugin.wfplugin.commands.chat;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import wfplugin.wfplugin.ChatMode;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;

public class ChatCommand extends Command {
    @Override
    public String[] names() {
        return new String[]{"chat"};
    }

    @Override
    public String permission() {
        return "wf.chat.use";
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{
                GenericArguments.enumValue(Text.of("type"), ChatMode.class)
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            ChatMode type = args.<ChatMode>getOne("type").get();
            WFPlugin.chatMode.put(src.getName(), type);
            return CommandResult.success();
        };
    }
}
