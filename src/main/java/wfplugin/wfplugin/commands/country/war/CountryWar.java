package wfplugin.wfplugin.commands.country.war;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.spec.CommandExecutor;
import wfplugin.wfplugin.commands.Command;

import static wfplugin.wfplugin.WFPlugin.strings;

public class CountryWar extends Command {
    @Override
    public String[] names() {
        return new String[]{"war"};
    }

    @Override
    public String permission() {
        return "wf.country.war";
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{};
    }

    @Override
    public Command[] children() {
        return new Command[]{
                new CountryWarDeclare(),
                new CountryWarSurrender()
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            src.sendMessage(strings.tempNotWork());
            return CommandResult.success();
        };
    }
}
