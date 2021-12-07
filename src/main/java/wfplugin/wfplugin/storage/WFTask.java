package wfplugin.wfplugin.storage;

import lombok.AllArgsConstructor;
import org.spongepowered.api.scheduler.Task;
import wfplugin.wfplugin.WFPlugin;
import wfplugin.wfplugin.storage.country.Country;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

import static wfplugin.wfplugin.WFPlugin.strings;

public class WFTask {
    public final TaskAction action;
    public long stopTime;
    public String sender, consumer;

    public WFTask(long stopTime, TaskAction action, String sender, String consumer) {
        this.action = action;
        this.stopTime = stopTime;
        this.sender = sender;
        this.consumer = consumer;
    }

    public WFTask activate() {
        long l = System.currentTimeMillis();
        if (stopTime <= l) apply();
        else Task.builder().delay(stopTime - l, TimeUnit.MILLISECONDS).execute(this::apply).submit(WFPlugin.plugin);
        return this;
    }

    public void apply() {
        action.action.accept(sender, consumer);
        WFPlugin.tasks.remove(this);
    }

    @AllArgsConstructor
    public enum TaskAction {
        WAR_DECLARE((senderName, consumerName) -> {
            Country sender = WFPlugin.countries.get(senderName);
            Country consumer = WFPlugin.countries.get(consumerName);
            if (sender == null || consumer == null)
                return;
            if (sender.wars == null)
                sender.wars = new ArrayList<>();
            if (consumer.wars == null)
                consumer.wars = new ArrayList<>();
            sender.wars.add(consumerName);
            consumer.wars.add(senderName);
            sender.sendToAll(strings.declaredWar(senderName, consumerName));
            consumer.sendToAll(strings.declaredWar(senderName, consumerName));
        });
        public final BiConsumer<String, String> action;
    }
}
