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

public class CountryBuyBlocks extends Command {
    @Override
    public String[] names() {
        return new String[]{"buyblocks", "buyextra"};
    }

    @Override
    public String permission() {
        return "wf.country.buyblocks";
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
                GenericArguments.integer(Text.of("amount")),
                GenericArguments.optional(
                        GenericArguments.requiringPermission(
                                GenericArguments.string(Text.of("country")),
                                "wf.admin.country.buyBlocks"
                        )
                )
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            int amount = args.<Integer>getOne("amount").orElseThrow(IllegalStateException::new);
            Optional<String> countryName = args.getOne("country");
            Country country;
            boolean hasRights = false;
            if (countryName.isPresent()) {
                country = WFPlugin.countries.get(countryName.orElseThrow(IllegalStateException::new));
                hasRights = true;
            } else
                country = WFPlugin.countries.getByCitizen(src.getName());

            int cost = amount * 2;

            if (country == null)
                src.sendMessage(strings.countryNotSelected());
            else if (!hasRights && !country.isMinister(src.getName()))
                src.sendMessage(strings.onlyMinisters());
            else if (country.balance < cost)
                src.sendMessage(strings.notEnoughMoney());
            else {
                country.balance -= cost;
                country.freeBlocks += amount;
                src.sendMessage(strings.successBuyBlocks(amount, cost));
            }
            flushConfigs();
            return CommandResult.success();
        };
    }
}
