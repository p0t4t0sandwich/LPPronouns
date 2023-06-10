package ca.sperrer.p0t4t0sandwich.lppronouns.fabric.listeners;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import ca.sperrer.p0t4t0sandwich.lppronouns.fabric.FabricMain;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

public class FabricServerStartedListener implements ServerLifecycleEvents.ServerStarted {
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
}
