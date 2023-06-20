package ca.sperrer.p0t4t0sandwich.lppronouns.fabric.mixin.commands;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.LPPronouns;
import ca.sperrer.p0t4t0sandwich.lppronouns.fabric.player.FabricPronounPlayer;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static ca.sperrer.p0t4t0sandwich.lppronouns.common.Utils.ansiiParser;
import static ca.sperrer.p0t4t0sandwich.lppronouns.common.Utils.runTaskAsync;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

@Mixin(CommandManager.class)
public class FabricPronounsCommand {
    @Shadow
    @Final
    private CommandDispatcher<ServerCommandSource> dispatcher;

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lcom/mojang/brigadier/CommandDispatcher;setConsumer(Lcom/mojang/brigadier/ResultConsumer;)V"))
    private void registerPronounsCommand(CommandManager.RegistrationEnvironment environment, CommandRegistryAccess commandRegistryAccess, CallbackInfo ci) {
        this.dispatcher.register(literal("pronouns")
                .requires(source -> source.hasPermissionLevel(0))
                .then(argument("pronouns", StringArgumentType.greedyString())
                        .executes(context -> {
                            runTaskAsync(() -> {
                                try {
                                    String[] args = new String[] {context.getArgument("pronouns", String.class)};

                                    // Send message to player or console
                                    Entity entity = context.getSource().getEntity();
                                    if (entity instanceof ServerPlayerEntity) {
                                        String text = LPPronouns.commandHandler(new FabricPronounPlayer((ServerPlayerEntity) entity), args);
                                        ((ServerPlayerEntity) entity).sendMessage(Text.of(text), false);
                                    } else {
                                        LPPronouns.useLogger(ansiiParser("§cYou must be a player to use this command."));
                                    }
                                } catch (Exception e) {
                                    System.err.println(e);
                                    e.printStackTrace();
                                }
                            });
                            return 1;
                        })
                ));
    }
}
