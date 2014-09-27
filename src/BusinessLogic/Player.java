package BusinessLogic;

import DataAccess.DBProcessor;

import java.sql.Timestamp;

public class Player {
    private String name;
    private String country;
    private String position;
    private int rate = 0;
    private Timestamp birth;
    private boolean isRightFooted; // Сильная нога
    private boolean isHealth; // Травма
    private int playerId;

    public Player(int playerId) {
        this.playerId = playerId;
        if (playerId != 0) { //уже существующий игрок
            Player tempPlayer = DBProcessor.getPlayer(this);
            if (tempPlayer != null) { //если нул - игрока нет
                name = tempPlayer.getName();
                country = tempPlayer.getCountry();
                position = tempPlayer.getPosition();
                rate = tempPlayer.getRate();
                birth = tempPlayer.getBirth();
                isRightFooted = tempPlayer.isRightFooted();
                isHealth = tempPlayer.isHealth;
            }
        }
    }

    public boolean save () {
        if (playerId == 0) { //новый игрок
            int newPlayerId = DBProcessor.newPlayer(this);
            if (playerId == 0) {
                return false;
            } else {
                playerId = newPlayerId;
                return true;
            }
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Timestamp getBirth() {
        return birth;
    }

    public void setBirth(Timestamp birth) {
        this.birth = birth;
    }

    public boolean isRightFooted() {
        return isRightFooted;
    }

    public void setRightFooted(boolean isRightFooted) {
        this.isRightFooted = isRightFooted;
    }

    public boolean isHealth() {
        return isHealth;
    }

    public void setHealth(boolean isHealth) {
        this.isHealth = isHealth;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
