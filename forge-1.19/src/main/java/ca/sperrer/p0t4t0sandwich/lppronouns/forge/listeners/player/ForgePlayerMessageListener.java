package ca.sperrer.p0t4t0sandwich.lppronouns.forge.listeners.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.listeners.player.PlayerMessageListener;
import ca.sperrer.p0t4t0sandwich.lppronouns.forge.player.ForgePronounPlayer;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Listens for player messages and formats them.
 */
public class ForgePlayerMessageListener implements PlayerMessageListener {
    /**
     * Called when a player sends a message, and sends it to the message relay.
     * @param event The player message event
     */
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    void onPlayerMessage(ServerChatEvent event) {
        if (LPPronouns.cancelChat) {
            event.setCanceled(true);
            // Send message to message relay
            pronounPlayerMessage(new ForgePronounPlayer(event.getPlayer()), event.getMessage());
        }
    }
}
