package wfplugin.wfplugin.storage.log;

public class LogElement {
    public LogType type;
    public long time;
    public String executor;
    public String command;

    public LogElement(LogType type, long time, String executor, String command) {
        this.type = type;
        this.time = time;
        this.executor = executor;
        this.command = command;
    }

    public LogElement(LogType type, String executor, String command) {
        this.type = type;
        this.executor = executor;
        this.command = command;
        time = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "{" +
                "type=" + type +
                ", time=" + time +
                ", executor='" + executor + '\'' +
                ", command='" + command + '\'' +
                '}';
    }
}
