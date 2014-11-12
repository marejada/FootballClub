package BusinessLogic;

import DataAccess.DBProcessor;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.util.ArrayList;

public class RecommendationAssistant {
    public static void getRecommendation(Timestamp monday) {   //нужно передать понедельник
        Timestamp currentStamp = monday;
        String lastTrainingType = null;
        for (int i = 1; i < 7; i ++) {
            int type;
            Event event = DBProcessor.getDayEvent(currentStamp);
            if (event == null) { //нужно поставить тренировку
                if (lastTrainingType == null) { // если не известна последняя тренировка
                    lastTrainingType = getLastTrainingType(currentStamp);
                    if (lastTrainingType == null) { //берем первую тренировку
                        lastTrainingType = DBProcessor.getTrainingTypeName(1);
                        type = 1;
                    } else {
                        type = DBProcessor.getTrainingTypeId(lastTrainingType);
                    }
                }else {//высчитываем следующую тренировку
                    int trainingTypeID = DBProcessor.getTrainingTypeId(lastTrainingType);
                    lastTrainingType = DBProcessor.getTrainingTypeName(trainingTypeID + 1); //берем следующую тренировку
                    type = trainingTypeID + 1;
                    if (lastTrainingType == null) { //если завершен полный цикл тренировок
                        lastTrainingType = DBProcessor.getTrainingTypeName(1);
                        type = 1;
                    }
                }
                System.out.println("Ставим тренировку " + currentStamp + " " + lastTrainingType );
                Training training = new Training(0);
                training.setEventDate(currentStamp);
                DBProcessor.newTraining(training, type);
            } else {
                if (event.isTraining()) { //если тренировка
                    lastTrainingType = ((Training) event).getTrainingType();
                }
            }
            DateTime dateTime = new DateTime(currentStamp);
            dateTime = dateTime.plusDays(1);
            currentStamp = new Timestamp(dateTime.getMillis());  //плюс 1 день
        }
    }

    public static String getLastTrainingType (Timestamp nowStamp) {
        Timestamp currentStamp = nowStamp;
        for (int i = 1; i < 14; i ++) {
            DateTime dateTime = new DateTime(currentStamp);
            dateTime = dateTime.minusDays(1);
            currentStamp = new Timestamp(dateTime.getMillis());  //минус 1 день
            Event event = DBProcessor.getDayEvent(currentStamp);
            if (event != null) {
                if (event.isTraining()) {
                    return ((Training)event).getTrainingType();
                }
            }
        }
        return null;
    }

   public static ArrayList<Player> recommendPlayer (Player playerToSwitch) {
       Team team = new Team();
       ArrayList<Player> players = new ArrayList<Player>();
       int rus = 0;
       for (Player player : team.getMainLineUp()) {   //считаем сколько русских игроков
           if (player.getCountry().equals("Russia")){
               rus ++;
           }
       }

       if (playerToSwitch.getCountry().equals("Russia")) {
           rus --;
       }

       for (Player player: team.getSpareLineUp()) {
           int raiting = 0;
           if (rus < 7 ) { //меняем только на русских
             if (!player.getCountry().equals("Russia")) {
                 continue;
             }
           }
           if (!player.getPosition().equals(playerToSwitch.getPosition())) {
               continue;
           }

           if (!player.isHealth()) {
               continue;
           }

           if (player.getCountry().equals("Russia")) {
               raiting ++;
           }

           raiting += player.getRate();
           if (player.isRightFooted()) {
               raiting ++;
           }

           DateTime dateTime = new DateTime(player.getBirth());
           DateTime currentDate = new DateTime();
           int age = currentDate.getYear() - dateTime.getYear();

           if (age < 30) {
               raiting += 2;
           } else {
               raiting ++;
           }
           player.setRate(raiting);
           //System.out.println(player.getName() + " " + raiting);
           players.add(player);
       }
       return players;
   }
}
