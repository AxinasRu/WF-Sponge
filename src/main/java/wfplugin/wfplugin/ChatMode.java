package wfplugin.wfplugin;

import lombok.AllArgsConstructor;
import org.spongepowered.api.text.Text;

@AllArgsConstructor
public enum ChatMode {
    LOCAL(WFPlugin.strings.localPrefix()),
    GLOBAL(WFPlugin.strings.globalPrefix()),
    COUNTRY(WFPlugin.strings.countryPrefix()),
    DEFAULT(Text.of());

    public final Text prefix;
}
