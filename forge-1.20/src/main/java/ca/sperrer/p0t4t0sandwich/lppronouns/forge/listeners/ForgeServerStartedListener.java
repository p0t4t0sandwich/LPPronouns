package ca.sperrer.p0t4t0sandwich.lppronouns.forge.listeners;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import ca.sperrer.p0t4t0sandwich.lppronouns.forge.ForgeMain;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeServerStartedListener {
    ForgeMain mod = ForgeMain.getInstance();

    @SubscribeEvent
    public void onServerStart(ServerStartingEvent event) {
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
