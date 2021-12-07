package wfplugin.wfplugin.commands.country;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;
import wfplugin.wfplugin.storage.Region2d;
import wfplugin.wfplugin.storage.country.Country;

import static wfplugin.wfplugin.WFPlugin.flushConfigs;
import static wfplugin.wfplugin.WFPlugin.strings;

public class CountryClaim extends Command {
    @Override
    public String[] names() {
        return new String[]{"claim"};
    }

    @Override
    public String permission() {
        return "wf.country.claim";
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
        return new CommandElement[]{};
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            Country country = WFPlugin.countries.getByCitizen(src.getName());
            Region2d selected = WFPlugin.selectedRegions.get(src.getName());

            if (country == null)
                src.sendMessage(strings.countryNotSelected());
            else if (selected == null || selected.size() == 0)
                src.sendMessage(strings.selectionEmpty());
            else if (!country.isMinister(src.getName()))
                src.sendMessage(strings.onlyMinisters());
            else {
                int i = country.regions.freeBlocks(selected, country);
                if (i == -1)
                    src.sendMessage(strings.haveIntersections());
                else if (i > country.freeBlocks)
                    src.sendMessage(strings.notEnoughBlocks(selected.size() - country.freeBlocks));
                else if (i == 0)
                    src.sendMessage(strings.nothingChanged());
                else {
                    country.regions.add(selected);
                    src.sendMessage(strings.successClaimed(i));
                    country.freeBlocks -= i;
                }
            }
            flushConfigs();
            return CommandResult.success();
        };
    }
}
