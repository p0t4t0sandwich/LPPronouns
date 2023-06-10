package ca.sperrer.p0t4t0sandwich.lppronouns.forge;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import ca.sperrer.p0t4t0sandwich.lppronouns.forge.commands.ForgePronounsCommand;
import ca.sperrer.p0t4t0sandwich.lppronouns.forge.listeners.ForgePlayerLoginListener;
import ca.sperrer.p0t4t0sandwich.lppronouns.forge.listeners.ForgeServerStartedListener;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(ForgeMain.MODID)
public class ForgeMain {
    public LPPronouns LPPronouns;
    public static final String MODID = "lppronouns";
    public static final Logger logger = LogUtils.getLogger();

    // Get server type
    public String getServerType() {
        return "Forge";
    }

    // Singleton instance
    private static ForgeMain instance;

    public static ForgeMain getInstance() {
        return instance;
    }


    public ForgeMain() {
        // Singleton instance
        instance = this;

        logger.info("[LPPronouns]: LPPronouns is running on " + getServerType() + ".");

        // Register event listeners
        MinecraftForge.EVENT_BUS.register(new ForgeServerStartedListener());
        MinecraftForge.EVENT_BUS.register(new ForgePlayerLoginListener());

        // Register commands
        MinecraftForge.EVENT_BUS.register(ForgePronounsCommand.class);

        // Mod enable message
        logger.info("[LPPronouns]: LPPronouns has been enabled!");
    }
}
