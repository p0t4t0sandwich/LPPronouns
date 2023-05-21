package ca.sperrer.p0t4t0sandwich.lppronouns.common;

import java.util.UUID;

public class PlayerInstance {
    /**
     * Class used to abstract the player data to be used in the DataSource class.
     * playerName: The player's name.
     * playerUUID: The player's UUID.
     * currentServer: The server the player is currently on.
     */
    private final String playerName;
    private final UUID playerUUID;

    /**
     * Constructor for the PlayerInstance class.
     * @param playerName The player's name.
     * @param playerUUID The player's UUID.
     */
    public PlayerInstance(String playerName, UUID playerUUID) {
        this.playerName = playerName;
        this.playerUUID = playerUUID;
    }

    /**
     * Get the player's name.
     * @return The player's name.
     */
    public String getName() {
        return this.playerName;
    }

    /**
     * Get the player's UUID.
     * @return The player's UUID.
     */
    public UUID getUUID() {
        return this.playerUUID;
    }
}
