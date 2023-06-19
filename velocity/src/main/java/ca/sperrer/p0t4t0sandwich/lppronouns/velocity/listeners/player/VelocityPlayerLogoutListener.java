package ca.sperrer.p0t4t0sandwich.lppronouns.velocity.listeners.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.listeners.player.PlayerLogoutListener;
import ca.sperrer.p0t4t0sandwich.lppronouns.velocity.player.VelocityPronounPlayer;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;

/**
 * Listens for player logouts and removes them from the cache.
 */
public class VelocityPlayerLogoutListener implements PlayerLogoutListener {
    /**
     * Called when a player logs out.
     * @param event The player logout event
     */
    @Subscribe
    public void onPlayerLogout(DisconnectEvent event) {
        // Pass PronounPlayer to helper function
        pronounPlayerLogout(new VelocityPronounPlayer(event.getPlayer()));
    }
}
