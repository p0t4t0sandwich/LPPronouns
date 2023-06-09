package ca.sperrer.p0t4t0sandwich.lppronouns.common.pronouns;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.storage.Database;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.player.PronounPlayer;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;

public class MongoDBPronounsData extends PronounsData {
    /**
     * Constructor for the MongoDBPronounsData class
     * @param database The database
     * @param pronounsConfig The pronouns config
     */
    public MongoDBPronounsData(Database<MongoClient> database, HashMap<String, String> pronounsConfig) {
        super(database, pronounsConfig);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String dbGetPronouns(PronounPlayer player) {
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
            System.err.println(e);
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @inheritDoc
     */
    @Override
    public void dbSetPronouns(PronounPlayer player, String pronouns) {
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
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
