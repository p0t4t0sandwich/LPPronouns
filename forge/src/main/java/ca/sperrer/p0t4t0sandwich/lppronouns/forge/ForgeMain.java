package ca.sperrer.p0t4t0sandwich.lppronouns.forge;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import ca.sperrer.p0t4t0sandwich.lppronouns.forge.listeners.ForgeEventListener;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(ForgeMain.MODID)
public class ForgeMain {
    public LPPronouns LPPronouns;
    public static final String MODID = "playtimeutils";
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

        // Start LPPronouns
        LPPronouns = new LPPronouns("config", logger);
        LPPronouns.start();

        // Register event listener
        MinecraftForge.EVENT_BUS.register(new ForgeEventListener());

        // Mod enable message
        logger.info("[LPPronouns]: LPPronouns has been enabled!");
    }
}
