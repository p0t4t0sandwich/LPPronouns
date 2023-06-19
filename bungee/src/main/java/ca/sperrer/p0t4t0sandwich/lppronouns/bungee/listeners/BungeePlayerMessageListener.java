package ca.sperrer.p0t4t0sandwich.lppronouns.bungee.listeners;

import ca.sperrer.p0t4t0sandwich.lppronouns.bungee.player.BungeePronounPlayer;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.listeners.player.PlayerMessageListener;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

/**
 * Listens for player messages and formats them.
 */
public class BungeePlayerMessageListener implements Listener, PlayerMessageListener {
    /**
     * Called when a player sends a message.
     * @param event The event.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMessage(ChatEvent event) {
        if (LPPronouns.cancelChat) {
            event.setCancelled(true);
            // If cancelled, or is a command, ignore
            if (event.isCancelled()
                    || event.isCommand()
                    || event.isProxyCommand()
                    // Check if sender is a player
                    || !(event.getSender() instanceof ProxiedPlayer)) {
                return;
            }

            // Get player, server and message
            ProxiedPlayer player = (ProxiedPlayer) event.getSender();
            String server = player.getServer().getInfo().getName();
            String message = event.getMessage();

            // Send message to message relay
            pronounPlayerMessage(new BungeePronounPlayer(player), server, message);
        }
    }
}
