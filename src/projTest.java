import BusinessLogic.Game;
import BusinessLogic.Player;
import ServiceLayer.LoginServ;
import org.junit.Test;

public class projTest {

    @Test
    public void testHealth() throws Exception {
        Player player = new Player(3);
        boolean old = player.isHealth();
        LoginServ loginServ = new LoginServ();
        loginServ.switchPlayerHealth(player.getPlayerId());
        player = new Player(3);
        boolean newB = player.isHealth();
        if (old != newB) {
            System.out.println("OK");
        }
    }

    public void updateGame() throws Exception {
        Game game = new Game(1);
        String oldName = game.getEventName();
        game.setEventName("ololo");
        game.save();
        game = new Game(1);
        String newName = game.getEventName();

        if (!oldName.equals(newName)) {
            System.out.println("OK");
        }
    }
}