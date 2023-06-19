package ca.sperrer.p0t4t0sandwich.lppronouns.common.hooks;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.player.PronounPlayer;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;

/**
 * A hook for LuckPerms
 * @see <a href="https://luckperms.net/">LuckPerms</a>
 */
public class LuckPermsHook {
    private final LuckPerms luckPerms;
    private static LuckPermsHook instance;

    /**
     * Create a new LuckPermsHook
     */
    public LuckPermsHook() {
        instance = this;
        this.luckPerms = LuckPermsProvider.get();
    }

    /**
     * Get the LuckPermsHook instance
     * @return The LuckPermsHook instance
     */
    public static LuckPermsHook getInstance() {
        return instance;
    }

    /**
     * Get the CachedMetaData for a player
     * @param pronounPlayer The player to get the CachedMetaData for
     * @return The CachedMetaData for the player
     */
    private CachedMetaData getMetaData(PronounPlayer pronounPlayer) {
        if (this.luckPerms == null) return null;
        User user = luckPerms.getUserManager().getUser(pronounPlayer.getUUID());
        return user != null ? user.getCachedData().getMetaData() : null;
    }

    /**
     * Get the prefix for a player
     * @param pronounPlayer The player to get the prefix for
     * @return The prefix for the player
     */
    public String getPrefix(PronounPlayer pronounPlayer) {
        CachedMetaData metaData = getMetaData(pronounPlayer);
        return metaData != null ? metaData.getPrefix() : "";
    }

    /**
     * Get the suffix for a player
     * @param pronounPlayer The player to get the suffix for
     * @return The suffix for the player
     */
    public String getSuffix(PronounPlayer pronounPlayer) {
        CachedMetaData metaData = getMetaData(pronounPlayer);
        return metaData != null ? metaData.getSuffix() : "";
    }
}
