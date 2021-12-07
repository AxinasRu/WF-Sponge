package wfplugin.wfplugin.storage.log;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Log {
    private final ArrayList<LogElement> log = new ArrayList<>();

    public void add(LogElement logElement) {
        log.add(logElement);
    }

    public Stream<LogElement> getByPlayerStream(String player) {
        return log.stream().filter(logElement -> logElement.executor.equals(player));
    }

    public List<LogElement> getByPlayer(String player) {
        return log.stream().filter(logElement -> logElement.executor.equals(player)).collect(Collectors.toList());
    }
}
