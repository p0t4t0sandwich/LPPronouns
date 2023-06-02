package ca.sperrer.p0t4t0sandwich.lppronouns.common;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.storage.DataSource;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.storage.Database;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.pronouns.PronounsData;
import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.block.Block;

import java.io.File;
import java.io.IOException;
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
    private final Object logger;
    private static LPPronouns singleton = null;
    private boolean STARTED = false;
    public Database database;
    public PronounsData pronounsData;

    /**
     * Constructor for the LPPronouns class.
     * @param configPath The path to the config file
     * @param logger The logger
     */
    public LPPronouns(String configPath, Object logger) {
        singleton = this;
        this.logger = logger;

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
    public void useLogger(String message) {
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

        String type = config.getString("storage.type");
        database = DataSource.getDatabase(type, config);

        pronounsData = DataSource.getPronounsData(database, getPronounsMap());

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
            return "Your pronouns are currently: " + pronounsData.getPronouns(player);
        }
        // Get Pronoun List
        HashMap<String, String> pronoun_list = getPronounsMap();

        StringBuilder text = new StringBuilder();

        // If player does not have a pronoun set, set it to unspecified
        if (Objects.equals(args[0], "none") || Objects.equals(args[0], "remove")) {
            pronounsData.deletePronouns(player);
            text = new StringBuilder("Your pronouns have been removed.");

            // If the pronoun exists, set it to the specified value
        } else if (pronoun_list.containsKey(args[0])) {
            pronounsData.setPronouns(player, args[0]);
            text = new StringBuilder("Your pronouns are now set to " + pronoun_list.get(args[0]));
        } else {
            // If the pronoun does not exist, return an error
            text.append("Sorry, that is not a supported value, if you feel this is an error please create a request the addition in the Discord forum.\nSupported values:");
            for (Map.Entry<String, String> entry: pronoun_list.entrySet()) {
                text.append("\n").append(entry.getKey()).append(": ").append(entry.getValue());
            }
        }
        return "§a" + text;
    }
}
