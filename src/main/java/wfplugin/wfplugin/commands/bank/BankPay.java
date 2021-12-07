package wfplugin.wfplugin.commands.bank;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import wfplugin.wfplugin.commands.Command;

import static wfplugin.wfplugin.WFPlugin.bank;
import static wfplugin.wfplugin.WFPlugin.strings;

public class BankPay extends Command {
    @Override
    public String[] names() {
        return new String[]{"pay"};
    }

    @Override
    public Text description() {
        return Text.of("Перевести деньги с баланса игрока другому пользователю");
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{
                GenericArguments.player(Text.of("player")),
                GenericArguments.integer(Text.of("amount"))
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            int amount = args.<Integer>getOne("amount").get();
            Player consumer = args.<Player>getOne("player").get();

            int i = bank.pay(src, consumer, amount);
            Text of = strings.paySuccess(src, consumer, i);
            src.sendMessage(of);
            consumer.sendMessage(of);

            return CommandResult.success();
        };
    }

    @Override
    public String permission() {
        return "wf.bank.pay";
    }
}
