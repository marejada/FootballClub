package BusinessLogic;

import DataAccess.DBProcessor;

import java.util.ArrayList;

public class Team {
    private ArrayList<Player> mainLineUp = new ArrayList<Player>();
    private ArrayList<Player> spareLineUp = new ArrayList<Player>();

    Team() {
        fiiTeams ();
    }

    public void fiiTeams () {
        ArrayList<Player> allPlayers = DBProcessor.getAllPlayers();
        for (Player player : allPlayers) {
            if (player.isInMainTeam()) {
                mainLineUp.add(player);
            }else {
                spareLineUp.add(player);
            }
        }
    }

    public ArrayList<Player> getMainLineUp() {
        return mainLineUp;
    }

    public ArrayList<Player> getSpareLineUp() {
        return spareLineUp;
    }
}
