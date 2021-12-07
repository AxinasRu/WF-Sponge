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

public class CountryWithdraw extends Command {
    @Override
    public String[] names() {
        return new String[]{"withdraw"};
    }

    @Override
    public String permission() {
        return "wf.country.withdraw";
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
                if (!country.isMinister(src.getName()))
                    src.sendMessage(Text.of("Not enough rights"));
                else
                    WFPlugin.bank.players.compute(src.getName(), (s, balance) -> {
                        if (balance == null)
                            balance = 0;
                        if (country.balance == 0)
                            return 0;
                        if (country.balance < money[0])
                            money[0] = country.balance;
                        country.balance -= money[0];
                        return balance + money[0];
                    });

            flushConfigs();
            return CommandResult.success();
        };
    }
}
