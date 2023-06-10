package ca.sperrer.p0t4t0sandwich.lppronouns.api;

import ca.sperrer.p0t4t0sandwich.lppronouns.api.storage.Database;
import ca.sperrer.p0t4t0sandwich.lppronouns.api.pronouns.PronounsData;

import java.util.HashMap;

public interface LPPronouns {
    /**
     * Properties of the LPPronouns class.
     * config: The config file
     * logger: The logger
     * singleton: The singleton instance of the LPPronouns class
     * STARTED: Whether the LPPronouns has been started
     */
    LPPronouns singleton = null;
    Database database = null;
    PronounsData pronounsData = null;

    /**
     * Getter for the singleton instance of the LPPronouns class.
     * @return The singleton instance
     */
    static LPPronouns getInstance() {
        return singleton;
    }

    /**
     * Get pronouns from the config file.
     * @return The pronouns
     */
    HashMap<String, String> getPronounsMap();
}
