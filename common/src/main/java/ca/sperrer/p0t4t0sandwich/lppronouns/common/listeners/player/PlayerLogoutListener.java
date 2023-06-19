package ca.sperrer.p0t4t0sandwich.lppronouns.common.listeners.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.player.PronounPlayer;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.relay.MessageRelay;

import static ca.sperrer.p0t4t0sandwich.lppronouns.common.Utils.runTaskAsync;

/**
 * Listens for player logouts and removes them from the cache.
 */
public interface PlayerLogoutListener {
    /**
     * Called when a player logs out, and removes them from the cache.
     * @param pronounPlayer The player.
     */
    default void pronounPlayerLogout(PronounPlayer pronounPlayer) {
        runTaskAsync(() -> {
            try {
                MessageRelay relay = MessageRelay.getInstance();

                if (relay != null) {
                    // Remove the PronounPlayer from the cache
                    relay.removeTaterPlayerFromCache(pronounPlayer.getUUID());
                }
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}