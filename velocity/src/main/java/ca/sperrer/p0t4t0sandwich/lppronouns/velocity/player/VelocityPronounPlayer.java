package ca.sperrer.p0t4t0sandwich.lppronouns.velocity.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.player.PronounPlayer;
import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.text.Component;

import java.util.UUID;

/**
 * Abstracts a Velocity player to a PronounPlayer.
 */
public class VelocityPronounPlayer implements PronounPlayer {
    private final Player player;
    private String serverName;

    /**
     * Constructor.
     * @param player The Velocity player.
     */
    public VelocityPronounPlayer(Player player) {
        this.player = player;

        if (player.getCurrentServer().isPresent()) {
            this.serverName = player.getCurrentServer().get().getServerInfo().getName();
        } else {
            this.serverName = null;
        }
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
        return player.getUsername();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDisplayName() {
        return player.getUsername();
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
    public void setServerName(String server) {
        this.serverName = server;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void sendMessage(String message) {
        player.sendMessage(Component.text(message));
    }
}