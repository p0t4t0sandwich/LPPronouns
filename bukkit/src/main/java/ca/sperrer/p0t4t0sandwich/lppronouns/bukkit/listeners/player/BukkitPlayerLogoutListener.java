package ca.sperrer.p0t4t0sandwich.lppronouns.bukkit.listeners.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.bukkit.player.BukkitPronounPlayer;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.listeners.player.PlayerLogoutListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Listens for player logouts and removes them from the cache.
 */
public class BukkitPlayerLogoutListener implements Listener, PlayerLogoutListener {
    /**
     * Called when a player logs out.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerLogout(PlayerQuitEvent event) {
        // Pass PronounPlayer to helper function
        pronounPlayerLogout(new BukkitPronounPlayer(event.getPlayer()));
    }
}
