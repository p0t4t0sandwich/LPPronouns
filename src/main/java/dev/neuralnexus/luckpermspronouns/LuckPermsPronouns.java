package dev.neuralnexus.luckpermspronouns;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.dejvokep.boostedyaml.YamlDocument;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.node.types.SuffixNode;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public final class LuckPermsPronouns extends Plugin {
    public static YamlDocument config;
    private static HikariConfig dbconfig = new HikariConfig();
    private static HikariDataSource ds;
    private LuckPerms luckPerms;

    // Singleton instance
    private static LuckPermsPronouns instance;
    public static LuckPermsPronouns getInstance() {
        return instance;
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    @Override
    public void onEnable() {
        // Config
        try {
            config = YamlDocument.create(new File(getDataFolder(), "config.yml"), getResourceAsStream("config.yml"));
            config.reload();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Database
        dbconfig.setJdbcUrl("jdbc:mysql://" + config.getString("data.address") + "/" + config.getString("data.database"));
        dbconfig.setUsername(config.getString("data.username"));
        dbconfig.setPassword(config.getString("data.password"));

        dbconfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dbconfig.addDataSourceProperty("cachePrepStmts", "true");
        dbconfig.addDataSourceProperty("prepStmtCacheSize", "250");
        dbconfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(dbconfig);

        // Get LuckPerms API
        this.luckPerms = LuckPermsProvider.get();

        // Singleton instance
        instance = this;

        // Register event listeners
        getProxy().getPluginManager().registerListener(this, new BungeeEventListener());

        // Register commands
        getProxy().getPluginManager().registerCommand(this, new PronounsCommand());

        // Plugin enable message
        getLogger().info("LuckPermsPronouns has been enabled.");
    }

    @Override
    public void onDisable() {
        ds.close();
        // Plugin disable message
        getLogger().info("LuckPermsPronouns has been disabled.");
    }

    // Set the player's pronouns in the database and LuckPerms
    public void setPronouns(ProxiedPlayer player, String pronouns) {
        UUID playerUuid = player.getUniqueId();

        // Update the player's suffix in the database
        Connection con = null;
        try{
            con = getConnection();
            String SQL_QUERY = "UPDATE `player_data` SET `pronouns` = '" + pronouns + "' WHERE player_uuid='" + playerUuid + "';";
            PreparedStatement pst = con.prepareStatement(SQL_QUERY);
            pst.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        // Get mapped pronouns from config
        String mapped_pronouns = config.getString("pronouns." + pronouns);

        // Update the player's suffix in LuckPerms
        SuffixNode node = SuffixNode.builder(mapped_pronouns, 1).build();
        luckPerms.getUserManager().modifyUser(playerUuid, user -> user.data().add(node));
    }

    // Get the player's pronouns from the database
    public String getPronouns(ProxiedPlayer player) {
        UUID playerUuid = player.getUniqueId();

        // Get the player's suffix from the database
        Connection con = null;
        try{
            con = getConnection();
            String SQL_QUERY = "SELECT `pronouns` FROM `player_data` WHERE player_uuid='" + playerUuid + "';";
            PreparedStatement pst = con.prepareStatement(SQL_QUERY);
            ResultSet rs = pst.executeQuery(SQL_QUERY);

            if (rs.next()) {
                return rs.getString("pronouns");
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return "";
    }

    // Refresh the player's pronouns in LuckPerms
    public void refreshPronouns(ProxiedPlayer player) {
        // Get the player's pronouns from the database
        String pronouns = getPronouns(player);

        // Get mapped pronouns from config
        String mapped_pronouns = config.getString("pronouns." + pronouns);

        if (mapped_pronouns != null && !mapped_pronouns.isEmpty()) {
            // Update the player's suffix in LuckPerms
            SuffixNode node = SuffixNode.builder(mapped_pronouns, 1).build();
            luckPerms.getUserManager().modifyUser(player.getUniqueId(), user -> user.data().add(node));
        }
    }

    // Remove the player's pronouns from the database and LuckPerms
    public void removePronouns(ProxiedPlayer player) {
        UUID playerUuid = player.getUniqueId();

        // Get the player's pronouns from the database
        String pronouns = getPronouns(player);
        String mapped_pronouns = config.getString("pronouns." + pronouns);

        if (mapped_pronouns != null && !mapped_pronouns.isEmpty()) {
            // Update the player's suffix in LuckPerms
            SuffixNode node = SuffixNode.builder(mapped_pronouns, 1).build();
            luckPerms.getUserManager().modifyUser(playerUuid, user -> user.data().remove(node));

            // Update the player's suffix in the database
            Connection con = null;
            try{
                con = getConnection();
                String SQL_QUERY = "UPDATE `player_data` SET `pronouns` = '' WHERE player_uuid='" + playerUuid + "';";
                PreparedStatement pst = con.prepareStatement(SQL_QUERY);
                pst.executeUpdate();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
