package ca.sperrer.p0t4t0sandwich.lppronouns.velocity.commands;

import ca.sperrer.p0t4t0sandwich.lppronouns.velocity.VelocityMain;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.text.Component;

import static ca.sperrer.p0t4t0sandwich.lppronouns.velocity.VelocityUtils.mapPlayer;

public class VelocityPronounsCommand implements SimpleCommand {
    private final VelocityMain plugin = VelocityMain.getInstance();

    @Override
    public void execute(Invocation invocation) {
//        runTaskAsync(() -> { // TODO: Figure out why it just doesn't work async
            try {
                // Check if sender is a player
                if ((invocation.source() instanceof Player)) {
                    Player player = (Player) invocation.source();

                    // Permission check
                    if (!player.hasPermission("lppronouns.pronouns")) {
                        player.sendMessage(Component.text("§cYou do not have permission to use this command."));
                        return;
                    }

                    String text = plugin.LPPronouns.commandHandler(mapPlayer(player), invocation.arguments());

                    player.sendMessage(Component.text(text));
                } else {
                    plugin.getLogger().info("§cYou must be a player to use this command.");
                }
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
//        });
    }
}
