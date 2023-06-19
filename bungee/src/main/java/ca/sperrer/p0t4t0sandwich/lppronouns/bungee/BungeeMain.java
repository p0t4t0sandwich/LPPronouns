package ca.sperrer.p0t4t0sandwich.lppronouns.bungee;

import ca.sperrer.p0t4t0sandwich.lppronouns.bungee.commands.BungeePronounsCommand;
import ca.sperrer.p0t4t0sandwich.lppronouns.bungee.listeners.BungeePlayerLoginListener;
import ca.sperrer.p0t4t0sandwich.lppronouns.bungee.listeners.BungeePlayerLogoutListener;
import ca.sperrer.p0t4t0sandwich.lppronouns.bungee.listeners.BungeePlayerMessageListener;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

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
        PluginManager pluginManager = getProxy().getPluginManager();
        pluginManager.registerListener(this, new BungeePlayerLoginListener());
        pluginManager.registerListener(this, new BungeePlayerLogoutListener());
        pluginManager.registerListener(this, new BungeePlayerMessageListener());

        // Register commands
        pluginManager.registerCommand(this, new BungeePronounsCommand());

        // Plugin enable message
        getLogger().info("LPPronouns has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin disable message
        getLogger().info("LPPronouns has been disabled!");
    }
}
