package wfplugin.wfplugin.commands.country.chat;

import com.google.gson.Gson;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;
import wfplugin.wfplugin.storage.country.Country;

import java.util.Optional;

public class CountryInfo extends Command {
    @Override
    public String[] names() {
        return new String[]{"info"};
    }

    @Override
    public String permission() {
        return "wf.country.info";
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{GenericArguments.optional(GenericArguments.string(Text.of("country")))};
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
                src.sendMessage(Text.of("Country not selected"));
            else
                src.sendMessage(Text.of("Ой, ну нафиг всё это расписывать, давай договоримся? Я тебе кину JSON и мы расстанемся\n\n" + new Gson().toJson(country)));

            return CommandResult.success();
        };
    }
}
