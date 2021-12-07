package wfplugin.wfplugin.commands.country.citizen;

import wfplugin.wfplugin.commands.Command;

public class CountryCitizens extends Command {
    @Override
    public String[] names() {
        return new String[]{"citizens"};
    }

    @Override
    public String permission() {
        return "wf.country.citizens";
    }

    @Override
    public Command[] children() {
        return new Command[]{
                new CountryCitizensInvite(),
                new CountryCitizensReject(),
                new CountryCitizensKick(),
                new CountryCitizensList(),
                new CountryCitizensRestrict(),
                new CountryCitizensUnrestrict()
        };
    }
}
