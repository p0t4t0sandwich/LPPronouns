package ca.sperrer.p0t4t0sandwich.lppronouns.common;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.api.LPPronounsProvider;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.hooks.LuckPermsHook;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.relay.MessageRelay;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.storage.DataSource;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.storage.Database;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.pronouns.PronounsData;
import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.block.Block;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LPPronouns {
    /**
     * Properties of the LPPronouns class.
     * config: The config file
     * logger: The logger
     * singleton: The singleton instance of the LPPronouns class
     * STARTED: Whether the LPPronouns has been started
     */
    private static YamlDocument config;
    private static Object logger;
    private static LPPronouns singleton = null;
    private boolean STARTED = false;
    public Database database;
    public PronounsData pronounsData;
    private static final ArrayList<Object> hooks = new ArrayList<>();
    private MessageRelay messageRelay;
    public static boolean cancelChat = false;

    /**
     * Constructor for the LPPronouns class.
     * @param configPath The path to the config file
     * @param logger The logger
     */
    public LPPronouns(String configPath, Object logger) {
        singleton = this;
        LPPronouns.logger = logger;

        // Config
        try {
            config = YamlDocument.create(new File("./" + configPath + "/LPPronouns", "config.yml"),
                    Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("config.yml"))
            );
            config.reload();
        } catch (IOException e) {
            useLogger("Failed to load config.yml!\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Getter for the singleton instance of the LPPronouns class.
     * @return The singleton instance
     */
    public static LPPronouns getInstance() {
        return singleton;
    }

    /**
     * Use whatever logger is being used.
     * @param message The message to log
     */
    public static void useLogger(String message) {
        if (logger instanceof java.util.logging.Logger) {
            ((java.util.logging.Logger) logger).info(message);
        } else if (logger instanceof org.slf4j.Logger) {
            ((org.slf4j.Logger) logger).info(message);
        } else {
            System.out.println(message);
        }
    }

    /**
     * Start LPPronouns
     */
    public void start() {
        if (STARTED) {
            useLogger("LPPronouns has already started!");
            return;
        }
        STARTED = true;

        // Set API instance
        LPPronounsProvider.register(this);

        // Add LuckPerms hook
        useLogger("LuckPerms detected, enabling LuckPerms hook.");
        addHook(new LuckPermsHook());

        // Set up database
        String type = config.getString("storage.type");
        database = DataSource.getDatabase(type, config);

        // Set up pronouns data
        pronounsData = DataSource.getPronounsData(database, getPronounsMap());

        // Set up message formatter
        if (config.getBoolean("formatting.enabled") && config.getString("formatting.format") != null) {
            cancelChat = true;
            messageRelay = new MessageRelay(config.getString("formatting.format"));
        }

        useLogger("LPPronouns has been started!");
    }

    /**
     * Get pronouns from the config file.
     * @return The pronouns
     */
    public HashMap<String, String> getPronounsMap() {
        HashMap<String, String> pronouns = new HashMap<>();

        HashMap<String, Block> pronouns_config = (HashMap<String, Block>) config.getBlock("pronouns").getStoredValue();

        for (Map.Entry<String, Block> entry: pronouns_config.entrySet()) {
            pronouns.put(entry.getKey(), (String) entry.getValue().getStoredValue());
        }

        return pronouns;
    }

    /**
     * Command Handler
     * @param args The arguments
     * @return The output
     */
    public String commandHandler(PlayerInstance player, String[] args) {
        if (args.length == 0) {
            return "§6Your pronouns are currently: §a" + pronounsData.getPronouns(player);
        }

        // Get Pronoun List
        HashMap<String, String> pronoun_list = getPronounsMap();

        String text = "";
        switch (args[0]) {
            // Command to list pronouns
            case "list":
                text += "§6Supported values:";
                for (Map.Entry<String, String> entry: pronoun_list.entrySet()) {
                    text += "\n§6" + entry.getKey() + ": §a" + entry.getValue();
                }
                break;

            // Command to remove pronouns
            case "clear":
            case "delete":
            case "none":
            case "remove":
            case "reset":
                pronounsData.deletePronouns(player);
                text = "§aYour pronouns have been removed.";
                break;

            // Command to get help
            case "help":
                text = "§6Usage: §3/pronouns [pronoun]" +
                        "\n§6Supported values: §3/pronouns list" +
                        "\n§6Remove pronouns: §3/pronouns none" +
                        "\n§6Set pronouns: §3/pronouns [pronoun]" +
                        "\n\n§6Your pronouns are currently: §a" + pronounsData.getPronouns(player);
                break;

            default:
                // If the pronoun exists, set it to the specified value
                if (pronoun_list.containsKey(args[0])) {
                    pronounsData.setPronouns(player, args[0]);
                    text = "§6Your pronouns are now set to: §a" + pronoun_list.get(args[0]);
                } else {
                    // If the pronoun does not exist, return an error
                    text = "§6Sorry, that is not a supported value, if you feel this is an error please create a request the addition in the Discord forum.\nSupported values:";
                    for (Map.Entry<String, String> entry: pronoun_list.entrySet()) {
                        text += "\n§6" + entry.getKey() + ": §a" + entry.getValue();
                    }
                }
                break;
        }
        return text;
    }

    /**
     * Add a hook to the list of hooks
     * @param hook The hook to add
     */
    public static void addHook(Object hook) {
        hooks.add(hook);
    }
}
