package ca.sperrer.p0t4t0sandwich.lppronouns.forge.listeners.server;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import ca.sperrer.p0t4t0sandwich.lppronouns.forge.ForgeMain;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeServerStartingListener {
    ForgeMain plugin = ForgeMain.getInstance();

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        try {
            // Start LPPronouns
            plugin.lpPronouns = new LPPronouns("config", ForgeMain.logger);
            plugin.lpPronouns.start();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
