package ca.sperrer.p0t4t0sandwich.lppronouns.bungee;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.PlayerInstance;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeeUtils {
    /**
     * Maps a Bungee Player to a PlayerInstance.
     * @param player Bungee Player
     * @return PlayerInstance
     */
    public static PlayerInstance mapPlayer(ProxiedPlayer player) {
        return new PlayerInstance(player.getName(), player.getUniqueId());
    }
}
