package wfplugin.wfplugin.commands.bank;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.Carrier;
import org.spongepowered.api.item.inventory.type.CarriedInventory;
import org.spongepowered.api.text.Text;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;
import wfplugin.wfplugin.storage.log.LogElement;
import wfplugin.wfplugin.storage.log.LogType;

import static wfplugin.wfplugin.WFPlugin.log;
import static wfplugin.wfplugin.WFPlugin.strings;
import static wfplugin.wfplugin.utils.GuiUtils.addGuiItems;

public class BankWithdraw extends Command {
    @Override
    public String[] names() {
        return new String[]{"withdraw", "pull", "get"};
    }

    @Override
    public Text description() {
        return Text.of("Снять деньги со счёта");
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{
                GenericArguments.integer(Text.of("amount"))
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            Integer amount = args.<Integer>getOne("amount").get();
            CarriedInventory<? extends Carrier> inventory = ((Player) src).getInventory();
            WFPlugin.bank.players.compute(src.getName(), (s, balance) -> {
                if (balance == null)
                    balance = 0;
                int withdrawal = amount;
                if (balance < amount)
                    withdrawal = balance;

                int papers = withdrawal / 100;
                int coins = withdrawal % 100;

                ItemType paperType = Sponge.getGame().getRegistry().getType(ItemType.class, "variedcommodities:money").get();
                int papersResult = addGuiItems(paperType, strings.paperMoney(), strings.moneyLore(), papers, inventory);

                ItemType coinType = Sponge.getGame().getRegistry().getType(ItemType.class, "variedcommodities:coin_gold").get();
                int coinsResult = addGuiItems(coinType, strings.coinMoney(), strings.moneyLore(), coins, inventory);

                int result = papersResult * 100 + coinsResult;
                log(new LogElement(LogType.WITHDRAW, src.getName(), result + ""));
                src.sendMessage(strings.withdrawSuccess(result));
                return balance - result;
            });
            return CommandResult.success();
        };
    }

    @Override
    public String permission() {
        return "wf.bank.withdraw";
    }
}
