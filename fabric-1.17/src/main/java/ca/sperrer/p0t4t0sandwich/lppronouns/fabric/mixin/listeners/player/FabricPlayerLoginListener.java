package ca.sperrer.p0t4t0sandwich.lppronouns.fabric.mixin.listeners.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.listeners.player.PlayerLoginListener;
import ca.sperrer.p0t4t0sandwich.lppronouns.fabric.player.FabricPronounPlayer;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Listens for player logins and refreshes their pronouns.
 */
@Mixin(ServerLoginNetworkHandler.class)
public class FabricPlayerLoginListener implements PlayerLoginListener {
    /**
     * Called when a player logs in.
     * @param ci The callback info.
     */
    @Inject(method = "addToServer", at = @At("HEAD"))
    private void onPlayerLogin(ServerPlayerEntity player, CallbackInfo ci) {
        // Pass PronounPlayer to helper function
        pronounPlayerLogin(new FabricPronounPlayer(player));
    }
}
