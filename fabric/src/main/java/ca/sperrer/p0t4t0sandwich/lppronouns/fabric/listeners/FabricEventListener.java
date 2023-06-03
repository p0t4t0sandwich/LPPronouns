package ca.sperrer.p0t4t0sandwich.lppronouns.fabric.listeners;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import ca.sperrer.p0t4t0sandwich.lppronouns.fabric.FabricMain;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

import static ca.sperrer.p0t4t0sandwich.lppronouns.common.Utils.runTaskAsync;
import static ca.sperrer.p0t4t0sandwich.lppronouns.fabric.FabricUtils.mapPlayer;

public class FabricEventListener implements ServerPlayConnectionEvents.Join, ServerLifecycleEvents.ServerStarted {
    FabricMain mod = FabricMain.getInstance();

    @Override
    public void onServerStarted(MinecraftServer server) {
        try {
            // Start LPPronouns
            mod.LPPronouns = new LPPronouns("config", mod.logger);
            mod.LPPronouns.start();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }

    @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        runTaskAsync(() -> {
            try {
                mod.LPPronouns.pronounsData.refreshPronouns(mapPlayer(handler.getPlayer()));
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
