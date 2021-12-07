package wfplugin.wfplugin.commands.country.citizen;

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

public class CountryCitizensInvite extends Command {

    @Override
    public String[] names() {
        return new String[]{"invite", "addMember"};
    }

    @Override
    public String permission() {
        return "wf.country.citizens.invite";
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

            if (WFPlugin.countries.getByCitizen(player.getName()) != null)
                src.sendMessage(strings.alreadyCitizenInvite());
            else if (country == null)
                src.sendMessage(strings.countryNotSelected());
            else if (!country.isMinister(src.getName()))
                src.sendMessage(strings.onlyMinisters());
            else {
                String request = WFPlugin.requests.get(player.getName());
                if (request != null && request.equals(country.name)) {
                    WFPlugin.requests.remove(player.getName());
                    country.addCitizen(player.getName());
                } else {
                    WFPlugin.invites.put(player.getName(), country.name);
                    src.sendMessage(strings.countryInviteSent(player.getName()));
                    player.sendMessage(strings.countryInviteReceive(country.name));
                }
            }

            return CommandResult.success();
        };
    }
}
