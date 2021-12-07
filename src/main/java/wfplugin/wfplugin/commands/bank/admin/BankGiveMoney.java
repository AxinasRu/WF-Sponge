package wfplugin.wfplugin.commands.bank.admin;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;
import wfplugin.wfplugin.storage.log.LogElement;
import wfplugin.wfplugin.storage.log.LogType;

import static wfplugin.wfplugin.WFPlugin.bank;
import static wfplugin.wfplugin.WFPlugin.strings;

public class BankGiveMoney extends Command {
    @Override
    public String[] names() {
        return new String[]{"givemoney"};
    }

    @Override
    public Text description() {
        return Text.of("Пополнить счёт игрока");
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{
                GenericArguments.firstParsing(
                        GenericArguments.player(Text.of("player")),
                        GenericArguments.string(Text.of("player"))
                ),
                GenericArguments.integer(Text.of("amount"))
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            int amount = args.<Integer>getOne("amount").get();
            Object argPlayer = args.getOne("player").get();

            String consumer = null;
            if (argPlayer instanceof String)
                consumer = (String) argPlayer;
            if (argPlayer instanceof Player)
                consumer = ((Player) argPlayer).getName();

            if (consumer == null)
                return CommandResult.empty();

            bank.add(consumer, amount);

            src.sendMessage(strings.giveMoneySuccessSrc(consumer, amount));
            Sponge.getServer().getPlayer(consumer).ifPresent(player -> player.sendMessage(strings.giveMoneySuccess(amount)));

            WFPlugin.log(new LogElement(LogType.GIVEMONEY, src.getName(), consumer + ", " + amount));
            return CommandResult.success();
        };
    }

    @Override
    public String permission() {
        return "wf.admin.bank.givemoney";
    }
}
