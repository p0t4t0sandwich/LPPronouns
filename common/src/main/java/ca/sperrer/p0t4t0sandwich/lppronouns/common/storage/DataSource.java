package ca.sperrer.p0t4t0sandwich.lppronouns.common.storage;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.pronouns.MongoDBPronounsData;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.pronouns.MySQLPronounsData;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.pronouns.PronounsData;
import dev.dejvokep.boostedyaml.YamlDocument;


public interface DataSource {
    /**
     * Get the database
     * @param type The type of database
     * @param config The config file
     * @return The database
     */
    static Database getDatabase(String type, YamlDocument config) {
        switch (type) {
            case "mysql":
                return new MySQLDatabase(config);
            case "mongodb":
                return new MongoDBDatabase(config);
            default:
                return null;
        }
    }

    /**
     * Get the pronouns data class
     * @param type The type of database
     * @param database The database
     * @return The pronouns data class
     */
    static PronounsData getPronounsData(String type, Database database, YamlDocument config) {
        switch (type) {
            case "mysql":
                return new MySQLPronounsData(database, config);
            case "mongodb":
                return new MongoDBPronounsData(database, config);
            default:
                return null;
        }
    }
}