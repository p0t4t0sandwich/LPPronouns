package ca.sperrer.p0t4t0sandwich.lppronouns.common.storage;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.dejvokep.boostedyaml.YamlDocument;

public class MongoDBDatabase extends Database<MongoClient> {
    /**
     * Constructor for the MongoDBDataSource class
     * @param config The configuration for the MongoDB data source.
     */
    public MongoDBDatabase(YamlDocument config) {
        super("mongodb", null, null);
        String host = config.getString("storage.config.host");
        int port = Integer.parseInt(config.getString("storage.config.port"));
        String database = config.getString("storage.config.database");
        String username = config.getString("storage.config.username");
        String password = config.getString("storage.config.password");
        String authSource = config.getString("storage.config.authSource");

        if (port == 0) {
            port = 27017;
        }
        String URI = "mongodb://" + username + ":" + password + "@" + host + ":" + port + "/" + database + "?authSource=" + authSource;
        MongoClient mongoClient = MongoClients.create(URI);

        setConnection(mongoClient);
        setDatabase(database);
    }
}
