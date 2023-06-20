package ca.sperrer.p0t4t0sandwich.lppronouns.fabric.mixin.listeners.server;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import ca.sperrer.p0t4t0sandwich.lppronouns.fabric.FabricMain;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Listens for server start and starts LPPronouns.
 */
@Mixin(MinecraftServer.class)
public class FabricServerStartingListener {
    FabricMain plugin = FabricMain.getInstance();
    /**
     * Called when the server is starting.
     * @param ci The callback info.
     */
    @Inject(method = "runServer", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;setupServer()Z"))
    public void onServerStarting(CallbackInfo ci) {
        // Start LPPronouns
        plugin.lpPronouns = new LPPronouns("config", plugin.logger);
        plugin.lpPronouns.start();
    }
}