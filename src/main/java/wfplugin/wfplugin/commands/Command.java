package wfplugin.wfplugin.commands;

import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;

import java.util.Arrays;

public abstract class Command {
    public abstract String[] names();

    public String permission() {
        return null;
    }

    public Text description() {
        return null;
    }

    public CommandElement[] args() {
        return null;
    }

    public CommandExecutor executor() {
        return null;
    }

    public Command[] children() {
        return new Command[0];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{\"names\": \"" + Arrays.toString(names()) + "\", \"description\": \"" + description() + "\", \"args\": \"" + Arrays.toString(args()) + "\", \"permission\": \"" + permission() + "\", \"children\": [");
        for (Command child : children()) {
            s.append(child);
        }
        return s + "]}";
    }
}
