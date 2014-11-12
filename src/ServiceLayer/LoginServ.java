package ServiceLayer;

import BusinessLogic.*;
import DataAccess.DBProcessor;
import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Timestamp;
import java.util.ArrayList;

public class LoginServ {
    private User user = null;

    public boolean loginUser (String login, String password) {
        user = new User(login, password, true);
        user = DBProcessor.loginUser(user);
        if (user != null) {
            return true;
        }else {
            return false;
        }
    }

    public Object[][] getPlayers () {
        ArrayList <Player> players = DBProcessor.getAllPlayers();
        ArrayList<ArrayList<Object>> playerString = new ArrayList<ArrayList<Object>>();
        for (Player player : players) {
            playerString.add(player.getPlayerString());
        }

        Object[][] array = new Object[playerString.size()][];
        for (int i = 0; i < playerString.size(); i++) {
            ArrayList<Object> row = playerString.get(i);
            array[i] = row.toArray(new Object[row.size()]);
        }
        return array;
    }

    public Object[][] getEvents () {
        DateTime dateTime = new DateTime();
        dateTime = dateTime.secondOfMinute().setCopy(0);
        dateTime = dateTime.minuteOfHour().setCopy(0);
        dateTime = dateTime.hourOfDay().setCopy(0);
        dateTime =dateTime.millisOfSecond().setCopy(0);
        ArrayList<ArrayList<Object>> eventString = new ArrayList<ArrayList<Object>>();
        for (int i = 0; i <= 14; i++) {
            Event event = DBProcessor.getDayEvent(new Timestamp(dateTime.plusDays(i).getMillis()));
            if (event == null) {
                continue;
            }
            eventString.add(event.getEventString());
        }
        Object[][] array = new Object[eventString.size()][];
        for (int i = 0; i < eventString.size(); i++) {
            ArrayList<Object> row = eventString.get(i);
            array[i] = row.toArray(new Object[row.size()]);
        }
        return array;
    }

    public Object[] getGameNameDate (int gameID) {
        Game game = new Game(gameID);
        Score score = game.getScore();
        int ourGoals = -1;
        int theirGoals = -1;
        if (score != null) {
            ourGoals = score.getOurGoals();
            theirGoals = score.getTheirGoals();
        }
        Object[] res = {game.getEventName(), game.getEventDate().toString(), ourGoals, theirGoals};
        return res;
    }

    public void updateGame (String name, Timestamp date, int gameID, int ourGoals, int theirGoals) {
        Game game = new Game(gameID);
        game.setEventName(name);
        game.setEventDate(date);
        if (ourGoals != -1 && theirGoals != -1) {
            Score score = new Score(ourGoals, theirGoals);
            if (game.getScore() != null) {
                game.updateScore(score);
            } else {
                game.addNewScore(score);
            }
        }
        game.save();
    }

    public Timestamp getTimestampFromString (String date) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime dt = null;
        try {
            dt = formatter.parseDateTime(date);
            dt = dt.millisOfSecond().setCopy(0);
            dt = dt.secondOfMinute().setCopy(0);
            dt = dt.minuteOfHour().setCopy(0);
            dt = dt.hourOfDay().setCopy(0);
        }catch (IllegalFieldValueException e) {
            return null;
        }
        return new Timestamp(dt.getMillis());
    }

    public String[] getTrainingTypes() {
        ArrayList<String> types = DBProcessor.getTrainingsTypes();
        String[] array = types.toArray(new String[0]);
        return array;
    }

    public int getTrainingTypeID (int trainingID) {
        Training training = new Training(trainingID);
        String typeName = training.getTrainingType();
        return DBProcessor.getTrainingTypeId(typeName);
    }

    public void updateTraining (int trainingID, Timestamp date, int type) {
        Training training = new Training(trainingID);
        training.setEventDate(date);
        DBProcessor.updateTraining(training, type);
    }

    public String getTrainingDate (int trainingID) {
        Training training = new Training(trainingID);
        return training.getEventDate().toString();
    }

    public void createGame(String name, Timestamp date) {
        Game game = new Game(0);
        game.setEventName(name);
        game.setEventDate(date);
        game.save();
    }

    public void createTraining (Timestamp date, int type) {
      Training training = new Training(0);
      training.setEventDate(date);
      training.save(type);
    }

    public void fiilTrain () {
        DateTime dt = new DateTime();
        dt = dt.millisOfSecond().setCopy(0);
        dt = dt.secondOfMinute().setCopy(0);
        dt = dt.minuteOfHour().setCopy(0);
        dt = dt.hourOfDay().setCopy(0);
        RecommendationAssistant.getRecommendation(new Timestamp(dt.getMillis()));
    }

    public void deleteGame(int eventID) {
        Game game = new Game(eventID);
        DBProcessor.deleteEvent(game);
    }

    public void deleteTrain(int eventID) {
        Training training = new Training(eventID);
        DBProcessor.deleteEvent(training);
    }

    public Object[][] getPLayersToSwitch (int playerID) {
        Player player = new Player(playerID);
        ArrayList<Player> players = RecommendationAssistant.recommendPlayer(player);
        ArrayList<ArrayList<Object>> playerString = new ArrayList<ArrayList<Object>>();
        for (Player pl : players) {
            ArrayList <Object> innerObj = new ArrayList<Object>();
            innerObj.add(pl.getPlayerId());
            innerObj.add(pl.getName());
            innerObj.add(pl.getRate());
            playerString.add(innerObj);
        }

        Object[][] array = new Object[playerString.size()][];
        for (int i = 0; i < playerString.size(); i++) {
            ArrayList<Object> row = playerString.get(i);
            array[i] = row.toArray(new Object[row.size()]);
        }
        return array;
    }

    public void updatePlayer (int oldPlayerID, int newPlayerID) {
        Player oldPlayer = new Player(oldPlayerID);
        oldPlayer.setInMainTeam(false);
        oldPlayer.save();

        Player newPlayer = new Player(newPlayerID);
        newPlayer.setInMainTeam(true);
        newPlayer.save();
    }

    public void switchPlayerHealth (int playerID) {
        Player player = new Player(playerID);
        player.setHealth(!player.isHealth());
        player.save();
    }

    public boolean isAdmin () {
        if (user == null) {
            return false;
        } else {
            return true;
        }

    }
}
