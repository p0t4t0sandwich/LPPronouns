package ca.sperrer.p0t4t0sandwich.lppronouns.bukkit.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.player.PronounPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Abstracts a Bukkit player to a PronounPlayer.
 */
public class BukkitPronounPlayer implements PronounPlayer {
    private final Player player;

    /**
     * Constructor.
     * @param player The Bukkit player.
     */
    public BukkitPronounPlayer(Player player) {
        this.player = player;
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
    public void sendMessage(String message) {
        player.sendMessage(message);
    }
}
