package ca.sperrer.p0t4t0sandwich.lppronouns.fabric.commands;

import ca.sperrer.p0t4t0sandwich.lppronouns.fabric.FabricMain;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager.RegistrationEnvironment;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import static ca.sperrer.p0t4t0sandwich.lppronouns.common.Utils.ansiiParser;
import static ca.sperrer.p0t4t0sandwich.lppronouns.common.Utils.runTaskAsync;
import static ca.sperrer.p0t4t0sandwich.lppronouns.fabric.FabricUtils.mapPlayer;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;


public final class PronounsCommand {
    private static final FabricMain mod = FabricMain.getInstance();

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, RegistrationEnvironment environment) {
        dispatcher.register(literal("pronouns")
            .requires(source -> source.hasPermissionLevel(0))
            .then(argument("pronouns", StringArgumentType.greedyString())
            .executes(context -> {
                runTaskAsync(() -> {
                    try {
                        String[] args = new String[] {context.getArgument("pronouns", String.class)};

                        // Send message to player or console
                        Entity entity = context.getSource().getEntity();
                        if (entity instanceof ServerPlayerEntity) {
                            String text = mod.LPPronouns.commandHandler(mapPlayer((ServerPlayerEntity) entity), args);
                            entity.sendMessage(Text.literal(text));
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
