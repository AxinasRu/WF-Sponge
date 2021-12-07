package wfplugin.wfplugin.commands.country.ministers;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;
import wfplugin.wfplugin.storage.country.Country;

import java.util.Optional;

import static wfplugin.wfplugin.WFPlugin.strings;

public class CountryMinistersList extends Command {
    @Override
    public String[] names() {
        return new String[]{"list"};
    }

    @Override
    public String permission() {
        return "wf.country.ministers.list";
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
                                "wf.admin.country.ministers.list"
                        )
                )
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {

            Optional<String> countryName = args.getOne("country");
            Country country;
            if (countryName.isPresent())
                country = WFPlugin.countries.get(countryName.get());
            else
                country = WFPlugin.countries.getByCitizen(src.getName());

            if (country == null)
                src.sendMessage(strings.countryNotSelected());
            else
                src.sendMessage(strings.listMinisters(country.name, country.groups.playersByGroup("minister")));

            return CommandResult.success();
        };
    }
}
