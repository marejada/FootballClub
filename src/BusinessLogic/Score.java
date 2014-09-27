package BusinessLogic;

public class Score {
    private int ourGoals;
    private int theirGoals;

    public Score(int ourGoals, int theirGoals) {
        this.ourGoals = ourGoals;
        this.theirGoals = theirGoals;
    }

    public int getOurGoals() {
        return ourGoals;
    }

    public void setOurGoals(int ourGoals) {
        this.ourGoals = ourGoals;
    }

    public int getTheirGoals() {
        return theirGoals;
    }

    public void setTheirGoals(int theirGoals) {
        this.theirGoals = theirGoals;
    }

}
