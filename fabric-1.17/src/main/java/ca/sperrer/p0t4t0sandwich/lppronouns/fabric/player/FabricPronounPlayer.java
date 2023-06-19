package ca.sperrer.p0t4t0sandwich.lppronouns.fabric.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.player.PronounPlayer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

/**
 * Abstracts a Fabric player to a TaterPlayer.
 */
public class FabricPronounPlayer implements PronounPlayer {
    private final ServerPlayerEntity player;
    private String serverName;

    /**
     * Constructor.
     * @param player The Fabric player.
     */
    public FabricPronounPlayer(ServerPlayerEntity player) {
        this.player = player;
        this.serverName = "";
    }

    /**
     * @inheritDoc
     */
    @Override
    public java.util.UUID getUUID() {
        return player.getUuid();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getName() {
        return player.getName().getString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDisplayName() {
        return player.getDisplayName().getString();
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
        player.sendMessage(Text.of(message), false);
    }
}
