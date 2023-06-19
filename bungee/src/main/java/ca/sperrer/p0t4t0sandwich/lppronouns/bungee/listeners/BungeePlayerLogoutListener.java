package ca.sperrer.p0t4t0sandwich.lppronouns.bungee.listeners;

import ca.sperrer.p0t4t0sandwich.lppronouns.bungee.player.BungeePronounPlayer;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.listeners.player.PlayerLogoutListener;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Listens for player logouts and removes them from the cache.
 */
public class BungeePlayerLogoutListener implements Listener, PlayerLogoutListener {
    /**
     * Called when a player logs out.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerLogout(PlayerDisconnectEvent event) {
        // Pass PronounPlayer to helper function
        pronounPlayerLogout(new BungeePronounPlayer(event.getPlayer()));
    }
}
