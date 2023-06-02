package ca.sperrer.p0t4t0sandwich.lppronouns.common.storage;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.pronouns.MongoDBPronounsData;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.pronouns.MySQLPronounsData;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.pronouns.PronounsData;
import dev.dejvokep.boostedyaml.YamlDocument;

import java.util.Arrays;
import java.util.HashMap;


public class DataSource {
    /**
     * Get the database
     * @param type The type of database
     * @param config The config file
     * @return The database
     */
    public static Database getDatabase(String type, YamlDocument config) {
        try {
            switch (type) {
                case "mysql":
                    return new MySQLDatabase(config);
                case "mongodb":
                    return new MongoDBDatabase(config);
                default:
                    return null;
            }
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
            return null;
        }
    }

    /**
     * Get the pronouns data class
     * @param database The database
     * @return The pronouns data class
     */
    public static PronounsData getPronounsData(Database database, HashMap<String, String> config) {
        try {
            switch (database.getType()) {
                case "mysql":
                    return new MySQLPronounsData(database, config);
                case "mongodb":
                    return new MongoDBPronounsData(database, config);
                default:
                    return null;
                }
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
            return null;
        }
    }
}
