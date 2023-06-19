package ca.sperrer.p0t4t0sandwich.lppronouns.bukkit.listeners.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.bukkit.player.BukkitPronounPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Listens for player messages and formats them.
 */
public class BukkitPlayerMessageListener implements Listener {
    /**
     * Called when a player sends a message.
     * @param event The event.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMessage(AsyncPlayerChatEvent event) {
        // TODO: Get weather or not the event should be cancelled
        event.setCancelled(true);
    }
}
