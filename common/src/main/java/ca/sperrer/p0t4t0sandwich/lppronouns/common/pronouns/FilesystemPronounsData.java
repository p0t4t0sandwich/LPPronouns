package ca.sperrer.p0t4t0sandwich.lppronouns.common.pronouns;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.player.PronounPlayer;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.storage.Database;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoClient;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FilesystemPronounsData extends PronounsData {
    /**
     * Constructor for the FilesystemPronounsData class
     * @param database The database
     * @param pronounsConfig The pronouns config
     */
    public FilesystemPronounsData(Database<MongoClient> database, HashMap<String, String> pronounsConfig) {
        super(database, pronounsConfig);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String dbGetPronouns(PronounPlayer player) {
        UUID playerUuid = player.getUUID();

        String connection = (String) this.db.getConnection();
        String database = this.db.getDatabase();

        String filePath = connection + "/" + database + "/" + playerUuid + ".json";
        try {
            // Read JSON file using GSON
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            // Data is in the form of {"player_uuid": "UUID", "pronouns": "pronouns"}
            Map data = gson.fromJson(new FileReader(filePath), Map.class);

            return (String) data.get("pronouns");
        } catch (FileNotFoundException e) {
            // File doesn't exist, return empty string
            this.dbSetPronouns(player, "");
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
        UUID playerUuid = player.getUUID();

        String connection = (String) this.db.getConnection();
        String database = this.db.getDatabase();

        String filePath = connection + "/" + database + "/" + playerUuid + ".json";
        try {
            // Read JSON file using GSON
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            // New Map to store data
            Map<String, String> data = new HashMap<>();
            data.put("player_uuid", playerUuid.toString());
            data.put("pronouns", pronouns);

            // Check if folder exists
            File folder = new File(connection + "/" + database);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Write to JSON file
            String json = gson.toJson(data);
            FileWriter writer = new FileWriter(filePath);

            writer.write(json);
            writer.close();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
