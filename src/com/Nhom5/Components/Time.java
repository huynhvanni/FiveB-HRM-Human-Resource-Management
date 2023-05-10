/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.Nhom5.Components;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author hvn45
 */
public class Time extends javax.swing.JPanel {

    /**
     * Creates new form Time
     */
    public Time() {
        initComponents();
        time();
    }

    public void time(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int hour, second, minute;
                int day, month, year;
                String timeStr, yearStr;
                while (true) {
                    try {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR_OF_DAY);
                if (hour > 12) {
                    hour = hour - 12;
                }
                minute = c.get(Calendar.MINUTE);
                second = c.get(Calendar.SECOND);
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
                SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyy");
                Date dat = c.getTime();
                timeStr = sdf.format(dat);
                yearStr = df.format(dat);
                time.setText(timeStr);
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
            }
        }).start();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        time = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(155, 32));
        setMinimumSize(new java.awt.Dimension(155, 32));
        setPreferredSize(new java.awt.Dimension(155, 32));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        time.setBackground(new java.awt.Color(255, 255, 255));
        time.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        time.setForeground(new java.awt.Color(236, 56, 188));
        time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        time.setText("Time");
        add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 155, 32));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
