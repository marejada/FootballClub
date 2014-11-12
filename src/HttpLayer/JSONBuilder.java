package HttpLayer;

import BusinessLogic.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class JSONBuilder {
    private static JSONObject mainObj = new JSONObject();
    public static JSONObject prepareAndGetJSON (int playerID) {
        Player player = new Player(playerID);
        if (player == null) {
            return null;
        }
        JSONArray playerInfo = new JSONArray();
        playerInfo.add(player.getPlayerId());
        playerInfo.add(player.getName());
        playerInfo.add(player.getBirth());
        playerInfo.add(player.getCountry());
        playerInfo.add(player.getPosition());

        mainObj.put("player", playerInfo);
        return  mainObj;
    }
}
