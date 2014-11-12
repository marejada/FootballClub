package DataAccess;

import BusinessLogic.*;

import java.sql.*;
import java.util.ArrayList;

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
        PreparedStatement pr = null;
        ResultSet rs = null;
        try {
            pr = connection.prepareStatement("SELECT isadmin, user_id FROM \"user\" WHERE login = ? AND password = ?");
            pr.setString(1, user.getLogin());
            pr.setString(2, user.getPassword());
            rs = pr.executeQuery();
            if (rs.next()) {
                user.setAdmin(rs.getBoolean(1));
                user.setUserId(rs.getInt(2));
            } else {
                user = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pr, rs);
        }
        return user;
    }

    public static void close (PreparedStatement pr) {
        try {
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close (PreparedStatement pr, ResultSet rs) {
        close(pr);
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static Player getPlayer(Player player) {
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT name, country, position,\n" +
                    "rate, birth, isrightfooted, ishealth, isinmainteam\n" +
                    "FROM player WHERE player_id = ?");
            pr.setInt(1, player.getPlayerId());
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                player.setName(rs.getString(1));
                player.setCountry(rs.getString(2));
                player.setPosition(getPositionName(rs.getInt(3)));
                player.setRate(rs.getInt(4));
                player.setBirth(rs.getTimestamp(5));
                player.setRightFooted(rs.getBoolean(6));
                player.setHealth(rs.getBoolean(7));
                player.setInMainTeam(rs.getBoolean(8));
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

    public static ArrayList<Player> getAllPlayers() {
        ArrayList<Player> players = new ArrayList<Player>();
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT name, country, position,\n" +
                    "rate, birth, isrightfooted, isinmainteam, player_id, isHealth \n" +
                    "FROM player");
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                String name = rs.getString(1);
                String country = rs.getString(2);
                String position = getPositionName (rs.getInt(3));
                int rate = rs.getInt(4);
                Timestamp birth = rs.getTimestamp(5);
                boolean rightFooted = rs.getBoolean(6);
                boolean inMainTeam = (rs.getBoolean(7));
                int player_id = rs.getInt(8);
                boolean isHealth = rs.getBoolean(9);
                Player player = new Player(name, country, position, rate, birth, rightFooted,
                        player_id, inMainTeam, isHealth);
                players.add(player);
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }


        public static int newPlayer(Player player) {
            int playerId = 0;
            int position = getPositionID (player.getPosition());
            if (position == 0) {
                return playerId;
            }
        try {
            PreparedStatement pr = connection.prepareStatement("INSERT INTO player (name, country, \n" +
                    "rate, birth, isrightfooted, ishealth, position, isinmainteam) VALUES \n" +
                    "(?, ?, ? ,? ,? ,? ,?, ?)\n" +
                    "RETURNING player_id");
            pr.setString(1, player.getName());
            pr.setString(2, player.getCountry());
            pr.setInt(3, player.getRate());
            pr.setTimestamp(4, player.getBirth());
            pr.setBoolean(5, player.isRightFooted());
            pr.setBoolean(6, player.isHealth());
            pr.setInt(7, position);
            pr.setBoolean(8, player.isInMainTeam());
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

    public static void updatePlayer (Player player) {
        int position = getPositionID (player.getPosition());
        if (position == 0) {
            return;
        }
        try {
            PreparedStatement pr = connection.prepareStatement("UPDATE player SET name = ?, country = ?, \n" +
                    "rate = ?, birth = ?, isrightfooted = ?, ishealth = ?, position = ?, isinmainteam = ?\n" +
                    "WHERE player_id = ?");
            pr.setString(1, player.getName());
            pr.setString(2, player.getCountry());
            pr.setInt(3, player.getRate());
            pr.setTimestamp(4, player.getBirth());
            pr.setBoolean(5, player.isRightFooted());
            pr.setBoolean(6, player.isHealth());
            pr.setInt(7, position);
            pr.setBoolean(8, player.isInMainTeam());
            pr.setInt(9, player.getPlayerId());
            pr.executeUpdate();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletePlayer (Player player) {
        try {
            PreparedStatement pr = connection.prepareStatement("DELETE FROM  player\n" +
                    "WHERE player_id = ?");
            pr.setInt(1, player.getPlayerId());
            pr.execute();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int newGame (Game game) {
        int eventId = 0;
        try {
            PreparedStatement pr = connection.prepareStatement("INSERT INTO event (name, event_date, \n" +
                    "istraining) VALUES \n" +
                    "(?, ?, ?)\n" +
                    "RETURNING event_id");
            pr.setString(1, game.getEventName());
            pr.setTimestamp(2, game.getEventDate());
            pr.setBoolean(3, false);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                eventId = rs.getInt(1);
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventId;
    }

    public static Game getGame (Game game) {
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT name, event_date\n" +
                    "FROM event WHERE event_id = ?");
            pr.setInt(1, game.getEventId());
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                game.setEventName(rs.getString(1));
                game.setEventDate(rs.getTimestamp(2));
                game.setScore(getScore(game));
            } else {
                game = null;
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return game;
    }

    public static Score getScore (Game game) {
        Score score = null;
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT ourgoals, theirgoals\n" +
                    "FROM score WHERE game_id = ?");
            pr.setInt(1, game.getEventId());
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                score = new Score(rs.getInt(1), rs.getInt(2));
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return score;
    }

    public static void deleteEvent(Event event) {
        try {
            PreparedStatement pr = connection.prepareStatement("DELETE FROM event\n" +
                    "WHERE event_id = ?");
            pr.setInt(1, event.getEventId());
            pr.execute();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteScore (Game game) {
        try {
            PreparedStatement pr = connection.prepareStatement("DELETE FROM score\n" +
                    "WHERE event_id = ?");
            pr.setInt(1, game.getEventId());
            pr.execute();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newScore (Game game) {
        try {
            PreparedStatement pr = connection.prepareStatement("INSERT INTO score (ourgoals, theirgoals, \n" +
                    "game_id) VALUES \n" +
                    "(?, ?, ?)");
            Score score = game.getScore();
            pr.setInt(1, score.getOurGoals());
            pr.setInt(2, score.getTheirGoals());
            pr.setInt(3, game.getEventId());
            pr.execute();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateScore (Game game) {
        Score score = game.getScore();
        try {
            PreparedStatement pr = connection.prepareStatement("UPDATE score SET ourgoals = ?, theirgoals = ?\n" +
                    "WHERE game_id = ?");
            pr.setInt(1, score.getOurGoals());
            pr.setInt(2, score.getTheirGoals());
            pr.setInt(3, game.getEventId());
            pr.executeUpdate();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateGame (Game game) {
        PreparedStatement pr = null;
        try {
             pr = connection.prepareStatement("UPDATE event SET name = ?, event_date = ?\n" +
                    "WHERE event_id = ?");
            pr.setString(1, game.getEventName());
            pr.setTimestamp(2, game.getEventDate());
            pr.setInt(3, game.getEventId());
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }   finally {
            close(pr);
        }
    }




    public static void updateTraining (Training training, int typeID) {
        PreparedStatement pr = null;
        try {
            pr = connection.prepareStatement("UPDATE event SET training_type = ?, event_date = ?\n" +
                    "WHERE event_id = ?");
            pr.setInt(1, typeID);
            pr.setTimestamp(2, training.getEventDate());
            pr.setInt(3, training.getEventId());
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }   finally {
            close(pr);
        }
    }

    public static int newTraining (Training training, int type) {
        int eventId = 0;
        try {
            PreparedStatement pr = connection.prepareStatement("INSERT INTO event (name, event_date, \n" +
                    "istraining, training_type) VALUES \n" +
                    "(?, ?, ?, ?)\n" +
                    "RETURNING event_id");
            pr.setString(1, "Training");
            pr.setTimestamp(2, training.getEventDate());
            pr.setBoolean(3, true);
            pr.setInt(4, type);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                eventId = rs.getInt(1);
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventId;
    }

    public static int getTrainingTypeId (String trainingTypeName) {
        int trainingTypeId = 0;
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT training_type_id\n" +
                    "FROM training_type WHERE UPPER (training_name)= UPPER (?)");
            pr.setString(1, trainingTypeName);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                trainingTypeId = rs.getInt(1);
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainingTypeId;
    }

    public static String getTrainingTypeName (int trainingTypeID) {
        String name = null;
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT training_name\n" +
                    "FROM training_type WHERE training_type_id = ?");
            pr.setInt(1, trainingTypeID);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                name = rs.getString(1);
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public static Training getTraining (Training training) {
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT event.name, event.event_date, training_type.training_name\n" +
                    "FROM event JOIN training_type ON (event.training_type = training_type.training_type_id )\n" +
                    "WHERE event.event_id = ?");
            pr.setInt(1, training.getEventId());
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                training.setEventName(rs.getString(1));
                training.setEventDate(rs.getTimestamp(2));
                training.setTrainingType(rs.getString(3));
            } else {
                training = null;
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return training;
    }

    public static ArrayList<String> getTrainingsTypes() {
        ArrayList<String> trainings = new ArrayList<String>();
        ResultSet rs = null;
        PreparedStatement pr = null;
        try {
            pr = connection.prepareStatement("SELECT training_type_id, training_name\n" +
                    "FROM training_type\n" +
                    "ORDER BY training_type_id");
            rs = pr.executeQuery();
            while (rs.next()) {
                trainings.add(rs.getString(2));
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pr, rs);
        }
        return trainings;
    }


    public static Event getDayEvent (Timestamp timestamp) {
        try {
            PreparedStatement pr = connection.prepareStatement("SELECT event_id, istraining\n" +
                    "FROM event\n" +
                    "WHERE event_date = ?");
            pr.setTimestamp(1, timestamp);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                if (rs.getBoolean(2)) {
                    return new Training(rs.getInt(1));
                } else {
                    return new Game(rs.getInt(1));
                }
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}