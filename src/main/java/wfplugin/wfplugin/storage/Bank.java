package wfplugin.wfplugin.storage;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;
import wfplugin.wfplugin.WFPlugin;

import java.util.HashMap;

public class Bank {
    public HashMap<String, Integer> players = new HashMap<>();

    public int get(Player player) {
        return get(player.getName());
    }

    public int get(CommandSource player) {
        return get(player.getName());
    }

    public int get(String player) {
        return WFPlugin.bank.players.get(player);
    }

    public int add(CommandSource player, int i) {
        return add(player.getName(), i);
    }

    public int add(String player, int i) {
        int[] result = {0};
        players.compute(player, (s, integer) -> {
            if (integer == null)
                integer = 0;
            result[0] = integer + i;
            return result[0];
        });
        WFPlugin.flushConfigs();
        return result[0];
    }

    public int set(CommandSource player, int i) {
        return set(player.getName(), i);
    }

    public int set(String player, int i) {
        players.put(player, i);
        WFPlugin.flushConfigs();
        return i;
    }

    public int pay(CommandSource sender, Player consumer, int amount) {
        return pay(sender.getName(), consumer.getName(), amount);
    }

    public int pay(String sender, String consumer, int amount) {
        int[] transition = {0};
        if (amount <= 0)
            return 0;

        WFPlugin.bank.players.compute(sender, (s, balance) -> {
            if (balance == null)
                balance = 0;
            transition[0] = amount;
            if (balance < amount)
                transition[0] = balance;

            return balance - transition[0];
        });
        WFPlugin.bank.players.compute(consumer, (s, balance) -> {
            if (balance == null)
                balance = 0;
            return balance + transition[0];
        });

        WFPlugin.flushConfigs();
        return transition[0];
    }

    public boolean tryDeduct(CommandSource player, int amount) {
        return tryDeduct(player.getName(), amount);
    }

    public boolean tryDeduct(String player, int amount) {
        int i = get(player);
        if (amount <= i) {
            add(player, amount * -1);
            return true;
        }
        WFPlugin.flushConfigs();
        return false;
    }
}
