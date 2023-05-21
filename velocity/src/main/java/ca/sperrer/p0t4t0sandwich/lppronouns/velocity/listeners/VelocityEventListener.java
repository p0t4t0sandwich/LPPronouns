package ca.sperrer.p0t4t0sandwich.lppronouns.velocity.listeners;

import ca.sperrer.p0t4t0sandwich.lppronouns.velocity.VelocityMain;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.LoginEvent;

import static ca.sperrer.p0t4t0sandwich.lppronouns.common.Utils.runTaskAsync;
import static ca.sperrer.p0t4t0sandwich.lppronouns.velocity.VelocityUtils.mapPlayer;

public class VelocityEventListener {
    VelocityMain plugin = VelocityMain.getInstance();

    @Subscribe
    public void onPlayerLogin(LoginEvent event) {
        runTaskAsync(() -> {
            try {
                plugin.LPPronouns.pronounsData.refreshPronouns(mapPlayer(event.getPlayer()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
