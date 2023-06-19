package ca.sperrer.p0t4t0sandwich.lppronouns.common.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.hooks.LuckPermsHook;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.placeholder.PlaceholderParser;

import java.util.UUID;

public interface PronounPlayer {
    /**
     * Get the UUID of the player
     * @return The UUID of the player
     */
    UUID getUUID();

    /**
     * Get the name of the player
     * @return The name of the player
     */
    String getName();

    /**
     * Get the display name of the player
     * @return The display name of the player
     */
    String getDisplayName();

    /**
     * Send a message to the player
     * @param message The message to send
     */
    void sendMessage(String message);

    /**
     * Get the prefix of the player
     * @return The prefix of the player
     */
    default String getPrefix() {
        LuckPermsHook luckPermsHook = LuckPermsHook.getInstance();
        return (luckPermsHook != null && luckPermsHook.getPrefix(this) != null) ? luckPermsHook.getPrefix(this) : "";
    }

    /**
     * Get the suffix of the player
     * @return The suffix of the player
     */
    default String getSuffix() {
        LuckPermsHook luckPermsHook = LuckPermsHook.getInstance();
        return (luckPermsHook != null && luckPermsHook.getSuffix(this) != null) ? luckPermsHook.getSuffix(this) : "";
    }

    default PlaceholderParser parsePlaceholders(String input) {
        return new PlaceholderParser(input)
                .parseString("player", this.getName())
                .parseString("displayname", this.getDisplayName())
                .parseString("prefix", this.getPrefix())
                .parseString("suffix", this.getSuffix())
                .parseSectionSign();
    }
}
