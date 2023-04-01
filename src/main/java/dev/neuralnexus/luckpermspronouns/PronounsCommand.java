package dev.neuralnexus.luckpermspronouns;

import dev.dejvokep.boostedyaml.block.Block;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.scheduler.ScheduledTask;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class PronounsCommand extends Command {
    private final LuckPermsPronouns plugin = LuckPermsPronouns.getInstance();

    public PronounsCommand() {
        super("pronouns");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        ScheduledTask scheduledTask = plugin.getProxy().getScheduler().schedule(plugin, () -> {
            // Check if sender is a player
            if ((sender instanceof ProxiedPlayer)) {
                // Get Pronoun List
                Map<String, Block> pronoun_list = (Map<String, Block>) plugin.config.getBlock("pronouns").getStoredValue();

                ProxiedPlayer player = (ProxiedPlayer) sender;

                StringBuilder text = new StringBuilder();

                // If player does not have a pronoun set, set it to unspecified
                if (args.length > 0 && (Objects.equals(args[0], "unspecified") || Objects.equals(args[0], "remove"))) {
                    plugin.removePronouns(player);
                    text = new StringBuilder("Your pronouns have been removed.");

                    // If the pronoun exists, set it to the specified value
                } else if (args.length > 0 && pronoun_list.containsKey(args[0])) {
                    plugin.setPronouns(player, args[0]);
                    text = new StringBuilder("Your pronouns are now set to " + pronoun_list.get(args[0]).getStoredValue().toString());
                } else {
                    // If the pronoun does not exist, return an error
                    text.append("Sorry, that is not a supported value, if you feel this is an error please create a request the addition in the Discord forum.\nSupported values:");
                    for (Map.Entry<String, Block> entry: pronoun_list.entrySet()) {
                        text.append("\n").append(entry.getKey()).append(": ").append(entry.getValue().getStoredValue().toString());
                    }
                }
                player.sendMessage(new ComponentBuilder(text.toString()).color(ChatColor.GREEN).create());
            }
        }, 0, TimeUnit.SECONDS);
    }
}
