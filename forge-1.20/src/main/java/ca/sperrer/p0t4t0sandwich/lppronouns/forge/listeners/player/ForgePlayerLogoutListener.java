package ca.sperrer.p0t4t0sandwich.lppronouns.forge.listeners.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.listeners.player.PlayerLogoutListener;
import ca.sperrer.p0t4t0sandwich.lppronouns.forge.player.ForgePronounPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Listens for player logouts and removes them from the cache.
 */
public class ForgePlayerLogoutListener implements PlayerLogoutListener {
    /**
     * Called when a player logs out.
     * @param event The player logout event
     */
    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        // Pass PronounPlayer to helper function
        pronounPlayerLogout(new ForgePronounPlayer(event.getEntity()));
    }
}
