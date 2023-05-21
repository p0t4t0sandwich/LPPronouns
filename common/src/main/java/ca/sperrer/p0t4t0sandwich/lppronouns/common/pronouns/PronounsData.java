package ca.sperrer.p0t4t0sandwich.lppronouns.common.pronouns;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.PlayerInstance;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.storage.Database;
import dev.dejvokep.boostedyaml.YamlDocument;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.node.types.SuffixNode;

import java.util.UUID;

public abstract class PronounsData {
    final Database db;
    private LuckPerms luckPerms;
    private YamlDocument config;

    public PronounsData(Database database, YamlDocument config) {
        this.db = database;
        this.config = config;
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

        // Get mapped pronouns from config
        String mapped_pronouns = config.getString("pronouns." + pronouns);

        // Update the player's suffix in LuckPerms
        SuffixNode node = SuffixNode.builder(mapped_pronouns, 1).build();
        luckPerms.getUserManager().modifyUser(playerUuid, user -> user.data().add(node));
    }

    /**
     * Remove the pronouns of a player.
     * @param player The player to remove the pronouns of.
     */
    public void deletePronouns(PlayerInstance player) {
        UUID playerUuid = player.getUUID();

        // Get the player's pronouns from the database
        String pronouns = getPronouns(player);
        String mapped_pronouns = config.getString("pronouns." + pronouns);

        if (mapped_pronouns != null && !mapped_pronouns.isEmpty()) {
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
        String mapped_pronouns = config.getString("pronouns." + pronouns);

        if (mapped_pronouns != null && !mapped_pronouns.isEmpty()) {
            // Update the player's suffix in LuckPerms
            SuffixNode node = SuffixNode.builder(mapped_pronouns, 1).build();
            luckPerms.getUserManager().modifyUser(player.getUUID(), user -> user.data().add(node));
        }
    }
}
