package ca.sperrer.p0t4t0sandwich.lppronouns.velocity.listeners.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.listeners.player.PlayerLoginListener;
import ca.sperrer.p0t4t0sandwich.lppronouns.velocity.player.VelocityPronounPlayer;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.LoginEvent;

public class VelocityPlayerLoginListener implements PlayerLoginListener {
    @Subscribe
    public void onPlayerLogin(LoginEvent event) {
        pronounPlayerLogin(new VelocityPronounPlayer(event.getPlayer()));
    }
}
