package ca.sperrer.p0t4t0sandwich.lppronouns.bukkit;

import ca.sperrer.p0t4t0sandwich.lppronouns.bukkit.commands.PronounsCommand;
import ca.sperrer.p0t4t0sandwich.lppronouns.bukkit.listeners.BukkitEventListener;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import org.bukkit.plugin.java.JavaPlugin;

import static ca.sperrer.p0t4t0sandwich.lppronouns.common.Utils.*;

public class BukkitMain extends JavaPlugin {
    public LPPronouns LPPronouns;

    // Singleton instance
    private static BukkitMain instance;
    public static BukkitMain getInstance() {
        return instance;
    }

    public String getServerType() {
        if (isFolia()) {
            return "Folia";
        } else if (isPaper()) {
            return "Paper";
        } else if (isSpigot()) {
            return "Spigot";
        } else if (isCraftBukkit()) {
            return "CraftBukkit";
        } else {
            return "Unknown";
        }
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
        getServer().getPluginManager().registerEvents(new BukkitEventListener(), this);

        // Register commands
        getCommand("pronouns").setExecutor(new PronounsCommand());

        // Plugin enable message
        getLogger().info("LPPronouns has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin disable message
        getLogger().info("LPPronouns has been disabled!");
    }
}
