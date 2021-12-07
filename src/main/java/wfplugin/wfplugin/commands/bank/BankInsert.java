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
import wfplugin.wfplugin.commands.Command;

import static wfplugin.wfplugin.WFPlugin.bank;
import static wfplugin.wfplugin.WFPlugin.strings;
import static wfplugin.wfplugin.utils.GuiUtils.clearGuiItems;

public class BankInsert extends Command {
    @Override
    public String[] names() {
        return new String[]{"insert", "deposit", "put", "push"};
    }

    @Override
    public Text description() {
        return Text.of(strings.insertDescription);
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
            int amount = args.<Integer>getOne("amount").orElse(0);
            CarriedInventory<? extends Carrier> inventory = ((Player) src).getInventory();

            ItemType paperType = Sponge.getGame().getRegistry().getType(ItemType.class, "variedcommodities:money").orElseThrow(IllegalStateException::new);
            int papersMoney = clearGuiItems(paperType, amount / 100, inventory) * 100;

            ItemType coinType = Sponge.getGame().getRegistry().getType(ItemType.class, "variedcommodities:coin_gold").orElseThrow(IllegalStateException::new);
            int coinsMoney = clearGuiItems(coinType, amount - papersMoney, inventory);

            int toInsert = papersMoney + coinsMoney;
            bank.add(src, toInsert);
            src.sendMessage(strings.insertSuccess(toInsert));
            return CommandResult.success();
        };
    }

    @Override
    public String permission() {
        return "wf.bank.insert";
    }
}
