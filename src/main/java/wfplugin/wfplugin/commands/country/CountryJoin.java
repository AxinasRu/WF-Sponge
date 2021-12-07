package wfplugin.wfplugin.commands.country;

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

public class CountryJoin extends Command {


    @Override
    public String[] names() {
        return new String[]{"join"};
    }

    @Override
    public String permission() {
        return "wf.country.join";
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
                GenericArguments.string(Text.of("country"))
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            String countryName = args.<String>getOne("country").get();
            Country country = WFPlugin.countries.get(countryName);

            if (WFPlugin.countries.getByCitizen(src.getName()) != null)
                src.sendMessage(strings.alreadyCitizenJoin());
            else if (country == null)
                src.sendMessage(strings.countryNotSelected());
            else {
                String invite = WFPlugin.invites.get(src.getName());
                if (invite != null && invite.equals(countryName)) {
                    WFPlugin.invites.remove(src.getName());
                    country.addCitizen(src.getName());
                } else {
                    WFPlugin.requests.put(src.getName(), country.name);
                    src.sendMessage(strings.countryRequestSent(countryName));

                    country.groups.applyOnlinePlayersByGroup("minister", player -> player.sendMessage(strings.countryRequestReceive(src.getName())));
                }
            }

            flushConfigs();
            return CommandResult.success();
        };
    }
}
