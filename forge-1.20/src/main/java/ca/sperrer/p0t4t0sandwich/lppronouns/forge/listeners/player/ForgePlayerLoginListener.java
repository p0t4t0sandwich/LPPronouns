package ca.sperrer.p0t4t0sandwich.lppronouns.forge.listeners.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.listeners.player.PlayerLoginListener;
import ca.sperrer.p0t4t0sandwich.lppronouns.forge.player.ForgePronounPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Listens for player logins and refreshes their pronouns.
 */
public class ForgePlayerLoginListener implements PlayerLoginListener {
    /**
     * Called when a player logs in and refreshes their pronouns.
     * @param event The event.
     */
    @SubscribeEvent
    public void onPlayerConnect(PlayerEvent.PlayerLoggedInEvent event) {
        pronounPlayerLogin(new ForgePronounPlayer(event.getEntity()));
    }
}
