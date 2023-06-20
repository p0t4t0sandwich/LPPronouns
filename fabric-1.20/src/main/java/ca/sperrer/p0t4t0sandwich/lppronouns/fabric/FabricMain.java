package ca.sperrer.p0t4t0sandwich.lppronouns.fabric;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import ca.sperrer.p0t4t0sandwich.lppronouns.fabric.commands.FabricPronounsCommand;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FabricMain implements DedicatedServerModInitializer {
    public LPPronouns lpPronouns;

    // Logger
    public final Logger logger = LoggerFactory.getLogger("lppronouns");

    // Get server type
    public String getServerType() {
        return "Fabric";
    }

    // Singleton instance
    private static FabricMain instance;
    public static FabricMain getInstance() {
        return instance;
    }

    @Override
    public void onInitializeServer() {
        // Singleton instance
        instance = this;

        logger.info("[LPPronouns]: LPPronouns is running on " + getServerType() + ".");

        // Register commands
        CommandRegistrationCallback.EVENT.register(FabricPronounsCommand::register);

        // Mod enable message
        logger.info("[LPPronouns]: LPPronouns has been enabled!");
    }
}
