package wfplugin.wfplugin.commands.country.plot;

import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.commands.Command;
import wfplugin.wfplugin.storage.country.Country;

import java.util.stream.Collectors;

public class CountryPlotList extends Command {
    @Override
    public String[] names() {
        return new String[]{"list"};
    }

    @Override
    public String permission() {
        return "wf.country.plot.group.list";
    }

    @Override
    public CommandElement[] args() {
        return new CommandElement[]{};
    }

    @Override
    public CommandExecutor executor() {
        return (src, args) -> {
            Country country = WFPlugin.countries.getByCitizen(src.getName());

            src.sendMessage(
                    Text.of(
                            "\u041F\u043B\u043E\u0442\u044B: ",
                            Text.joinWith(
                                    Text.of(TextColors.WHITE, ", "),
                                    country
                                            .plots
                                            .stream()
                                            .map(plot -> {
                                                TextColor color = TextColors.RED;
                                                if (country.groups.hasRights(src.getName(), plot.group))
                                                    color = TextColors.GREEN;
                                                return Text.of(color, plot.name, " (", plot.id, ")");
                                            })
                                            .collect(Collectors.toList())
                            )
                    )
            );

            return CommandResult.success();
        };
    }
}
