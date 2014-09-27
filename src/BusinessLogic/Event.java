package BusinessLogic;

import java.sql.Timestamp;

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

}
