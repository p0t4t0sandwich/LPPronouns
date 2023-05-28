package ca.sperrer.p0t4t0sandwich.lppronouns.common.pronouns;

import ca.sperrer.p0t4t0sandwich.lppronouns.common.PlayerInstance;
import ca.sperrer.p0t4t0sandwich.lppronouns.common.storage.Database;
import dev.dejvokep.boostedyaml.YamlDocument;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MySQLPronounsData extends PronounsData {
    public MySQLPronounsData(Database<Connection> database, YamlDocument config) {
        super(database, config);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String dbGetPronouns(PlayerInstance player) {
        UUID playerUuid = player.getUUID();

        // Get the player's suffix from the database
        try{
            Connection con = (Connection) this.db.getConnection();
            String SQL_QUERY = "SELECT `pronouns` FROM `player_data` WHERE player_uuid='" + playerUuid + "';";
            PreparedStatement pst = con.prepareStatement(SQL_QUERY);
            ResultSet rs = pst.executeQuery(SQL_QUERY);

            if (rs.next()) {
                return rs.getString("pronouns");
            }

            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "";
    }

    /**
     * @inheritDoc
     */
    @Override
    public void dbSetPronouns(PlayerInstance player, String pronouns) {
        UUID playerUuid = player.getUUID();

        // Update the player's suffix in the database
        try{
            Connection con = (Connection) this.db.getConnection();
            String SQL_QUERY = "UPDATE `player_data` SET `pronouns` = '" + pronouns + "' WHERE player_uuid='" + playerUuid + "';";
            PreparedStatement pst = con.prepareStatement(SQL_QUERY);
            pst.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
