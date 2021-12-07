package wfplugin.wfplugin.commands.bank;

import wfplugin.wfplugin.commands.Command;
import wfplugin.wfplugin.commands.bank.admin.BankGiveMoney;
import wfplugin.wfplugin.commands.bank.admin.BankSetBalance;

public class BankCommand extends Command {
    @Override
    public String[] names() {
        return new String[]{"bank", "b"};
    }

    @Override
    public String permission() {
        return "wf.bank";
    }

    @Override
    public Command[] children() {
        return new Command[]{
                new BankMoney(),
                new BankPay(),
                new BankWithdraw(),
                new BankInsert(),
                new BankGiveMoney(),
                new BankSetBalance()
        };
    }
}
