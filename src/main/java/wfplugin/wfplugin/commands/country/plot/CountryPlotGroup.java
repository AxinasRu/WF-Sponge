package wfplugin.wfplugin.commands.country.plot;

import wfplugin.wfplugin.commands.Command;

public class CountryPlotGroup extends Command {
    @Override
    public String[] names() {
        return new String[]{"group"};
    }

    @Override
    public String permission() {
        return "wf.country.plot.group";
    }

    @Override
    public Command[] children() {
        return new Command[]{
                new CountryPlotGroupSet(),
                new CountryPlotGroupGet()
        };
    }
}
