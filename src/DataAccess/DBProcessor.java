package DataAccess;

import BusinessLogic.Player;
import BusinessLogic.User;

import java.sql.*;

public class DBProcessor {
    private static String serverAdres = "127.0.0.1:5432";
    private static String DBname = "fc";
    private static String clientName = "postgres";
    private static String password = "master";
    private static Connection connection;

    public static Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return null;
        }
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://" + serverAdres + "/" + DBname, clientName, password);
        } catch (SQLException e) {
            System.err.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }
        if (connection == null) {
            System.err.println("Failed to make connection!");
        }
        return connection;
    }

    public static User addNewUser(User user) {
        if (checkUser(user.getLogin())) {
            return null;
        }
        try {
            PreparedStatement pr = connection.prepareStatement("INSERT INTO \"user\" (login, password) VALUES (?,?)\n" +
                    "RETURNING user_id");
            pr.setString(1, user.getLogin());
            pr.setString(2, user.getPassword());
            ResultSet rs = pr.executeQuery();
            if (!rs.next()) {
                return null;
            }
            user.setUserId(rs.getInt(1));
            user.setAdmin(false);
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static boolean checkUser(String userName) {
        boolean exists = false;
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT login FROM \"user\" WHERE login = ?");
            pr.setString(1, userName);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                exists = true;
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

    public static User loginUser(User user) {
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT isadmin, user_id FROM \"user\" WHERE login = ? AND password = ?");
            pr.setString(1, user.getLogin());
            pr.setString(2, user.getPassword());
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                user.setAdmin(rs.getBoolean(1));
                user.setUserId(rs.getInt(2));
            } else {
                user = null;
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static Player getPlayer(Player player) {
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT name, country, position,\n" +
                    "rate, birth, isrightfooted, ishealth\n" +
                    "FROM player WHERE player_id = ?");
            pr.setInt(1, player.getPlayerId());
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                player.setName(rs.getString(1));
                player.setCountry(rs.getString(2));
                player.setPosition(getPositionName (rs.getInt(3)));
                player.setRate(rs.getInt(4));
                player.setBirth(rs.getTimestamp(5));
                player.setRightFooted(rs.getBoolean(6));
                player.setHealth(rs.getBoolean(7));
            } else {
                player = null;
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player;
    }

        public static int newPlayer(Player player) {
            int playerId = 0;
            int position = getPositionID (player.getPosition());
            if (position == 0) {
                return playerId;
            }
        try {
            PreparedStatement pr = connection.prepareStatement("INSERT INTO player (name, country, \n" +
                    "rate, birth, isrightfooted, ishealth, position) VALUES \n" +
                    "(?, ?, ? ,? ,? ,? ,?)\n" +
                    "RETURNING player_id");
            pr.setString(1, player.getName());
            pr.setString(2, player.getCountry());
            pr.setInt(3, player.getRate());
            pr.setTimestamp(4, player.getBirth());
            pr.setBoolean(5, player.isRightFooted());
            pr.setBoolean(6, player.isHealth());
            pr.setInt(7, position);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                playerId = rs.getInt(1);
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerId;
    }

    public static int getPositionID (String positionName) {
        int positionID = 0;
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT player_position_id\n" +
                    "FROM player_position WHERE UPPER(player_position_name) = UPPER(?)");
            pr.setString(1, positionName);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                positionID = rs.getInt(1);
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positionID;
    }

    public static String getPositionName (int positionId) {
        String positionName = "error";
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT player_position_name\n" +
                    "FROM player_position WHERE player_position_id = ?");
            pr.setInt(1, positionId);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                positionName = rs.getString(1);
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return positionName;
    }
}