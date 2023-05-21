package ca.sperrer.p0t4t0sandwich.lppronouns.velocity;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.PlayerInstance;
import com.velocitypowered.api.proxy.Player;

public class VelocityUtils {
    /**
     * Maps a Bukkit Player to a PlayerInstance.
     * @param player Bukkit Player
     * @return PlayerInstance
     */
    public static PlayerInstance mapPlayer(Player player) {
        return new PlayerInstance(player.getUsername(), player.getUniqueId());
    }
}
