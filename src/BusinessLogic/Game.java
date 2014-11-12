package BusinessLogic;

import DataAccess.DBProcessor;

public class Game extends Event {
    private Score score;

    public Game(int eventId) {
        isTraining = false;
        if (eventId != 0) { // если уже есть
            this.eventId = eventId;
            Game newGame = DBProcessor.getGame(this);
            eventName = newGame.getEventName();
            eventDate = newGame.getEventDate();
            score = newGame.getScore();
        }
    }

    public void save () {
        if (eventId != 0) {
            DBProcessor.updateGame(this);
        } else {
            int newEventId = DBProcessor.newGame(this);
            this.eventId = newEventId;
        }
    }

    public void deleteGame () {
        DBProcessor.deleteEvent(this);
        DBProcessor.deleteScore(this);
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public void updateScore(Score score) {
        this.score = score;
        DBProcessor.updateScore(this);
    }

    public void addNewScore (Score score) {
        this.score = score;
        DBProcessor.newScore(this);
    }
}
