package wfplugin.wfplugin.storage.country;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class CountryGroup {
    public String id;
    public String name;
    public String permissions;
    public String color;
    public int index;
}
