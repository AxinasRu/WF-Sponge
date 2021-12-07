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

import static wfplugin.wfplugin.WFPlugin.strings;

public class CountryInfo extends Command {
    @Override
    public String[] names() {
        return new String[]{"info", "i", "about"};
    }

    @Override
    public String permission() {
        return "wf.country.info";
    }

    @Override
    public Text description() {
        return super.description();
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{
                GenericArguments.optional(GenericArguments.string(Text.of("name")))
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            String srcName = src.getName();
            Optional<String> name = args.getOne("name");
            Country country;

            if (name.isPresent())
                country = WFPlugin.countries.get(name.get());
            else
                country = WFPlugin.countries.getByCitizen(srcName);

            src.sendMessage(strings.countryInfo(country, srcName));

            return CommandResult.success();
        };
    }
}
