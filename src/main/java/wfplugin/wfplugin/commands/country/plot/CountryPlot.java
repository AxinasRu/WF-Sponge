package wfplugin.wfplugin.commands.country.plot;

import org.spongepowered.api.command.args.CommandElement;
import wfplugin.wfplugin.commands.Command;

public class CountryPlot extends Command {
    @Override
    public String[] names() {
        return new String[]{"plot"};
    }

    @Override
    public String permission() {
        return "wf.country.plot";
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{};
    }

    @Override
    public Command[] children() {
        return new Command[]{
                new CountryPlotDelete(),
                new CountryPlotGroup(),
                new CountryPlotMove(),
                new CountryPlotNew(),
                new CountryPlotPlayers(),
                new CountryPlotRename()
        };
    }
}
