package ca.sperrer.p0t4t0sandwich.lppronouns.fabric.mixin.listeners.player;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.listeners.player.PlayerMessageListener;
import ca.sperrer.p0t4t0sandwich.lppronouns.fabric.player.FabricPronounPlayer;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Listens for player messages and formats them.
 */
@Mixin(ServerPlayNetworkHandler.class)
public abstract class FabricPlayerMessageListener implements PlayerMessageListener {
    @Shadow public abstract ServerPlayerEntity getPlayer();

    /**
     * Called when a player sends a message.
     * @param packet The packet.
     * @param ci The callback info.
     */
    @Inject(method = "onChatMessage", at = @At("HEAD"), cancellable = true)
    public void onPlayerMessage(ChatMessageC2SPacket packet, CallbackInfo ci) {
        if (packet.chatMessage().startsWith("/")) return;
        if (LPPronouns.cancelChat) {
            ci.cancel();
            // Send message to message relay
            pronounPlayerMessage(new FabricPronounPlayer(getPlayer()), packet.chatMessage());
        }
    }
}
