package BusinessLogic;

import java.sql.Date;

public class Event {
    private String eventName;
    public enum EventType { // типы событий
        Game,
        Training
    };

    private EventType eventType;
    private Date eventDate;
}
