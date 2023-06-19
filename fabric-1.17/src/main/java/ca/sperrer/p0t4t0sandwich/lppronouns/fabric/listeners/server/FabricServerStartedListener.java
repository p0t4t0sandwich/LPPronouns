package ca.sperrer.p0t4t0sandwich.lppronouns.fabric.listeners.server;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import ca.sperrer.p0t4t0sandwich.lppronouns.fabric.FabricMain;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

public class FabricServerStartedListener implements ServerLifecycleEvents.ServerStarted {
    FabricMain plugin = FabricMain.getInstance();

    @Override
    public void onServerStarted(MinecraftServer server) {
        try {
            // Start LPPronouns
            plugin.lpPronouns = new LPPronouns("config", plugin.logger);
            plugin.lpPronouns.start();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
