package ca.sperrer.p0t4t0sandwich.lppronouns.common.pronouns;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.storage.Database;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.PlayerInstance;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dev.dejvokep.boostedyaml.YamlDocument;
import org.bson.Document;

public class MongoDBPronounsData extends PronounsData {
    public MongoDBPronounsData(Database<MongoClient> database, YamlDocument config) {
        super(database, config);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String dbGetPronouns(PlayerInstance player) {
        String player_uuid = player.getUUID().toString();
        try {
            MongoClient mongoClient = (MongoClient) this.db.getConnection();
            String database = this.db.getDatabase();

            // Get player data
            MongoDatabase db = mongoClient.getDatabase(database);
            MongoCollection<Document> collection = db.getCollection("player_data");
            Document query = new Document("player_uuid", player_uuid);
            Document player_data = collection.find(query).first();

            // If player data doesn't exist, create it
            if (player_data == null) {
                Document new_player_data = new Document();
                new_player_data.append("player_name", player.getName());
                new_player_data.append("player_uuid", player_uuid);
                new_player_data.append("pronouns", "unspecified");
                collection.insertOne(new_player_data);
                return "";
            }

            // Get pronouns
            return player_data.getString("pronouns");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    /**
     * @inheritDoc
     */
    @Override
    public void dbSetPronouns(PlayerInstance player, String pronouns) {
        String player_uuid = player.getUUID().toString();
        try {
            MongoClient mongoClient = (MongoClient) this.db.getConnection();
            String database = this.db.getDatabase();

            // Get player data
            MongoDatabase db = mongoClient.getDatabase(database);
            MongoCollection<Document> collection = db.getCollection("player_data");
            Document query = new Document("player_uuid", player_uuid);
            Document player_data = collection.find(query).first();

            // If player data doesn't exist, create it
            if (player_data == null) {
                Document new_player_data = new Document();
                new_player_data.append("player_name", player.getName());
                new_player_data.append("player_uuid", player_uuid);
                new_player_data.append("pronouns", "unspecified");
                collection.insertOne(new_player_data);
            }

            // Update pronouns
            Document update = new Document("pronouns", pronouns);
            collection.updateOne(query, new Document("$set", update));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
