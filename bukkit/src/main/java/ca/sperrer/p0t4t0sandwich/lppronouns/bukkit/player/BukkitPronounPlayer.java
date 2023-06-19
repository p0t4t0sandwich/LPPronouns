package ca.sperrer.p0t4t0sandwich.lppronouns.bukkit.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.player.PronounPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Abstracts a Bukkit player to a PronounPlayer.
 */
public class BukkitPronounPlayer implements PronounPlayer {
    private final Player player;
    private String serverName;

    /**
     * Constructor.
     * @param player The Bukkit player.
     */
    public BukkitPronounPlayer(Player player) {
        this.player = player;
        this.serverName = "";
    }

    /**
     * @inheritDoc
     */
    @Override
    public UUID getUUID() {
        return player.getUniqueId();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getName() {
        return player.getName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDisplayName() {
        return player.getDisplayName();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getServerName() {
        return serverName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void sendMessage(String message) {
        player.sendMessage(message);
    }
}
