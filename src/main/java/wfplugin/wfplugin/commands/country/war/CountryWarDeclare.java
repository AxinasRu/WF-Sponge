package wfplugin.wfplugin.commands.country.war;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;
import wfplugin.wfplugin.storage.WFTask;
import wfplugin.wfplugin.storage.country.Country;

import static wfplugin.wfplugin.WFPlugin.strings;
import static wfplugin.wfplugin.WFPlugin.tasks;
import static wfplugin.wfplugin.storage.WFTask.TaskAction.WAR_DECLARE;

public class CountryWarDeclare extends Command {
    @Override
    public String[] names() {
        return new String[]{"declare"};
    }

    @Override
    public String permission() {
        return "wf.country.declare";
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{
                GenericArguments.string(Text.of("country"))
        };
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {

            Country sender = WFPlugin.countries.getByCitizen(src.getName());
            Country consumer = WFPlugin.countries.get(args.<String>getOne("country").get());

            if (!sender.isMinister(src.getName()))
                src.sendMessage(strings.onlyMinisters());
            else if (consumer == null)
                src.sendMessage(strings.countryNotSelected());
            else if (sender.name.equals(consumer.name))
                src.sendMessage(Text.of("Sender equals Consumer"));
            else if (sender.wars.contains(consumer.name) || tasks.containsTask(sender.name, consumer.name))
                src.sendMessage(strings.alreadyDeclared());
            else {
                long stopTime = System.currentTimeMillis() + 30 * 60000;
                sender.sendToAll(strings.declaringWar(sender.name, consumer.name, stopTime));
                consumer.sendToAll(strings.declaringWar(sender.name, consumer.name, stopTime));
                WFPlugin.tasks.add(new WFTask(stopTime, WAR_DECLARE, sender.name, consumer.name).activate());
            }

            return CommandResult.success();
        };
    }
}
