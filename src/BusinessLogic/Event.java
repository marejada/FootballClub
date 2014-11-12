package BusinessLogic;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Event {
    protected String eventName;
    protected boolean isTraining;
    protected Timestamp eventDate;
    protected int eventId;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public boolean isTraining() {
        return isTraining;
    }

    public void setTraining(boolean isTraining) {
        this.isTraining = isTraining;
    }

    public Timestamp getEventDate() {
        return eventDate;
    }

    public void setEventDate(Timestamp eventDate) {
        this.eventDate = eventDate;
    }

    public int getEventId() {
        return eventId;
    }

    public ArrayList<Object> getEventString () {
        ArrayList <Object> res = new ArrayList<Object>();
        res.add(eventId);
        res.add(new Date(eventDate.getTime()));
        if (isTraining) {
            res.add("Training");
            Training training = (Training) this;
            res.add(training.getTrainingType());
        } else {
            res.add("Game");
            String about = eventName;
            Game game = (Game) this;
            Score score = game.getScore();
            if (score != null) {
                 about += " " + score.getOurGoals() + " : " + score.getTheirGoals();
            }
            res.add(about);
        }
        return res;
    }
}
