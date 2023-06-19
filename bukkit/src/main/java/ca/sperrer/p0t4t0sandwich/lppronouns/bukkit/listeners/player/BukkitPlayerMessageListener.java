package ca.sperrer.p0t4t0sandwich.lppronouns.bukkit.listeners.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.bukkit.player.BukkitPronounPlayer;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.listeners.player.PlayerMessageListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Listens for player messages and formats them.
 */
public class BukkitPlayerMessageListener implements Listener, PlayerMessageListener {
    /**
     * Called when a player sends a message.
     * @param event The event.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMessage(AsyncPlayerChatEvent event) {
        if (LPPronouns.cancelChat) {
            event.setCancelled(true);
            pronounPlayerMessage(new BukkitPronounPlayer(event.getPlayer()), event.getMessage());
        }
    }
}
