package wfplugin.wfplugin.commands.country;

import wfplugin.wfplugin.commands.Command;
import wfplugin.wfplugin.commands.country.chat.CountryBroadCast;
import wfplugin.wfplugin.commands.country.chat.CountryList;
import wfplugin.wfplugin.commands.country.citizen.CountryCitizens;
import wfplugin.wfplugin.commands.country.economy.CountryBalance;
import wfplugin.wfplugin.commands.country.economy.CountryDeposit;
import wfplugin.wfplugin.commands.country.economy.CountryWithdraw;
import wfplugin.wfplugin.commands.country.ministers.CountryMinister;
import wfplugin.wfplugin.commands.country.plot.CountryPlot;
import wfplugin.wfplugin.commands.country.war.CountryWar;

public class CountryCommand extends Command {
    @Override
    public String[] names() {
        return new String[]{"country", "c"};
    }

    @Override
    public String permission() {
        return "wf.country";
    }

    @Override
    public Command[] children() {
        return new Command[]{
                new CountryClaim(),
                new CountryUnClaim(),
                new CountryDisband(),
                new CountryInfo(),
                new CountryJoin(),
                new CountryReject(),
                new CountryLeave(),
                new CountryRegister(),
                new CountryRename(),
                new CountryResign(),
                new CountryWar(),
                new CountryMinister(),
                new CountryBalance(),
                new CountryDeposit(),
                new CountryWithdraw(),
                new CountryCitizens(),
                new CountryBroadCast(),
                new CountryInfo(),
                new CountryBuyBlocks(),
                new CountryPlot(),
                new CountryList()
        };
    }
}
