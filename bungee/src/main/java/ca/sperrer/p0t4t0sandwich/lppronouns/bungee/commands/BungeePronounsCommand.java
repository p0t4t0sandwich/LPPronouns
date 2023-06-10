package ca.sperrer.p0t4t0sandwich.lppronouns.bungee.commands;

import ca.sperrer.p0t4t0sandwich.lppronouns.bungee.BungeeMain;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import static ca.sperrer.p0t4t0sandwich.lppronouns.bungee.BungeeUtils.mapPlayer;
import static ca.sperrer.p0t4t0sandwich.lppronouns.common.Utils.runTaskAsync;

public class BungeePronounsCommand extends Command {
    private final BungeeMain plugin = BungeeMain.getInstance();

    public BungeePronounsCommand() {
        super("pronouns");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        runTaskAsync(() -> {
            try {
                // Check if sender is a player
                if ((sender instanceof ProxiedPlayer)) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;

                    String text = plugin.LPPronouns.commandHandler(mapPlayer(player), args);

                    player.sendMessage(new ComponentBuilder(text).create());
                } else {
                    sender.sendMessage(new ComponentBuilder("§cYou must be a player to use this command.").create());
                }
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();
            }
        });
    }
}
