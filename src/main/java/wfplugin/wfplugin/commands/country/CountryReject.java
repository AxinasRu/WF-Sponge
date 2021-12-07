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

import static wfplugin.wfplugin.WFPlugin.strings;

public class CountryReject extends Command {


    @Override
    public String[] names() {
        return new String[]{"reject"};
    }

    @Override
    public String permission() {
        return "wf.country.reject";
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
            if (country != null) {
                String invite = WFPlugin.invites.get(src.getName());
                if (invite != null && invite.equals(countryName)) {
                    WFPlugin.invites.remove(src.getName());

                    src.sendMessage(strings.countryReject(country.name));
                    country.groups.applyOnlinePlayersByGroup("minister", player -> player.sendMessage(strings.countryRejectMinister(src.getName())));
                }
            }

            return CommandResult.success();
        };
    }
}
