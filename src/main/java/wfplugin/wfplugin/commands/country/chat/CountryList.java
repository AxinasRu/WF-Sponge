package wfplugin.wfplugin.commands.country.chat;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;

import java.util.ArrayList;

public class CountryList extends Command {
    @Override
    public String[] names() {
        return new String[]{"list"};
    }

    @Override
    public String permission() {
        return "wf.country.list";
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{};
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            ArrayList<Text> countries = new ArrayList<>(WFPlugin.countries.countries.size());

            WFPlugin.countries.countries.forEach((country) -> {
                String command = "/c info " + country.name;
                countries.add(
                        Text.of(
                                Text
                                        .builder()
                                        .onClick(TextActions.runCommand(command))
                                        .append(Text.of(country.name))
                                        .build(),
                                Text
                                        .builder()
                                        .onHover(TextActions.showText(Text.of(country.citizens + "")))
                                        .onClick(TextActions.runCommand(command))
                                        .append(Text.of(" [" + country.citizens.size() + "]"))
                                        .build()
                        )
                );
            });

            src.sendMessage(Text.joinWith(Text.NEW_LINE, countries));
            return CommandResult.success();
        };
    }
}
