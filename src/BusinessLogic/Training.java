package BusinessLogic;

public class Training extends Event {
    public enum TrainingType { // типы тренировок
        Tactics,
        Workout,
        Technique,
        Sparring,
        Stamina
    };
    private TrainingType trainingType;
}
