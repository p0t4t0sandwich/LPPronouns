package ca.sperrer.p0t4t0sandwich.lppronouns.fabric.mixin.listeners.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.listeners.player.PlayerLogoutListener;
import ca.sperrer.p0t4t0sandwich.lppronouns.fabric.player.FabricPronounPlayer;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Listens for player logouts and removes them from the cache.
 */
@Mixin(ServerPlayerEntity.class)
public class FabricPlayerLogoutListener implements PlayerLogoutListener {
    /**
     * Called when a player logs out.
     * @param ci The callback info.
     */
    @Inject(method = "onDisconnect", at = @At("HEAD"))
    public void onPlayerDeath(CallbackInfo ci) {
        // Pass PronounPlayer to helper function
        pronounPlayerLogout(new FabricPronounPlayer((ServerPlayerEntity) (Object) this));
    }
}
