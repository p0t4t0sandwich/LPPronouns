package ca.sperrer.p0t4t0sandwich.lppronouns.bungee.listeners;

import ca.sperrer.p0t4t0sandwich.lppronouns.bungee.player.BungeePronounPlayer;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.listeners.player.PlayerLoginListener;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

/**
 * Listens for player logins and refreshes their pronouns.
 */
public class BungeePlayerLoginListener implements Listener, PlayerLoginListener {
    /**
     * Called when a player logs in and refreshes their pronouns.
     * @param event The event.
     */
    @EventHandler
    public void onPlayerLogin(PostLoginEvent event) {
        pronounPlayerLogin(new BungeePronounPlayer(event.getPlayer()));
    }
}
