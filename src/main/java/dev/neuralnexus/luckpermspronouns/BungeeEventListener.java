package dev.neuralnexus.luckpermspronouns;

import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.scheduler.ScheduledTask;
import net.md_5.bungee.event.EventHandler;

import java.util.concurrent.TimeUnit;

public class BungeeEventListener implements Listener {
    private final LuckPermsPronouns plugin = LuckPermsPronouns.getInstance();

    @EventHandler
    public void onPlayerLoggedInEvent(PostLoginEvent event) {
        ScheduledTask scheduledTask = plugin.getProxy().getScheduler().schedule(plugin, () -> plugin.refreshPronouns(event.getPlayer()), 0, TimeUnit.SECONDS);
    }
}
