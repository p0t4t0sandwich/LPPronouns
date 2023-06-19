package ca.sperrer.p0t4t0sandwich.lppronouns.fabric.listeners.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.listeners.player.PlayerLoginListener;
import ca.sperrer.p0t4t0sandwich.lppronouns.fabric.player.FabricPronounPlayer;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

/**
 * Listens for player logins and refreshes their pronouns.
 */
public class FabricPlayerLoginListener implements ServerPlayConnectionEvents.Join, PlayerLoginListener {
    /**
     * Called when a player logs in and refreshes their pronouns.
     * @param handler The handler.
     * @param sender The sender.
     * @param server The server.
     */
    @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        pronounPlayerLogin(new FabricPronounPlayer(handler.player));
    }
}
