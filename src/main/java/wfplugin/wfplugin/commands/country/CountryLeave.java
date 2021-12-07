package wfplugin.wfplugin.commands.country;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;
import wfplugin.wfplugin.storage.country.Country;

import static wfplugin.wfplugin.WFPlugin.flushConfigs;
import static wfplugin.wfplugin.WFPlugin.strings;

public class CountryLeave extends Command {


    @Override
    public String[] names() {
        return new String[]{"leave"};
    }

    @Override
    public String permission() {
        return "wf.country.leave";
    }

    @Override
    public Command[] children() {
        return new Command[]{};
    }

    @Override
    public Text description() {
        return super.description();
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{};
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            Country country = WFPlugin.countries.getByCitizen(src.getName());
            if (country == null)
                src.sendMessage(strings.countryNotSelected());
            else if (country.isLeader(src.getName()))
                src.sendMessage(Text.of("You are leader!"));
            else
                country.removeCitizen(src.getName());
            flushConfigs();
            return CommandResult.success();
        };
    }
}
