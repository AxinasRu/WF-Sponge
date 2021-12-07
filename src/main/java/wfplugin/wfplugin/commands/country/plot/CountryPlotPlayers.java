package wfplugin.wfplugin.commands.country.plot;

import wfplugin.wfplugin.commands.Command;

public class CountryPlotPlayers extends Command {
    @Override
    public String[] names() {
        return new String[]{"players"};
    }

    @Override
    public String permission() {
        return "wf.country.plot.players";
    }

    @Override
    public Command[] children() {
        return new Command[]{
                new CountryPlotPlayersAdd(),
                new CountryPlotPlayersList(),
                new CountryPlotPlayersRemove()
        };
    }
}
