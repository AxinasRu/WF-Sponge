package wfplugin.wfplugin.storage.country;

import lombok.AllArgsConstructor;
import org.spongepowered.api.text.format.TextColor;

@AllArgsConstructor
public class CountryGroup {
    public String id;
    public String name;
    public String permissions;
    public int index;
    public TextColor color;
}
