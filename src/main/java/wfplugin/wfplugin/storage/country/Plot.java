package wfplugin.wfplugin.storage.country;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import wfplugin.wfplugin.storage.Region2d;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class Plot {
    public String id;
    public String name;
    public Region2d region2d = null;
    public String group = "minister";
    public List<String> players = new ArrayList<>();
}
