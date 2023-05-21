package ca.sperrer.p0t4t0sandwich.lppronouns.bungee;

import ca.sperrer.p0t4t0sandwich.lppronouns.bungee.commands.PronounsCommand;
import ca.sperrer.p0t4t0sandwich.lppronouns.bungee.listeners.BungeeEventListener;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeMain extends Plugin {
    public LPPronouns LPPronouns;

    // Get server type
    public String getServerType() {
        return "BungeeCord";
    }

    // Singleton instance
    private static BungeeMain instance;
    public static BungeeMain getInstance() {
        return instance;
    }
    @Override
    public void onEnable() {
        // Singleton instance
        instance = this;

        getLogger().info("LPPronouns is running on " + getServerType() + ".");

        // Start LPPronouns
        LPPronouns = new LPPronouns("plugins", getLogger());
        LPPronouns.start();

        // Register event listener
        getProxy().getPluginManager().registerListener(this, new BungeeEventListener());

        // Register commands
        getProxy().getPluginManager().registerCommand(this, new PronounsCommand());

        // Plugin enable message
        getLogger().info("LPPronouns has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin disable message
        getLogger().info("LPPronouns has been disabled!");
    }
}
