package BusinessLogic;

import DataAccess.DBProcessor;

public class Training extends Event {
    private String trainingType;
    Training (int trainingId) {
        isTraining = true;
        if (trainingId != 0) { //уже есть такая
            eventId = trainingId;
        }
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public void save () {
        int newEventId = DBProcessor.newTraining(this);
        this.eventId = newEventId;
    }

    public void  deleteTraining () {
        DBProcessor.deleteEvent(this);
    }
}
