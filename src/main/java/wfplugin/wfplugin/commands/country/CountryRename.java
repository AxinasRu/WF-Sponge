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

public class CountryRename extends Command {
    @Override
    public String[] names() {
        return new String[]{"rename"};
    }

    @Override
    public String permission() {
        return "wf.country.rename";
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{
                GenericArguments.optional(
                        GenericArguments.requiringPermission(
                                GenericArguments.string(Text.of("country")),
                                "wf.admin.country.rename"
                        )
                ),
                GenericArguments.string(Text.of("new name"))
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            Optional<String> countryName = args.getOne("country");
            Country country;
            boolean hasRights = false;
            if (countryName.isPresent()) {
                country = WFPlugin.countries.get(countryName.get());
                hasRights = true;
            } else
                country = WFPlugin.countries.getByCitizen(src.getName());

            if (country == null)
                src.sendMessage(Text.of("Country not selected"));
            else if (!hasRights && !country.isMinister(src.getName()))
                src.sendMessage(Text.of("Not enough rights"));
            else {
                String newName = args.<String>getOne("new name").get();
                country.sendToAll(Text.of(country.name + " renamed to " + newName));
                country.name = newName;
            }

            flushConfigs();
            return CommandResult.success();
        };
    }
}
