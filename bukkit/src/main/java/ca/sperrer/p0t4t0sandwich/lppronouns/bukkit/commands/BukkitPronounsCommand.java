package ca.sperrer.p0t4t0sandwich.lppronouns.bukkit.commands;

import ca.sperrer.p0t4t0sandwich.lppronouns.bukkit.BukkitMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

import static ca.sperrer.p0t4t0sandwich.lppronouns.bukkit.BukkitUtils.mapPlayer;
import static ca.sperrer.p0t4t0sandwich.lppronouns.common.Utils.ansiiParser;
import static ca.sperrer.p0t4t0sandwich.lppronouns.common.Utils.runTaskAsync;

public class BukkitPronounsCommand implements CommandExecutor {
    private final BukkitMain plugin = BukkitMain.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        AtomicBoolean success = new AtomicBoolean(false);
        runTaskAsync(() -> {
            try {
                // Check if sender is a player
                if ((sender instanceof Player)) {
                    Player player = (Player) sender;

                    String text = plugin.LPPronouns.commandHandler(mapPlayer(player), args);

                    player.sendMessage(text);
                    success.set(true);
                } else {
                    plugin.getLogger().info(ansiiParser("§cYou must be a player to use this command."));
                }
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }
        });
        return success.get();
    }
}
