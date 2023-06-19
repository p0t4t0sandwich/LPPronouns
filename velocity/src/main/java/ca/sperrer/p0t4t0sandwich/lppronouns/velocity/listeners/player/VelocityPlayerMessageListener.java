package ca.sperrer.p0t4t0sandwich.lppronouns.velocity.listeners.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.listeners.player.PlayerMessageListener;
import ca.sperrer.p0t4t0sandwich.lppronouns.velocity.player.VelocityPronounPlayer;
import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.proxy.Player;

/**
 * Listens for player messages and formats them.
 */
public class VelocityPlayerMessageListener implements PlayerMessageListener {
    /**
     * Called when a player sends a message, and sends it to the message relay.
     * @param event The player message event
     */
    @Subscribe(order = PostOrder.FIRST)
    public void onPlayerMessage(PlayerChatEvent event) {
        if (LPPronouns.cancelChat) {
            event.setResult(PlayerChatEvent.ChatResult.denied());
            // Get player, message and server
            Player player = event.getPlayer();
            String message = event.getMessage();
            if (!player.getCurrentServer().isPresent()) {
                return;
            }
            String server = player.getCurrentServer().get().getServerInfo().getName();

            // Send message to message relay
            pronounPlayerMessage(new VelocityPronounPlayer(player), server, message);
        }
    }
}
