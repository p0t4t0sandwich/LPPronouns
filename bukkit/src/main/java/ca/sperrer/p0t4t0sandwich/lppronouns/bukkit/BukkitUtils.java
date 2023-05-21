package ca.sperrer.p0t4t0sandwich.lppronouns.bukkit;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.PlayerInstance;
import org.bukkit.entity.Player;

public class BukkitUtils {
    /**
     * Maps a Bukkit Player to a PlayerInstance.
     * @param player Bukkit Player
     * @return PlayerInstance
     */
    public static PlayerInstance mapPlayer(Player player) {
        return new PlayerInstance(player.getName(), player.getUniqueId());
    }
}
