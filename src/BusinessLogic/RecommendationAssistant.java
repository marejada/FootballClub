package BusinessLogic;

import DataAccess.DBProcessor;
import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.util.ArrayList;

public class RecommendationAssistant {

    private static int [] noGameTrain = {4,1,0,2,3,1};
    private static int [] oneGameTrain = {1,0,2,3,1};
    private static int [] twoGameTrain = {1,0,2,1};

    public static int trainList(int trainCounter, int curNumber) {
        switch (trainCounter) {
            case 0 : {
                return noGameTrain[curNumber];
            }
            case 1 : {
                return oneGameTrain[curNumber];
            }
            case 2 : {
                return twoGameTrain[curNumber];
            }
        }
        return 0;
    }


    public static int getTrainCount (Timestamp today) {
        Timestamp currentStamp = today;
        int counter = 0;
        for (int i = 1; i <= 7; i ++) {
            Event event = DBProcessor.getDayEvent(currentStamp);
            if (event != null) {
                if (!event.isTraining) {
                    counter ++;
                }
            }
            DateTime dateTime = new DateTime(currentStamp);
            dateTime = dateTime.plusDays(1);
            currentStamp = new Timestamp(dateTime.getMillis());
        }
        return counter;
    }

    public static void getRecommendation(Timestamp monday) {   //нужно передать понедельник
        int trainCount = getTrainCount(monday);
        int cuttrainCount = 0;
        Timestamp currentStamp = monday;
        String lastTrainingType = null;
        for (int i = 1; i < 7; i ++) {
            int type;
            Event event = DBProcessor.getDayEvent(currentStamp);
            if (event == null) { //нужно поставить тренировку
                if (lastTrainingType == null) { // если не известна последняя тренировка
                    lastTrainingType = getLastTrainingType(currentStamp);
                    if (lastTrainingType == null) { //берем первую тренировку
                        lastTrainingType = DBProcessor.getTrainingTypeName(trainList(trainCount, 0));
                        type = trainList(trainCount, 0);
                    } else {
                        type = DBProcessor.getTrainingTypeId(lastTrainingType);
                    }
                }else {//высчитываем следующую тренировку
                    cuttrainCount ++;
                    boolean isRight = true;
                    switch (trainCount) {
                        case 1 : {
                            if (cuttrainCount >= 5) {
                                isRight = false;
                            }
                            break;
                        }
                        case 2 : {
                            if (cuttrainCount >= 4) {
                                isRight = false;
                            }
                            break;
                        }
                    }
                    if (!isRight) {
                        continue;
                    }
                    type = trainList(trainCount, cuttrainCount);
                    lastTrainingType = DBProcessor.getTrainingTypeName(trainList(trainCount, cuttrainCount));
                }
                System.out.println("Ставим тренировку " + currentStamp + " " + lastTrainingType );
                Training training = new Training(0);
                training.setEventDate(currentStamp);
                DBProcessor.newTraining(training, type);
            } else {
                if (event.isTraining()) { //если тренировка
                    lastTrainingType = ((Training) event).getTrainingType();
                    cuttrainCount ++;
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

           //правша-левша
           if (playerToSwitch.isRightFooted() && player.isRightFooted()) {
               raiting ++;
           }

           if (!playerToSwitch.isRightFooted() && !player.isRightFooted()) {
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
