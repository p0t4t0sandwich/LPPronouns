package ca.sperrer.p0t4t0sandwich.lppronouns.common.listeners.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.player.PronounPlayer;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.relay.MessageRelay;

import static ca.sperrer.p0t4t0sandwich.lppronouns.common.Utils.runTaskAsync;

/**
 * Listens for player logins and refreshes the pronouns.
 */
public interface PlayerLoginListener {
    /**
     * Called when a player logs in, and sends it to the message relay.
     * @param pronounPlayer The player.
     */
    default void pronounPlayerLogin(PronounPlayer pronounPlayer) {
        runTaskAsync(() -> {
            try {
                MessageRelay relay = MessageRelay.getInstance();

                // Add the TaterPlayer to the cache
                if (relay != null) {
                    relay.setTaterPlayerInCache(pronounPlayer.getUUID(), pronounPlayer);
                }

                // Refresh the pronouns
                LPPronouns.getPronounsData().refreshPronouns(pronounPlayer);
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
