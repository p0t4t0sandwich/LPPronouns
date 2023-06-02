package ca.sperrer.p0t4t0sandwich.lppronouns.common.pronouns;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.PlayerInstance;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.storage.Database;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.node.types.SuffixNode;

import java.util.HashMap;
import java.util.UUID;

public abstract class PronounsData {
    final Database db;
    private final LuckPerms luckPerms;
    private final HashMap<String, String> pronounsConfig;

    /**
     * Constructor for the PronounsData class
     * @param database The database
     * @param pronounsConfig The pronouns config
     */
    public PronounsData(Database database, HashMap<String, String> pronounsConfig) {
        this.db = database;
        this.pronounsConfig = pronounsConfig;
        this.luckPerms = LuckPermsProvider.get();
    }

    /**
     * Get the pronouns of a player from the database.
     * @param player The player to get the pronouns of.
     * @return The pronouns of the player.
     */
    public abstract String dbGetPronouns(PlayerInstance player);

    /**
     * Get the pronouns of a player.
     * @param player The player to get the pronouns of.
     * @return The pronouns of the player.
     */
    public String getPronouns(PlayerInstance player) {
        return this.dbGetPronouns(player);
    }

    /**
     * Set the pronouns of a player in the database.
     * @param player The player to set the pronouns of.
     * @param pronouns The pronouns to set.
     */
    public abstract void dbSetPronouns(PlayerInstance player, String pronouns);

    /**
     * Set the pronouns of a player.
     * @param player The player to set the pronouns of.
     * @param pronouns The pronouns to set.
     */
    public void setPronouns(PlayerInstance player, String pronouns) {
        this.dbSetPronouns(player, pronouns);
        UUID playerUuid = player.getUUID();

        if (!pronouns.isEmpty()) {
            // Get mapped pronouns from config
            String mapped_pronouns = pronounsConfig.getOrDefault(pronouns, "");

            // Update the player's suffix in LuckPerms
            SuffixNode node = SuffixNode.builder(mapped_pronouns, 1).build();
            luckPerms.getUserManager().modifyUser(playerUuid, user -> user.data().add(node));
        }
    }

    /**
     * Remove the pronouns of a player.
     * @param player The player to remove the pronouns of.
     */
    public void deletePronouns(PlayerInstance player) {
        UUID playerUuid = player.getUUID();

        // Get the player's pronouns from the database
        String pronouns = getPronouns(player);
        String mapped_pronouns = pronounsConfig.getOrDefault(pronouns, "");

        if (!mapped_pronouns.isEmpty()) {
            // Update the player's suffix in LuckPerms
            SuffixNode node = SuffixNode.builder(mapped_pronouns, 1).build();
            luckPerms.getUserManager().modifyUser(playerUuid, user -> user.data().remove(node));

            // Update the player's suffix in the database
            setPronouns(player, "");
        }
    }

    /**
     * Refresh the pronouns of a player.
     * @param player The player to refresh the pronouns of.
     */
    public void refreshPronouns(PlayerInstance player) {
        // Get the player's pronouns from the database
        String pronouns = getPronouns(player);

        // Get mapped pronouns from config
        String mapped_pronouns = pronounsConfig.getOrDefault(pronouns, "");

        if (!mapped_pronouns.isEmpty()) {
            // Update the player's suffix in LuckPerms
            SuffixNode node = SuffixNode.builder(mapped_pronouns, 1).build();
            luckPerms.getUserManager().modifyUser(player.getUUID(), user -> user.data().add(node));
        }
    }
}
