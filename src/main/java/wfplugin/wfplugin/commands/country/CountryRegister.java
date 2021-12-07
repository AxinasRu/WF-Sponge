package wfplugin.wfplugin.commands.country;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;

import static wfplugin.wfplugin.WFPlugin.*;

public class CountryRegister extends Command {
    @Override
    public String[] names() {
        return new String[]{"register", "reg", "create", "new"};
    }

    @Override
    public String permission() {
        return "wf.country.register";
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
                GenericArguments.string(Text.of("name"))
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            String name = args.<String>getOne("name").get();

            boolean deducted = bank.tryDeduct(src, 4000);
            if (deducted) {
                if (WFPlugin.countries.create(name, (Player) src))
                    src.sendMessage(strings.createCountrySuccess(name));
                else
                    src.sendMessage(strings.createCountryAlreadyExist(name));
            } else
                src.sendMessage(strings.countryCreateNotEnoughMoney());

            flushConfigs();
            return CommandResult.success();
        };
    }
}
