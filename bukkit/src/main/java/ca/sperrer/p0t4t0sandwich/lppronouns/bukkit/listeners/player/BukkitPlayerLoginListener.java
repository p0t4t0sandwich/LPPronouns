package ca.sperrer.p0t4t0sandwich.lppronouns.bukkit.listeners.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.bukkit.player.BukkitPronounPlayer;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.listeners.player.PlayerLoginListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Listens for player logins and refreshes their pronouns.
 */
public class BukkitPlayerLoginListener implements Listener, PlayerLoginListener {
    /**
     * Called when a player logs in and refreshes their pronouns.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerLogin(PlayerJoinEvent event) {
        pronounPlayerLogin(new BukkitPronounPlayer(event.getPlayer()));
    }
}
