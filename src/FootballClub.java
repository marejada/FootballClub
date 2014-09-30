import BusinessLogic.Event;
import BusinessLogic.Game;
import BusinessLogic.Training;
import DataAccess.DBProcessor;
import org.joda.time.DateTime;

import java.sql.Timestamp;

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
        Game event = new Game(2);
        getRecomendation(event.getEventDate());
    }

    public static void getRecomendation(Timestamp monday) {   //нужно передать понедельник
        Timestamp currentStamp = monday;
        String lastTrainingType = null;
        for (int i = 1; i < 7; i ++) {
            Event event = DBProcessor.getDayEvent(currentStamp);
            if (event == null) { //нужно поставить тренировку
                if (lastTrainingType == null) { // если не известна последняя тренировка
                    lastTrainingType = getLastTrainingType(currentStamp);
                    if (lastTrainingType == null) { //берем первую тренировку
                        lastTrainingType = DBProcessor.getTrainingTypeName(1);
                    }
                }else {//высчитываем следующую тренировку
                    int trainingTypeID = DBProcessor.getTrainingTypeId(lastTrainingType);
                    lastTrainingType = DBProcessor.getTrainingTypeName(trainingTypeID + 1); //берем следующую тренировку
                    if (lastTrainingType == null) { //если завершен полный цикл тренировок
                        lastTrainingType = DBProcessor.getTrainingTypeName(1);
                    }
                }
                System.out.println("Ставим тренировку " + currentStamp + " " + lastTrainingType );
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
}
