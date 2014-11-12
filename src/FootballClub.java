import DataAccess.DBProcessor;
import GUI.StartWindow;
import HttpLayer.HttpServer;

public class FootballClub {
    public static void main (String args[]){
        DBProcessor.connect();
        StartWindow startWindow = new StartWindow(null);
        startWindow.start();
        HttpServer httpServer = new HttpServer();
        new Thread(httpServer).start();
    }
}
