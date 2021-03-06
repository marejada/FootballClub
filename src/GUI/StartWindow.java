package GUI;/*

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

import java.awt.*;
import java.awt.event.*;
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

/**
 *
 * @author homeguard
 */
public class StartWindow extends javax.swing.JFrame {
     private LoginServ loginServ;
    /**
     * Creates new form start
     */
    private void managerEnterActionPerformed(ActionEvent e) {
        this.dispose();
        LoginWindow loginWindow = new LoginWindow(loginServ);
        loginWindow.start();
    }

    private void anonEnterButtonActionPerformed(ActionEvent e) {
        this.dispose();
        MenuWindow menuWindow = new MenuWindow(loginServ);
        menuWindow.start();
    }

    public StartWindow(LoginServ loginServ) {
        initComponents();
        if (loginServ == null) {
            loginServ = new LoginServ(); //get from rmi
        }
        this.loginServ = loginServ;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    // Generated using JFormDesigner Evaluation license - Anton Vsegda
    private void initComponents() {
        anonEnterButton = new JButton();
        managerEnterButton = new JButton();
        label1 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();

        //---- anonEnterButton ----
        anonEnterButton.setText("\u0412\u043e\u0439\u0442\u0438 \u043a\u0430\u043a \u0413\u043e\u0441\u0442\u044c");
        anonEnterButton.setMaximumSize(new Dimension(139, 23));
        anonEnterButton.setMinimumSize(new Dimension(139, 23));
        anonEnterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anonEnterActionPerformed(e);
                anonEnterButtonActionPerformed(e);
            }
        });

        //---- managerEnterButton ----
        managerEnterButton.setText("\u0412\u043e\u0439\u0442\u0438 \u043a\u0430\u043a \u041c\u0435\u043d\u0435\u0434\u0436\u0435\u0440");
        managerEnterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerEnterActionPerformed(e);
            }
        });

        //---- label1 ----
        label1.setText("\u0414\u043e\u0431\u0440\u043e \u043f\u043e\u0436\u0430\u043b\u043e\u0432\u0430\u0442\u044c");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(anonEnterButton, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
                            .addGap(30, 30, 30)
                            .addComponent(managerEnterButton))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(138, 138, 138)
                            .addComponent(label1)))
                    .addContainerGap(31, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(54, 54, 54)
                    .addComponent(label1)
                    .addGap(35, 35, 35)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(anonEnterButton, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                        .addComponent(managerEnterButton, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(96, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
    }// </editor-fold>//GEN-END:initComponents

    private void anonEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    public void start() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartWindow(loginServ).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Anton Vsegda
    private JButton anonEnterButton;
    private JButton managerEnterButton;
    private JLabel label1;
    // End of variables declaration//GEN-END:variables
}
