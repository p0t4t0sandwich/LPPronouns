package ca.sperrer.p0t4t0sandwich.lppronouns.bungee.listeners;

import ca.sperrer.p0t4t0sandwich.lppronouns.bungee.BungeeMain;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import static ca.sperrer.p0t4t0sandwich.lppronouns.bungee.BungeeUtils.mapPlayer;
import static ca.sperrer.p0t4t0sandwich.lppronouns.common.Utils.runTaskAsync;

public class BungeeEventListener implements Listener {
    BungeeMain plugin = BungeeMain.getInstance();

    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        runTaskAsync(() -> {
            try {
                plugin.LPPronouns.pronounsData.refreshPronouns(mapPlayer(event.getPlayer()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
