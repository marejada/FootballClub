import BusinessLogic.Player;
import DataAccess.DBProcessor;

public class FootballClub {
    public static void main (String args[]){
        DBProcessor.connect();
//        Player player = new Player(0);
//        player.setName("Имя");
//        player.setCountry("Страна");
//        player.setBirth(new Timestamp(123));
//        player.setRightFooted(true);
//        player.setHealth(true);
//        player.setPosition("Goalkeeper");
//        player.save();

        Player player = new Player(1);

    }
}
