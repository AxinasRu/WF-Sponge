package wfplugin.wfplugin.commands.country.chat;

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

public class CountryBroadCast extends Command {
    @Override
    public String[] names() {
        return new String[]{"broadcast", "bc"};
    }

    @Override
    public String permission() {
        return "wf.country.broadcast";
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{
                GenericArguments.remainingJoinedStrings(Text.of("text"))
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            String text = args.<String>getOne("text").get();
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
                country.sendToAll(strings.broadcast(country.name, text));
            else
                src.sendMessage(Text.of("Not enough rights"));

            return CommandResult.success();
        };
    }
}
