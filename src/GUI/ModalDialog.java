package GUI;

import javax.swing.*;

public class ModalDialog {
    public static void showEror (JFrame frame, String text) {
        JOptionPane.showMessageDialog(frame,
                text,
                "Error!",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void showComplete (JFrame frame, String text) {
        JOptionPane.showMessageDialog(frame,
                text,
                "Nice!",
                JOptionPane.PLAIN_MESSAGE);
    }

    public static int showQuestion (JFrame frame, String text) {
        return JOptionPane.showConfirmDialog(frame,
                    text,
                    "Question",
                     JOptionPane.YES_NO_OPTION);
    }
}
