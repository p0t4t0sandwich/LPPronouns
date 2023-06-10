package ca.sperrer.p0t4t0sandwich.lppronouns.api;

import java.util.UUID;

public interface PlayerInstance {
    /**
     * Class used to abstract the player data to be used in the DataSource class.
     * playerName: The player's name.
     * playerUUID: The player's UUID.
     */
    final String playerName = null;
    final UUID playerUUID = null;

    /**
     * Constructor for the PlayerInstance class.
     * @param playerName The player's name.
     * @param playerUUID The player's UUID.
     */

    /**
     * Get the player's name.
     * @return The player's name.
     */
    public default String getName() {
        return this.playerName;
    }

    /**
     * Get the player's UUID.
     * @return The player's UUID.
     */
    public default UUID getUUID() {
        return this.playerUUID;
    }
}
