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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

/**
 *
 * @author homeguard
 */
public class EditGameWindow extends javax.swing.JFrame {
    private LoginServ loginServ;
    private int gameID;
    /**
     * Creates new form games
     */
    private void backButtonActionPerformed(ActionEvent e) {
        this.dispose();
        EventWindow eventWindow = new EventWindow(loginServ);
        eventWindow.start();
    }

    private void createUIComponents() {
        // TODO: add custom component creation code here
    }

    private void saveButtonActionPerformed(ActionEvent e) {
        Timestamp ts = loginServ.getTimestampFromString(dateField.getText());
        if (ts != null) {
            String our = ourField.getText();
            String their = theirField.getText();
            int resOur = -1;
            int resTheir = -1;
            if (!our.isEmpty() && our != null && !their.isEmpty() && their != null) {
                resOur = Integer.valueOf(our);
                resTheir = Integer.valueOf(their);
            }
            loginServ.updateGame(nameField.getText(), ts, gameID, resOur, resTheir);
            ModalDialog.showComplete(this, "Updated");
        } else {
            ModalDialog.showEror(this, "Wrong date");
        }


    }

    public EditGameWindow(LoginServ loginServ, int gameID) {
        this.loginServ = loginServ;
        this.gameID = gameID;
        initComponents();
    }

    /**
     *
     setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
     Container contentPane = getContentPane();
     Object[] params = loginServ.getGameNameDate(gameID);
     nameField.setText((String)params[0]);
     dateField.setText(((String)params[1]).substring(0,10));
     if ((Integer)params[2] != -1) {
     ourField.setText(((Integer) params[2]).toString());
     theirField.setText(((Integer)params[3]).toString());
     }
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - Anton Vsegda
    private void initComponents() {
        backButton = new JButton();
        nameField = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        dateField = new JTextField();
        label3 = new JLabel();
        saveButton = new JButton();
        ourField = new JTextField();
        theirField = new JTextField();
        label4 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        Object[] params = loginServ.getGameNameDate(gameID);
        nameField.setText((String)params[0]);
        dateField.setText(((String)params[1]).substring(0,10));
        if ((Integer)params[2] != -1) {
            ourField.setText(((Integer) params[2]).toString());
            theirField.setText(((Integer)params[3]).toString());
        }

        //---- backButton ----
        int a = 2;
        backButton.setText("\u043d\u0430\u0437\u0430\u0434");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backButtonActionPerformed(e);
            }
        });

        //---- label1 ----
        label1.setText("\u0420\u0435\u0434\u0430\u043a\u0442\u0438\u0440\u043e\u0432\u0430\u043d\u0438\u0435 \u0438\u0433\u0440\u044b");

        //---- label2 ----
        label2.setText("\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435");

        //---- label3 ----
        label3.setText("\u0414\u0430\u0442\u0430");

        //---- saveButton ----
        saveButton.setText("\u0421\u043e\u0445\u0440\u0430\u043d\u0438\u0442\u044c");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveButtonActionPerformed(e);
            }
        });

        //---- label4 ----
        label4.setText("\u0420\u0435\u0437\u0443\u043b\u044c\u0442\u0430\u0442 \u043c\u0430\u0442\u0447\u0430");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(label2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 292, Short.MAX_VALUE)
                        .addComponent(label3)
                        .addGap(120, 120, 120))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(backButton)
                    .addGap(116, 116, 116)
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(225, Short.MAX_VALUE))
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(41, 41, 41)
                                    .addComponent(nameField, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(213, 213, 213)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                            .addGroup(contentPaneLayout.createSequentialGroup()
                                                    .addComponent(ourField, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(theirField, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
                                            .addComponent(saveButton, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                            .addComponent(label4, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                    .addComponent(dateField, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                    .addGap(73, 73, 73))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(backButton)
                                    .addGap(58, 58, 58)
                                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(label2)
                                            .addComponent(label3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGap(29, 29, 29)
                                    .addComponent(label1)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label4)
                                    .addGap(16, 16, 16)))
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(ourField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(theirField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(dateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(56, 56, 56)
                    .addComponent(saveButton)
                    .addGap(52, 52, 52))
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
            java.util.logging.Logger.getLogger(EditGameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditGameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditGameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditGameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditGameWindow(loginServ, gameID).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Anton Vsegda
    private JButton backButton;
    private JTextField nameField;
    private JLabel label1;
    private JLabel label2;
    private JTextField dateField;
    private JLabel label3;
    private JButton saveButton;
    private JTextField ourField;
    private JTextField theirField;
    private JLabel label4;
    // End of variables declaration//GEN-END:variables
}
