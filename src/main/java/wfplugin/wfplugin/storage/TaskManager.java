package wfplugin.wfplugin.storage;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;

public class TaskManager {
    public final ArrayList<WFTask> tasks = new ArrayList<>();

    public void add(WFTask task) {
        tasks.add(task);
    }

    public void remove(WFTask task) {
        tasks.remove(task);
    }

    public void activate() {
        ImmutableList.copyOf(tasks).forEach(WFTask::activate);
    }

    public boolean containsTask(String sender, String consumer) {
        for (WFTask task : tasks)
            if (task.sender.equals(sender) && task.consumer.equals(consumer))
                return true;
        return false;
    }
}
