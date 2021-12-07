package wfplugin.wfplugin.commands.country.citizen;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;
import wfplugin.wfplugin.storage.country.Country;

import static wfplugin.wfplugin.WFPlugin.flushConfigs;
import static wfplugin.wfplugin.WFPlugin.strings;

public class CountryCitizensReject extends Command {

    @Override
    public String[] names() {
        return new String[]{"reject"};
    }

    @Override
    public String permission() {
        return "wf.country.citizens.reject";
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
                GenericArguments.player(Text.of("player"))
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            Player player = args.<Player>getOne("player").get();
            Country country = WFPlugin.countries.getByCitizen(src.getName());

            if (country != null && country.isMinister(src.getName())) {
                String request = WFPlugin.requests.get(player.getName());
                if (request != null && request.equals(country.name)) {
                    WFPlugin.requests.remove(player.getName());

                    country.groups.applyOnlinePlayersByGroup("minister", s -> s.sendMessage(strings.countryCitizenRejectMinister(player, src.getName())));
                    player.sendMessage(strings.countryCitizenReject(country.name));
                }
            }

            flushConfigs();
            return CommandResult.success();
        };
    }
}
