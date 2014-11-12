package BusinessLogic;

import DataAccess.DBProcessor;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Player {
    private String name;
    private String country;
    private String position;
    private int rate = 0;
    private Timestamp birth;
    private boolean isRightFooted; // Сильная нога
    private boolean isHealth; // Травма
    private int playerId;
    private boolean isInMainTeam;

    public ArrayList<Object> getPlayerString () {
        ArrayList<Object> player = new ArrayList<Object>();
        player.add(playerId);
        player.add(name);
        player.add(country);
        player.add(position);
        player.add(rate);
        player.add(isRightFooted);
        player.add(isHealth);
        player.add(isInMainTeam);
        return player;
    }

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

    public Player(String name, String country,
                  String position, int rate,
                  Timestamp birth, boolean isRightFooted,
                  int playerId, boolean isInMainTeam,
                  boolean isHealth) {
        this.name = name;
        this.country = country;
        this.position = position;
        this.rate = rate;
        this.birth = birth;
        this.isRightFooted = isRightFooted;
        this.isHealth = isHealth;
        this.playerId = playerId;
        this.isInMainTeam = isInMainTeam;
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
        } else  {
            DBProcessor.updatePlayer(this);
        }
        return true;
    }

    public void deletePlayer () {
        DBProcessor.deletePlayer(this);
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

    public boolean isInMainTeam() {
        return isInMainTeam;
    }

    public void setInMainTeam(boolean isInMainTeam) {
        this.isInMainTeam = isInMainTeam;
    }


}
