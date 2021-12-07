package wfplugin.wfplugin.commands.country.economy;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;
import wfplugin.wfplugin.storage.country.Country;

import java.util.Optional;

public class CountryBalance extends Command {
    @Override
    public String[] names() {
        return new String[]{"balance", "money"};
    }

    @Override
    public String permission() {
        return "wf.country.balance";
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
                                GenericArguments.string(Text.of("balance")),
                                "wf.admin.country.balance"
                        )
                )
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
            else if (hasRights || country.isMinister(src.getName()))
                src.sendMessage(Text.of("Balance: " + country.balance));
            else
                src.sendMessage(Text.of("Not enough rights"));

            return CommandResult.success();
        };
    }
}
