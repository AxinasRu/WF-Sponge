package wfplugin.wfplugin.commands.country.economy;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;
import wfplugin.wfplugin.storage.country.Country;

import static wfplugin.wfplugin.WFPlugin.flushConfigs;

public class CountryDeposit extends Command {
    @Override
    public String[] names() {
        return new String[]{"deposit"};
    }

    @Override
    public String permission() {
        return "wf.country.deposit";
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
                GenericArguments.integer(Text.of("money"))
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            int[] money = {args.<Integer>getOne("money").get()};
            Country country = WFPlugin.countries.getByCitizen(src.getName());

            if (country != null)
                WFPlugin.bank.players.compute(src.getName(), (s, balance) -> {
                    if (balance == null || balance == 0)
                        return 0;
                    if (balance < money[0])
                        money[0] = balance;
                    country.balance += money[0];
                    return balance - money[0];
                });

            flushConfigs();
            return CommandResult.success();
        };
    }
}
