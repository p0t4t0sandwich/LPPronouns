package ca.sperrer.p0t4t0sandwich.lppronouns.fabric;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import ca.sperrer.p0t4t0sandwich.lppronouns.fabric.commands.PronounsCommand;
import ca.sperrer.p0t4t0sandwich.lppronouns.fabric.listeners.FabricEventListener;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FabricMain implements DedicatedServerModInitializer {
    public LPPronouns LPPronouns;

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

        // Register event listeners
        ServerLifecycleEvents.SERVER_STARTED.register(new FabricEventListener());
        ServerPlayConnectionEvents.JOIN.register(new FabricEventListener());

        // Register commands
        CommandRegistrationCallback.EVENT.register(PronounsCommand::register);

        // Mod enable message
        logger.info("[LPPronouns]: LPPronouns has been enabled!");
    }
}
