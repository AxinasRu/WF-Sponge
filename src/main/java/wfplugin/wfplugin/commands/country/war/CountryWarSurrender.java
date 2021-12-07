package wfplugin.wfplugin.commands.country.war;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;
import wfplugin.wfplugin.storage.country.Country;

import static wfplugin.wfplugin.WFPlugin.strings;

public class CountryWarSurrender extends Command {
    @Override
    public String[] names() {
        return new String[]{"surrender", "capitulation"};
    }

    @Override
    public String permission() {
        return "wf.country.surrender";
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{
                GenericArguments.string(Text.of("country"))
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            Country who = WFPlugin.countries.get(src.getName());
            Country country = WFPlugin.countries.get(args.<String>getOne("country").get());
            if (!who.isLeader(src.getName()))
                src.sendMessage(strings.onlyLeader());
            else if (country == null)
                src.sendMessage(strings.countryNotSelected());
            else {
                who.sendToAll(strings.capitulated(who, country));
                country.include(who);
                WFPlugin.countries.countries.remove(who);
            }
            return CommandResult.success();
        };
    }
}
