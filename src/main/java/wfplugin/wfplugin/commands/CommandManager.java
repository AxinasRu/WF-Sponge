package wfplugin.wfplugin.commands;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Text;

import java.util.Arrays;

import static wfplugin.wfplugin.WFPlugin.log;

public class CommandManager {
    public static CommandSpec parseCommand(Command command) {
        CommandSpec.Builder commandSpec = CommandSpec
                .builder();

        Text description = command.description();
        if (description != null)
            commandSpec.description(description);

        CommandElement[] args = command.args();
        if (args != null)
            commandSpec.arguments(args);

        String permission = command.permission();
        if (permission != null)
            commandSpec.permission(permission);

        CommandExecutor executor = command.executor();
        if (executor != null)
            commandSpec.executor(executor);

        for (Command child : command.children())
            commandSpec.child(parseCommand(child), child.names());

        return commandSpec.build();
    }

    public static void registerCommands(PluginContainer plugin, Command... commands) {
        for (Command command : commands) {
            log(Arrays.toString(command.names()));
            Sponge.getCommandManager().register(plugin, parseCommand(command), command.names());
        }
    }
}
