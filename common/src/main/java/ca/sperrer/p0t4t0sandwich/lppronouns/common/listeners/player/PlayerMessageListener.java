package ca.sperrer.p0t4t0sandwich.lppronouns.common.listeners.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.player.PronounPlayer;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.relay.MessageRelay;

import static ca.sperrer.p0t4t0sandwich.lppronouns.common.Utils.runTaskAsync;

/**
 * Listens for player messages and sends them to the message relay.
 */
public interface PlayerMessageListener {
    /**
     * Called when a player sends a message, and sends it to the message relay.
     * @param pronounPlayer The player
     * @param message The message
     */
    default void pronounPlayerMessage(PronounPlayer pronounPlayer, String message) {
        runTaskAsync(() -> {
            try {
                MessageRelay relay = MessageRelay.getInstance();
                // Send message through relay
                relay.sendMessage(pronounPlayer, message);
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }

    /**
     * Called when a player sends a message, and sends it to the message relay.
     * @param pronounPlayer The player
     * @param message The message
     */
    default void pronounPlayerMessage(PronounPlayer pronounPlayer, String server, String message) {
        runTaskAsync(() -> {
            try {
                MessageRelay relay = MessageRelay.getInstance();
                // Send message through relay
                relay.sendMessage(pronounPlayer, server, message);
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
