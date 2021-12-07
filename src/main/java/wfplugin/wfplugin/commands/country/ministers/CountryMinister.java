package wfplugin.wfplugin.commands.country.ministers;

import wfplugin.wfplugin.commands.Command;

public class CountryMinister extends Command {
    @Override
    public String[] names() {
        return new String[]{"ministers"};
    }

    @Override
    public String permission() {
        return "wf.country.ministers";
    }

    @Override
    public Command[] children() {
        return new Command[]{
                new CountryMinistersAdd(),
                new CountryMinistersList(),
                new CountryMinistersRemove()
        };
    }
}
