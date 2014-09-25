package BusinessLogic;

import java.sql.Date;

public class Player {
    private String name;
    private String country;
    public enum PositionType { // описываем тип позиций игрока
        Goalkeeper,
        MidDefender,
        RightDefender,
        LeftDefender,
        Midfielder,
        RightMidfielder,
        LeftMidfielder,
        HoldingMidfielder,
        Forward,
        Striker
    };

    private PositionType position;
    private int rate;
    private Date birth;
    private boolean isRightFooted; // Сильная нога
    private boolean isHealth; // Травма
}
