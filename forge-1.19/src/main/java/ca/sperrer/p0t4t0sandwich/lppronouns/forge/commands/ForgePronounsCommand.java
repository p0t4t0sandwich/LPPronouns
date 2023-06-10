package ca.sperrer.p0t4t0sandwich.lppronouns.forge.commands;

import ca.sperrer.p0t4t0sandwich.lppronouns.forge.ForgeMain;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static ca.sperrer.p0t4t0sandwich.lppronouns.common.Utils.ansiiParser;
import static ca.sperrer.p0t4t0sandwich.lppronouns.common.Utils.runTaskAsync;
import static ca.sperrer.p0t4t0sandwich.lppronouns.forge.ForgeUtils.mapPlayer;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;


public final class ForgePronounsCommand {
    private static final ForgeMain mod = ForgeMain.getInstance();

    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(literal("pronouns")
            .requires(source -> source.hasPermission(0))
            .then(argument("pronouns", StringArgumentType.greedyString())
                .executes(context -> {
                    runTaskAsync(() -> {
                        try {
                            String[] args = new String[] {context.getArgument("pronouns", String.class)};

                            // Send message to player or console
                            Entity entity = context.getSource().getEntity();
                            if (entity instanceof ServerPlayer) {
                                String text = mod.LPPronouns.commandHandler(mapPlayer((ServerPlayer) entity), args);
                                ((ServerPlayer) entity).displayClientMessage(Component.empty().append(text), false);
                            } else {
                                mod.logger.info(ansiiParser("§cYou must be a player to use this command."));
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
