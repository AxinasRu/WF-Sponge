package wfplugin.wfplugin.commands.country.plot;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;
import wfplugin.wfplugin.storage.country.Country;
import wfplugin.wfplugin.storage.country.Plot;

public class CountryPlotPlayersAdd extends Command {
    @Override
    public String[] names() {
        return new String[]{"add"};
    }

    @Override
    public String permission() {
        return "wf.country.plot.players.add";
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{
                GenericArguments.string(Text.of("id")),
                GenericArguments.player(Text.of("player"))
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            String plotId = args.<String>getOne("id").orElse("");
            Player player = args.<Player>getOne("player").get();
            Country country = WFPlugin.countries.getByCitizen(src.getName());

            if (!country.isMinister(src.getName()))
                src.sendMessage(WFPlugin.strings.onlyMinisters());

            Plot plot = null;
            if (plotId.equals(country.defaultPlot.id))
                plot = country.defaultPlot;
            else
                for (Plot plot1 : country.plots) {
                    if (plot1.id.equals(plotId))
                        plot = plot1;
                    break;
                }

            if (plot == null)
                src.sendMessage(WFPlugin.strings.plotNotFound(plotId));
            else {
                plot.players.add(player.getName());
                src.sendMessage(WFPlugin.strings.plotAddPlayer(player.getName()));
            }

            return CommandResult.success();
        };
    }
}
