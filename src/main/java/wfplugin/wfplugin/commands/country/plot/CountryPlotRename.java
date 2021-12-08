package wfplugin.wfplugin.commands.country.plot;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;
import wfplugin.wfplugin.storage.country.Country;
import wfplugin.wfplugin.storage.country.Plot;

import java.security.spec.ECField;

public class CountryPlotRename extends Command {
    @Override
    public String[] names() {
        return new String[]{"rename"};
    }

    @Override
    public String permission() {
        return "wf.country.plot.rename";
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{
                GenericArguments.string(Text.of("id")),
                GenericArguments.string(Text.of("name"))
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            String plotId = args.<String>getOne("id").orElse("");
            String plotName = args.<String>getOne("name").orElse("");
            Country country = WFPlugin.countries.getByCitizen(src.getName());

            if (!country.isMinister(src.getName()))
                src.sendMessage(WFPlugin.strings.onlyMinisters());

            Plot plot = null;
            if (plotId.equals(country.defaultPlot.id)) {
                src.sendMessage(WFPlugin.strings.plotDefaultNotAllowed());
                return CommandResult.success();
            }
            for (Plot plot1 : country.plots) {
                if (plot1.id.equals(plotId))
                    plot = plot1;
                break;
            }

            if (plot == null)
                src.sendMessage(WFPlugin.strings.plotNotFound(plotId));
            else {
                src.sendMessage(WFPlugin.strings.plotNewName(plotId, plotName));
                plot.name = plotName;
            }

            return CommandResult.success();
        };
    }
}
