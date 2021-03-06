package GUI;/*

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ServiceLayer.LoginServ;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

/**
 *
 * @author homeguard
 */
public class HelperWindow extends javax.swing.JFrame {
    private LoginServ loginServ;
    /**
     * Creates new form recomentrain
     */
    private void gameTraneCheckBoxStateChanged(ChangeEvent e) {
        if (gameTraneCheckBox.isSelected()) {
            trainComboBox.setEnabled(false);
        }  else {
            trainComboBox.setEnabled(true);
        }
    }

    private void backButtonActionPerformed(ActionEvent e) {
        this.dispose();
        EventWindow eventWindow = new EventWindow(loginServ);
        eventWindow.start();
    }

    private void saveButton1ActionPerformed(ActionEvent e) {
        Timestamp date = loginServ.getTimestampFromString(dateField.getText());
        if (date == null) {
            ModalDialog.showEror(this, "Wrong date");
            return;
        }
        if (gameTraneCheckBox.isSelected()){//игра
                loginServ.createGame(nameField.getText(), date);
        } else {
            int selected = trainComboBox.getSelectedIndex();
            if (selected < 0) {
                ModalDialog.showEror(this, "Select type");
                return;
            } else {
                loginServ.createTraining(date, selected);
            }
        }
        ModalDialog.showComplete(this, "Created");
    }

    private void trainButtonActionPerformed(ActionEvent e) {
      loginServ.fiilTrain();
        ModalDialog.showComplete(this, "Complete");
    }

    public HelperWindow(LoginServ loginServ) {
        this.loginServ = loginServ;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - Anton Vsegda
    private void initComponents() {
        saveButton1 = new JButton();
        label1 = new JLabel();
        nameField = new JTextField();
        label2 = new JLabel();
        gameTraneCheckBox = new JCheckBox();
        label3 = new JLabel();
        dateField = new JTextField();
        trainComboBox = new JComboBox();
        label4 = new JLabel();
        trainButton = new JButton();
        backButton = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        //---- saveButton1 ----
        saveButton1.setText("\u0421\u043e\u0437\u0434\u0430\u0442\u044c");
        saveButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButton1ActionPerformed(e);
            }
        });

        //---- label1 ----
        label1.setText("\u0414\u043e\u0431\u0430\u0432\u0438\u0442\u044c \u0441\u043e\u0431\u044b\u0442\u0438\u0435");

        //---- label2 ----
        label2.setText("\u0414\u0430\u0442\u0430");

        //---- gameTraneCheckBox ----
        gameTraneCheckBox.setText("\u0422\u0440\u0435\u043d\u0438\u0440\u043e\u0432\u043a\u0430/\u0418\u0433\u0440\u0430");
        gameTraneCheckBox.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                gameTraneCheckBoxStateChanged(e);
            }
        });

        //---- label3 ----
        label3.setText("\u0422\u0438\u043f \u0442\u0440\u0435\u043d\u0438\u0440\u043e\u0432\u043a\u0438");

        //---- label4 ----
        label4.setText("\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435");

        //---- trainButton ----
        trainButton.setText("\u0417\u0430\u043f\u043e\u043b\u043d\u0438\u0442\u044c \u043d\u0435\u0434\u0435\u043b\u044e \u0442\u0440\u0435\u043d\u0438\u0440\u043e\u0432\u043a\u0430\u043c\u0438");
        trainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trainButtonActionPerformed(e);
            }
        });
        trainComboBox.setModel(new DefaultComboBoxModel(loginServ.getTrainingTypes()));
        //---- backButton ----
        int a = 2;
        backButton.setText("\u043d\u0430\u0437\u0430\u0434");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backButtonActionPerformed(e);
            }
        });

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(gameTraneCheckBox))
                            .addComponent(backButton))
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(102, 102, 102)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
                            .addGap(139, 139, 139)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                            .addGap(56, 56, 56))
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(nameField, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
                                .addComponent(dateField, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(trainComboBox, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
                            .addGap(45, 45, 45))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(54, 54, 54)
                            .addComponent(saveButton1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(trainButton)
                            .addGap(21, 21, 21))))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(200, 200, 200)
                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label1)
                            .addComponent(backButton))
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(51, 51, 51)
                            .addComponent(gameTraneCheckBox))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(41, 41, 41)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label2)
                                .addComponent(label3))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(dateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(trainComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(label4)
                    .addGap(5, 5, 5)
                    .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(saveButton1)
                        .addComponent(trainButton))
                    .addGap(33, 33, 33))
        );
        pack();
        setLocationRelativeTo(getOwner());
    }// </editor-fold>//GEN-END:initComponents

    public void start() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HelperWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HelperWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HelperWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HelperWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HelperWindow(loginServ).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Anton Vsegda
    private JButton saveButton1;
    private JLabel label1;
    private JTextField nameField;
    private JLabel label2;
    private JCheckBox gameTraneCheckBox;
    private JLabel label3;
    private JTextField dateField;
    private JComboBox trainComboBox;
    private JLabel label4;
    private JButton trainButton;
    private JButton backButton;
    // End of variables declaration//GEN-END:variables
}
