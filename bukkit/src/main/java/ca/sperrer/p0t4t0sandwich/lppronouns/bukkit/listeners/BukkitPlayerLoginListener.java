package ca.sperrer.p0t4t0sandwich.lppronouns.bukkit.listeners;

import ca.sperrer.p0t4t0sandwich.lppronouns.bukkit.BukkitMain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static ca.sperrer.p0t4t0sandwich.lppronouns.bukkit.BukkitUtils.mapPlayer;
import static ca.sperrer.p0t4t0sandwich.lppronouns.common.Utils.runTaskAsync;

public class BukkitPlayerLoginListener implements Listener {
    BukkitMain plugin = BukkitMain.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        runTaskAsync(() -> {
            try {
                // Refresh pronouns
                plugin.LPPronouns.pronounsData.refreshPronouns(mapPlayer(event.getPlayer()));
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        });
    }
}
