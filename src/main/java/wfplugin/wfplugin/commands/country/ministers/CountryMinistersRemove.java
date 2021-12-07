package wfplugin.wfplugin.commands.country.ministers;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;
import wfplugin.wfplugin.storage.country.Country;

import java.util.Optional;

import static wfplugin.wfplugin.WFPlugin.flushConfigs;
import static wfplugin.wfplugin.WFPlugin.strings;

public class CountryMinistersRemove extends Command {
    @Override
    public String[] names() {
        return new String[]{"remove"};
    }

    @Override
    public String permission() {
        return "wf.country.ministers.remove";
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
                GenericArguments.player(Text.of("player")),
                GenericArguments.optional(
                        GenericArguments.requiringPermission(
                                GenericArguments.string(Text.of("country")),
                                "wf.admin.country.ministers.remove"
                        )
                )
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            Player player = args.<Player>getOne("player").get();
            Optional<String> countryName = args.getOne("country");
            Country country;
            boolean hasRights = false;
            if (countryName.isPresent()) {
                country = WFPlugin.countries.get(countryName.get());
                hasRights = true;
            } else
                country = WFPlugin.countries.getByCitizen(src.getName());

            if (country == null)
                src.sendMessage(strings.countryNotSelected());
            else if (hasRights || country.isLeader(src.getName()))
                country.removeMinister(player.getName());
            else
                src.sendMessage(strings.onlyLeader());

            flushConfigs();
            return CommandResult.success();
        };
    }
}
