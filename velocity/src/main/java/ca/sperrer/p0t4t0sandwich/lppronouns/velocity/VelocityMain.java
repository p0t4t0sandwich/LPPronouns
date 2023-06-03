package ca.sperrer.p0t4t0sandwich.lppronouns.velocity;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import ca.sperrer.p0t4t0sandwich.lppronouns.velocity.commands.PronounsCommand;
import ca.sperrer.p0t4t0sandwich.lppronouns.velocity.listeners.VelocityEventListener;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

@Plugin(
        id = "lppronouns",
        name = "LPPronouns",
        version = "1.0.1"
)
public class VelocityMain {
    public LPPronouns LPPronouns;

    @Inject
    private ProxyServer server;

    @Inject
    private Logger logger;

    // Get logger
    public Logger getLogger() {
        return this.logger;
    }

    // Get server type
    public String getServerType() {
        return "Velocity";
    }

    // Singleton instance
    private static VelocityMain instance;
    public static VelocityMain getInstance() {
        return instance;
    }

    public ProxyServer getServer() {
        return this.server;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        // Singleton instance
        instance = this;

        this.logger.info("LPPronouns is running on " + getServerType() + ".");

        // Start LPPronouns
        LPPronouns = new LPPronouns("plugins", getLogger());
        LPPronouns.start();

        // Register event listener
        server.getEventManager().register(this, new VelocityEventListener());

        // Register commands
        server.getCommandManager().register("pronouns", new PronounsCommand());

        // Plugin enable message
        this.logger.info("LPPronouns has been enabled!");
    }
}
