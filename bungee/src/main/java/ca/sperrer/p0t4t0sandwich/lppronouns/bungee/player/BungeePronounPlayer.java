package ca.sperrer.p0t4t0sandwich.lppronouns.bungee.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.player.PronounPlayer;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

/**
 * Abstracts a BungeeCord player to a PronounPlayer.
 */
public class BungeePronounPlayer implements PronounPlayer {
    private final ProxiedPlayer player;
    private String serverName;

    /**
     * Constructor.
     * @param player The BungeeCord player.
     */
    public BungeePronounPlayer(ProxiedPlayer player) {
        this.player = player;
        if (player.getServer() != null) {
            this.serverName = player.getServer().getInfo().getName();
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
        player.sendMessage(new ComponentBuilder(message).create());
    }
}
