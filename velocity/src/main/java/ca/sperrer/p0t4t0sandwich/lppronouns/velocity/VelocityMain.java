package ca.sperrer.p0t4t0sandwich.lppronouns.velocity;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import ca.sperrer.p0t4t0sandwich.lppronouns.velocity.commands.VelocityPronounsCommand;
import ca.sperrer.p0t4t0sandwich.lppronouns.velocity.listeners.player.VelocityPlayerLoginListener;
import ca.sperrer.p0t4t0sandwich.lppronouns.velocity.listeners.player.VelocityPlayerMessageListener;
import com.google.inject.Inject;
import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

@Plugin(
        id = "lppronouns",
        name = "LPPronouns",
        version = "1.0.3",
        authors = "p0t4t0sandwich",
        description = "A simple, cross API plugin that allows players to set their pronouns.",
        url = "https://github.com/p0t4t0sandwich/LPPronouns",
        dependencies = {
                @Dependency(id = "luckperms")
        }
)
public class VelocityMain {
    public LPPronouns lpPronouns;

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
        lpPronouns = new LPPronouns("plugins", getLogger());
        lpPronouns.start();

        // Register event listener
        EventManager eventManager = server.getEventManager();
        eventManager.register(this, new VelocityPlayerLoginListener());
        eventManager.register(this, new VelocityPlayerMessageListener());

        // Register commands
        server.getCommandManager().register("pronouns", new VelocityPronounsCommand());

        // Plugin enable message
        this.logger.info("LPPronouns has been enabled!");
    }
}
