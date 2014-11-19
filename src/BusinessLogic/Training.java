package BusinessLogic;

import DataAccess.DBProcessor;

public class Training extends Event {
    private String trainingType;
    public Training(int trainingId) {
        isTraining = true;
        if (trainingId != 0) { //уже есть такая
            eventId = trainingId;
            Training training = DBProcessor.getTraining(this);
            eventName = training.getEventName();
            eventDate = training.getEventDate();
            trainingType = training.getTrainingType();
        }
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public void save (int type) {
        if (eventId == 0) {
            int newEventId = DBProcessor.newTraining(this, type);
            this.eventId = newEventId;
        }
    }

    public void  deleteTraining () {
        DBProcessor.deleteEvent(this);

    }
}
