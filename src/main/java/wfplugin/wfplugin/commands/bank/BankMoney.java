package wfplugin.wfplugin.commands.bank;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import wfplugin.wfplugin.commands.Command;

import java.util.Optional;

import static wfplugin.wfplugin.WFPlugin.bank;
import static wfplugin.wfplugin.WFPlugin.strings;

public class BankMoney extends Command {
    @Override
    public String[] names() {
        return new String[]{"money", "balance"};
    }

    @Override
    public Text description() {
        return Text.of("Посмотреть баланс пользователя");
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{
                GenericArguments.optional(GenericArguments.requiringPermission(
                        GenericArguments.firstParsing(
                                GenericArguments.player(Text.of("player")),
                                GenericArguments.string(Text.of("player"))
                        ),
                        "wfplugin.admin.bank.money")
                )
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            Optional<Object> argPlayer = args.getOne("player");

            String toCheck = src.getName();
            if (argPlayer.isPresent() && argPlayer.get() instanceof String)
                toCheck = (String) argPlayer.get();
            if (argPlayer.isPresent() && argPlayer.get() instanceof Player)
                toCheck = ((Player) argPlayer.get()).getName();

            int balance = bank.get(toCheck);
            if (argPlayer.isPresent())
                src.sendMessage(strings.moneySuccessSee(toCheck, balance));
            else
                src.sendMessage(strings.moneySuccess(balance));

            return CommandResult.success();
        };
    }

    @Override
    public String permission() {
        return "wf.bank.money";
    }
}
