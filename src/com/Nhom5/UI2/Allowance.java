/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.Nhom5.UI2;

import com.Nhom5.Entity.User;
import com.Nhom5.Helper.XImage;
import com.Nhom5.datechooser.EventDateChooser;
import com.Nhom5.datechooser.SelectedAction;
import com.Nhom5.datechooser.SelectedDate;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author hvn45
 */
public class Allowance extends javax.swing.JPanel {
    
    User u;
  
    public Allowance() {
        initComponents();
        table();
        checkLimitDate();
    }
    public Allowance(User entity) {
        initComponents();
        table();
        checkLimitDate();
        this.u = entity;
        setInforUser();
    }
    
    // Table
    public void table() {
        setBackground(new Color(255, 255, 255));
        jTable1.getTableHeader().setFont(new Font("Seoge UI", Font.BOLD, 16));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(236,56,188));
        jTable1.getTableHeader().setForeground(new Color(255, 255, 255));
        jTable1.setRowHeight(24);
        jTable1.setSelectionBackground(new Color(84,179,237));
        jTable1.setSelectionForeground(new Color(255, 255, 255));
    }
    // Set infor user
    public void setInforUser() {
        lbl_NameOfUser.setText(u.getName());
        lbl_DutyOfUser.setText(u.getDuty());
        lbl_Avatar.setIcon(XImage.read(u.getImg()));
        

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.Nhom5.datechooser.DateChooser();
        pn_Table = new com.Nhom5.Components.pnGradientColor();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        pn_infoEmployee = new javax.swing.JPanel();
        lbl_Avatar1 = new com.Nhom5.Swing.BoderRadius();
        jLabel5 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        lbl_dateFormat = new javax.swing.JLabel();
        lbl_Date = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        cbb_Department = new javax.swing.JComboBox<>();
        btn_Delete = new javax.swing.JLabel();
        btn_Add = new javax.swing.JLabel();
        btn_Clear = new javax.swing.JLabel();
        btn_Update = new javax.swing.JLabel();
        btn_Last = new javax.swing.JLabel();
        btn_First = new javax.swing.JLabel();
        btn_Prev = new javax.swing.JLabel();
        btn_Next = new javax.swing.JLabel();
        cbb_Department1 = new javax.swing.JComboBox<>();
        lbl_BasicSalary1 = new javax.swing.JTextField();
        lbl_VND1 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        pn_Contact = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbl_NameOfUser = new javax.swing.JLabel();
        lbl_DutyOfUser = new javax.swing.JLabel();
        lbl_Avatar = new com.Nhom5.Swing.BoderRadius();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        dateChooser1.setForeground(new java.awt.Color(236, 56, 188));
        dateChooser1.setTextRefernce(lbl_Date);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Huỳnh Văn Ni", "0123123123", "nihvpc04493@fpt.edu.vn"},
                {"2", "Võ Quốc Tuấn", "0123456789", "tuanvq09999@fpt.edu.vn"},
                {"3", "Bùi Thanh Bùi", "01648163518", "BTB090@gmail.com"},
                {"4", "Philip Lee", "0987654321", "LeDuy@gmail.com"}
            },
            new String [] {
                "STT", "Họ và tên", "Số điện thoại", "Email"
            }
        ));
        jTable1.setFocusable(false);
        jTable1.setGridColor(new java.awt.Color(236, 56, 188));
        jTable1.setIntercellSpacing(new java.awt.Dimension(0, 0));
        jTable1.setRowHeight(25);
        jTable1.setSelectionBackground(new java.awt.Color(84, 179, 237));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout pn_TableLayout = new javax.swing.GroupLayout(pn_Table);
        pn_Table.setLayout(pn_TableLayout);
        pn_TableLayout.setHorizontalGroup(
            pn_TableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_TableLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pn_TableLayout.setVerticalGroup(
            pn_TableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_TableLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        add(pn_Table, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 1610, 560));

        pn_infoEmployee.setBackground(new java.awt.Color(255, 255, 255));
        pn_infoEmployee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pn_infoEmployee.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_Avatar1.setBackground(new java.awt.Color(255, 255, 255));
        lbl_Avatar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Avatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Images/user.png"))); // NOI18N
        lbl_Avatar1.setRoundBottomLeft(130);
        lbl_Avatar1.setRoundBottomRight(130);
        lbl_Avatar1.setRoundTopLeft(130);
        lbl_Avatar1.setRoundTopRight(130);
        pn_infoEmployee.add(lbl_Avatar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 130, 130));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("09-12-2000");
        pn_infoEmployee.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 180, 130, 20));

        jRadioButton1.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setText("Nữ");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        pn_infoEmployee.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 200, -1, -1));

        jRadioButton2.setBackground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setText("Nam");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        pn_infoEmployee.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, -1, -1));

        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        pn_infoEmployee.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(283, 203, 2, 18));

        lbl_dateFormat.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        lbl_dateFormat.setText("(dd-MM-yyyy)");
        lbl_dateFormat.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        lbl_dateFormat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_dateFormatMouseClicked(evt);
            }
        });
        pn_infoEmployee.add(lbl_dateFormat, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 150, -1, -1));

        lbl_Date.setBackground(new java.awt.Color(255, 255, 255));
        lbl_Date.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lbl_Date.setForeground(new java.awt.Color(236, 56, 188));
        lbl_Date.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lbl_Date.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        lbl_Date.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_DateActionPerformed(evt);
            }
        });
        pn_infoEmployee.add(lbl_Date, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 150, 100, 19));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel22.setText("Loại phụ cấp");
        pn_infoEmployee.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, -1, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel23.setText("Số tiền");
        pn_infoEmployee.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, -1, -1));

        cbb_Department.setBackground(new java.awt.Color(253, 239, 249));
        cbb_Department.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        cbb_Department.setMaximumRowCount(20);
        cbb_Department.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_Department.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 224, 239)));
        cbb_Department.setName(""); // NOI18N
        cbb_Department.setOpaque(false);
        pn_infoEmployee.add(cbb_Department, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 130, 22));

        btn_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Delete.png"))); // NOI18N
        btn_Delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_DeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_DeleteMouseExited(evt);
            }
        });
        pn_infoEmployee.add(btn_Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 20, 169, 44));

        btn_Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_SetOverTime.png"))); // NOI18N
        btn_Add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_AddMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_AddMouseExited(evt);
            }
        });
        pn_infoEmployee.add(btn_Add, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, 169, 44));

        btn_Clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Clear.png"))); // NOI18N
        btn_Clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ClearMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ClearMouseExited(evt);
            }
        });
        pn_infoEmployee.add(btn_Clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 80, 169, 44));

        btn_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Update.png"))); // NOI18N
        btn_Update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_UpdateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_UpdateMouseExited(evt);
            }
        });
        pn_infoEmployee.add(btn_Update, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 80, 169, 44));

        btn_Last.setBackground(new java.awt.Color(255, 255, 255));
        btn_Last.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_Last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Last.png"))); // NOI18N
        btn_Last.setToolTipText("");
        btn_Last.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_LastMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_LastMouseExited(evt);
            }
        });
        pn_infoEmployee.add(btn_Last, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 150, 74, 49));

        btn_First.setBackground(new java.awt.Color(255, 255, 255));
        btn_First.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_First.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_First.png"))); // NOI18N
        btn_First.setToolTipText("");
        btn_First.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_FirstMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_FirstMouseExited(evt);
            }
        });
        pn_infoEmployee.add(btn_First, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 150, 74, 49));

        btn_Prev.setBackground(new java.awt.Color(255, 255, 255));
        btn_Prev.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_Prev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Prev.png"))); // NOI18N
        btn_Prev.setToolTipText("");
        btn_Prev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_PrevMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_PrevMouseExited(evt);
            }
        });
        pn_infoEmployee.add(btn_Prev, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 150, 74, 49));

        btn_Next.setBackground(new java.awt.Color(255, 255, 255));
        btn_Next.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_Next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Next.png"))); // NOI18N
        btn_Next.setToolTipText("");
        btn_Next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_NextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_NextMouseExited(evt);
            }
        });
        pn_infoEmployee.add(btn_Next, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 150, 74, 49));

        cbb_Department1.setBackground(new java.awt.Color(253, 239, 249));
        cbb_Department1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        cbb_Department1.setMaximumRowCount(20);
        cbb_Department1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_Department1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 224, 239)));
        cbb_Department1.setName(""); // NOI18N
        cbb_Department1.setOpaque(false);
        pn_infoEmployee.add(cbb_Department1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 70, 180, 22));

        lbl_BasicSalary1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        lbl_BasicSalary1.setForeground(new java.awt.Color(236, 56, 188));
        lbl_BasicSalary1.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        lbl_BasicSalary1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        lbl_BasicSalary1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_BasicSalary1ActionPerformed(evt);
            }
        });
        pn_infoEmployee.add(lbl_BasicSalary1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 110, 100, 19));

        lbl_VND1.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        lbl_VND1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_VND1.setText("VNĐ");
        lbl_VND1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        pn_infoEmployee.add(lbl_VND1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 110, 78, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel26.setText("Ngày phụ cấp");
        pn_infoEmployee.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 150, -1, -1));

        add(pn_infoEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 1610, 230));

        pn_Contact.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/twitter.png"))); // NOI18N

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/youtube32.png"))); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/facebook.png"))); // NOI18N

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/internet.png"))); // NOI18N

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/instagram.png"))); // NOI18N

        javax.swing.GroupLayout pn_ContactLayout = new javax.swing.GroupLayout(pn_Contact);
        pn_Contact.setLayout(pn_ContactLayout);
        pn_ContactLayout.setHorizontalGroup(
            pn_ContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_ContactLayout.createSequentialGroup()
                .addGap(0, 48, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pn_ContactLayout.setVerticalGroup(
            pn_ContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_ContactLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(pn_ContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        add(pn_Contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(1410, 980, 240, 60));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_NameOfUser.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbl_NameOfUser.setForeground(new java.awt.Color(236, 56, 188));
        lbl_NameOfUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_NameOfUser.setText("Họ tên");
        jPanel3.add(lbl_NameOfUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1262, 49, 139, -1));

        lbl_DutyOfUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_DutyOfUser.setForeground(new java.awt.Color(84, 179, 237));
        lbl_DutyOfUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_DutyOfUser.setText("Chức vụ");
        jPanel3.add(lbl_DutyOfUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(1262, 78, 139, -1));

        lbl_Avatar.setBackground(new java.awt.Color(255, 255, 255));
        lbl_Avatar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Avatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Images/user.png"))); // NOI18N
        lbl_Avatar.setRoundBottomLeft(130);
        lbl_Avatar.setRoundBottomRight(130);
        lbl_Avatar.setRoundTopLeft(130);
        lbl_Avatar.setRoundTopRight(130);
        jPanel3.add(lbl_Avatar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1419, 13, 130, 130));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1256, 49, -1, -1));

        jLabel6.setFont(new java.awt.Font("Source Sans Pro Black", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Phụ Cấp");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, 210, -1));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 1580, 150));
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void lbl_DateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_DateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_DateActionPerformed

    private void btn_DeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DeleteMouseEntered
        btn_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverDelete.png"))); // NOI18N
    }//GEN-LAST:event_btn_DeleteMouseEntered

    private void btn_DeleteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DeleteMouseExited
        btn_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Delete.png"))); // NOI18N
    }//GEN-LAST:event_btn_DeleteMouseExited

    private void btn_AddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_AddMouseEntered
        btn_Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverAdd.png"))); // NOI18N
    }//GEN-LAST:event_btn_AddMouseEntered

    private void btn_AddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_AddMouseExited
        btn_Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Add.png"))); // NOI18N
    }//GEN-LAST:event_btn_AddMouseExited

    private void btn_ClearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ClearMouseEntered
        btn_Clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverClear.png"))); // NOI18N
    }//GEN-LAST:event_btn_ClearMouseEntered

    private void btn_ClearMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ClearMouseExited
        btn_Clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Clear.png"))); // NOI18N
    }//GEN-LAST:event_btn_ClearMouseExited

    private void btn_UpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_UpdateMouseEntered
        btn_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverUpdate.png"))); // NOI18N
    }//GEN-LAST:event_btn_UpdateMouseEntered

    private void btn_UpdateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_UpdateMouseExited
        btn_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Update.png"))); // NOI18N
    }//GEN-LAST:event_btn_UpdateMouseExited

    private void btn_LastMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LastMouseEntered
        btn_Last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverLast.png"))); // NOI18N
    }//GEN-LAST:event_btn_LastMouseEntered

    private void btn_LastMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LastMouseExited
        btn_Last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Last.png"))); // NOI18N
    }//GEN-LAST:event_btn_LastMouseExited

    private void btn_FirstMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_FirstMouseEntered
        btn_First.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverFirst.png"))); // NOI18N
    }//GEN-LAST:event_btn_FirstMouseEntered

    private void btn_FirstMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_FirstMouseExited
        btn_First.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_First.png"))); // NOI18N
    }//GEN-LAST:event_btn_FirstMouseExited

    private void btn_PrevMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_PrevMouseEntered
        btn_Prev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverPrev.png"))); // NOI18N
    }//GEN-LAST:event_btn_PrevMouseEntered

    private void btn_PrevMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_PrevMouseExited
        btn_Prev.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Prev.png"))); // NOI18N
    }//GEN-LAST:event_btn_PrevMouseExited

    private void btn_NextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_NextMouseEntered
        btn_Next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverNext.png"))); // NOI18N
    }//GEN-LAST:event_btn_NextMouseEntered

    private void btn_NextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_NextMouseExited
        btn_Next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Next.png"))); // NOI18N
    }//GEN-LAST:event_btn_NextMouseExited

    private void lbl_BasicSalary1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_BasicSalary1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_BasicSalary1ActionPerformed

    private void lbl_dateFormatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_dateFormatMouseClicked
        dateChooser1.showPopup();
    }//GEN-LAST:event_lbl_dateFormatMouseClicked
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_Add;
    private javax.swing.JLabel btn_Clear;
    private javax.swing.JLabel btn_Delete;
    private javax.swing.JLabel btn_First;
    private javax.swing.JLabel btn_Last;
    private javax.swing.JLabel btn_Next;
    private javax.swing.JLabel btn_Prev;
    private javax.swing.JLabel btn_Update;
    private javax.swing.JComboBox<String> cbb_Department;
    private javax.swing.JComboBox<String> cbb_Department1;
    private com.Nhom5.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private com.Nhom5.Swing.BoderRadius lbl_Avatar;
    private com.Nhom5.Swing.BoderRadius lbl_Avatar1;
    private javax.swing.JTextField lbl_BasicSalary1;
    private javax.swing.JTextField lbl_Date;
    private javax.swing.JLabel lbl_DutyOfUser;
    private javax.swing.JLabel lbl_NameOfUser;
    private javax.swing.JLabel lbl_VND1;
    private javax.swing.JLabel lbl_dateFormat;
    private javax.swing.JPanel pn_Contact;
    private com.Nhom5.Components.pnGradientColor pn_Table;
    private javax.swing.JPanel pn_infoEmployee;
    // End of variables declaration//GEN-END:variables
    
    
  
    public void checkLimitDate(){
        dateChooser1.addEventDateChooser(new EventDateChooser() {
            @Override
            public void dateSelected(SelectedAction action, SelectedDate date) {
                System.out.println(date.getDay() + "-" + date.getMonth() + "-" + date.getYear());
                
                int hour, second, minute;
                int day, month, year;
                String timeStr, yearStr;
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
                
                if (date.getYear() > year ) {
                    JOptionPane.showMessageDialog(Allowance.this, "Không thể xét ngày phụ cấp trong tương lai!","Message",JOptionPane.INFORMATION_MESSAGE);
                    dateChooser1.showPopup();
                    lbl_Date.setText("");
                }else if(date.getYear() == year && date.getMonth() > (month+1) ){
                    JOptionPane.showMessageDialog(Allowance.this, "Không thể xét ngày phụ cấp trong tương lai!","Message",JOptionPane.INFORMATION_MESSAGE);
                    dateChooser1.showPopup();
                    lbl_Date.setText("");
                }else if(date.getYear() == year && date.getMonth() == (month+1) && date.getDay() > day){
                    JOptionPane.showMessageDialog(Allowance.this, "Không thể xét ngày phụ cấp trong tương lai!","Message",JOptionPane.INFORMATION_MESSAGE);
                    dateChooser1.showPopup();
                    lbl_Date.setText("");
                }
            }
        });
    }
}
