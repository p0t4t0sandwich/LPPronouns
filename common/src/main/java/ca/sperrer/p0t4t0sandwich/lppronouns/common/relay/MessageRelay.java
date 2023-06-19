package ca.sperrer.p0t4t0sandwich.lppronouns.common.relay;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.placeholder.PlaceholderParser;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.player.PronounPlayer;

import java.util.HashMap;
import java.util.UUID;

/**
 * Class for relaying messages to all players.
 */
public class MessageRelay {
    /**
     * Properties of the MessageRelay class.
     * singleton: The singleton instance of the MessageRelay class
     * pronounPlayerCache: A cache of TaterPlayer objects
     */
    private static MessageRelay singleton = null;
    private final HashMap<UUID, PronounPlayer> pronounPlayerCache = new HashMap<>();
    private final String formatting;

    /**
     * Constructor for the MessageRelay class.
     */
    public MessageRelay(String formatting) {
        singleton = this;
        this.formatting = formatting;
    }

    /**
     * Getter for the PronounPlayer cache.
     * @param uuid The UUID of the player
     * @return The PronounPlayer cache
     */
    public PronounPlayer getTaterPlayerFromCache(UUID uuid) {
        return this.pronounPlayerCache.get(uuid);
    }

    /**
     * Setter for the PronounPlayer cache.
     * @param uuid The UUID of the player
     * @param pronounPlayer The PronounPlayer object
     */
    public void setTaterPlayerInCache(UUID uuid, PronounPlayer pronounPlayer) {
        this.pronounPlayerCache.put(uuid, pronounPlayer);
    }

    /**
     * Removes a PronounPlayer object from the cache.
     * @param uuid The UUID of the player
     */
    public void removeTaterPlayerFromCache(UUID uuid) {
        this.pronounPlayerCache.remove(uuid);
    }

    /**
     * Getter for the singleton instance of the MessageRelay class.
     * @return The singleton instance
     */
    public static MessageRelay getInstance() {
        return singleton;
    }

    /**
     * Relays a message to all players.
     * @param player The player
     * @param message The message
     */
    public void sendMessage(PronounPlayer player, String message) {
        // Message formatting
        String formattedMessage = player.parsePlaceholders(this.formatting).parseString("message", message).getResult();
        LPPronouns.useLogger(PlaceholderParser.stripSectionSign(formattedMessage));

        // Relay message to each PronounPlayer in the cache
        for (PronounPlayer pronounPlayer : this.pronounPlayerCache.values()) {
            pronounPlayer.sendMessage(formattedMessage);
        }
    }

    /**
     * Relays a message to all players.
     * @param player The player
     * @param server The server
     * @param message The message
     */
    public void sendMessage(PronounPlayer player, String server, String message) {
        // Message formatting
        String formattedMessage = player.parsePlaceholders(this.formatting).parseString("message", message).getResult();
        LPPronouns.useLogger(PlaceholderParser.stripSectionSign(formattedMessage));

        // Relay message to each PronounPlayer in the cache
        for (PronounPlayer pronounPlayer : this.pronounPlayerCache.values()) {
            if (pronounPlayer.getServerName().equals(server)) {
                pronounPlayer.sendMessage(formattedMessage);
            }
        }
    }
}
