package wfplugin.wfplugin.commands.country.plot;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;
import wfplugin.wfplugin.storage.Region2d;
import wfplugin.wfplugin.storage.country.Country;
import wfplugin.wfplugin.storage.country.Plot;

import java.util.ArrayList;
import java.util.Collections;

public class CountryPlotNew extends Command {
    @Override
    public String[] names() {
        return new String[]{"new"};
    }

    @Override
    public String permission() {
        return "wf.country.plot.new";
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{
                GenericArguments.string(Text.of("id")),
                GenericArguments.string(Text.of("name")),
                GenericArguments.optional(
                        GenericArguments.string(Text.of("group"))
                ),
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            String plotId = args.<String>getOne("id").orElse("");
            String plotName = args.<String>getOne("name").orElse("");
            String plotGroup = args.<String>getOne("group").orElse("default");
            Country country = WFPlugin.countries.getByCitizen(src.getName());

            if (!country.isMinister(src.getName()))
                src.sendMessage(WFPlugin.strings.onlyMinisters());

            for (Plot plot : country.plots) {
                if (plot.id.equals(plotId)) {
                    src.sendMessage(WFPlugin.strings.plotAlreadyExist());
                    return CommandResult.success();
                }
            }
            Region2d selected = WFPlugin.selectedRegions.get(src.getName());
            if (selected == null || selected.size() == 0)
                src.sendMessage(WFPlugin.strings.regionNotSelected());
            else {
                new Plot(plotId, plotName, selected.getCorrected(), plotGroup, new ArrayList<>(Collections.emptyList()));
            }

            return CommandResult.success();
        };
    }
}
