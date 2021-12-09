package wfplugin.wfplugin.storage;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class Mute {
    public long endTime;
    public String reason;
}
