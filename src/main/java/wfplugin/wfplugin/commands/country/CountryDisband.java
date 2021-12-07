package wfplugin.wfplugin.commands.country;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;
import wfplugin.wfplugin.storage.country.Country;

import java.util.Optional;

import static wfplugin.wfplugin.WFPlugin.flushConfigs;
import static wfplugin.wfplugin.WFPlugin.strings;

public class CountryDisband extends Command {


    @Override
    public String[] names() {
        return new String[]{"disband"};
    }

    @Override
    public String permission() {
        return "wf.country.disband";
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
        return new CommandElement[]{
                GenericArguments.optional(
                        GenericArguments.requiringPermission(
                                GenericArguments.string(Text.of("country")),
                                "wf.admin.country.disband"
                        )
                )
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            Optional<String> argCountry = args.getOne("country");
            Country country;
            boolean hasRights = false;
            if (argCountry.isPresent()) {
                country = WFPlugin.countries.get(argCountry.get());
                hasRights = true;
            } else
                country = WFPlugin.countries.getByCitizen(src.getName());

            if (country == null)
                src.sendMessage(strings.countryNotSelected());
            else if (!hasRights && !country.isLeader(src.getName()))
                src.sendMessage(strings.onlyLeader());
            else {
                src.sendMessage(Text.of(country.name + " removed"));
                WFPlugin.countries.remove(country.name);
            }
            flushConfigs();
            return CommandResult.success();
        };
    }
}
