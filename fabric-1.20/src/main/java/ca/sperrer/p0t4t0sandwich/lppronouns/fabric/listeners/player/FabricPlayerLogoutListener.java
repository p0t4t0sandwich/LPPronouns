package ca.sperrer.p0t4t0sandwich.lppronouns.fabric.listeners.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.listeners.player.PlayerLogoutListener;
import ca.sperrer.p0t4t0sandwich.lppronouns.fabric.player.FabricPronounPlayer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

/**
 * Listens for player logouts and removes them from the cache.
 */
public class FabricPlayerLogoutListener implements ServerPlayConnectionEvents.Disconnect, PlayerLogoutListener {
    /**
     * Called when a player logs out.
     * @param handler The player's network handler
     * @param server The server
     */
    @Override
    public void onPlayDisconnect(ServerPlayNetworkHandler handler, MinecraftServer server) {
        // Pass PronounPlayer to helper function
        pronounPlayerLogout(new FabricPronounPlayer(handler.getPlayer()));
    }
}
