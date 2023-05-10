package com.Nhom5.UI2;

import AppPackage.AnimationClass;
import com.Nhom5.Entity.User;
import com.Nhom5.Helper.MsgBox;
import com.Nhom5.Helper.XImage;
import com.sun.source.tree.BreakTree;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author hvn45
 */
public class HomeFinal extends javax.swing.JFrame {

    private MigLayout layout;
    User u;

    public HomeFinal() {
        initComponents();
        init();
        loadLocation(); // load location của các lable để quay về chỗ cũ
//        slide();
//        table();
//        getTime();

    }

    public HomeFinal(User entity) {
        initComponents();
        init();
        loadLocation(); // load location của các lable để quay về chỗ cũ
//        slide();
//        table();
//        getTime();
        this.u = entity;
        setInforUser();
    }

    // Menu drop down
    int w = 240;
    int h = 155;
    boolean checkDrop = false;
    Point locaPB;
    Point locaCV;
    Point locaTD;
    Point locaThongKe;
    Point locaAccount;

    // 
    AnimationClass ac = new AnimationClass();

    // Get time
    int hour, second, minute;
    int day, month, year;
    String timeStr, yearStr;

    // Table
    public void table() {
        setBackground(new Color(0, 0, 0));
        jTable1.getTableHeader().setFont(new Font("Seoge UI", Font.BOLD, 15));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(247, 37, 133));
        jTable1.getTableHeader().setForeground(new Color(0, 0, 128));
        jTable1.setRowHeight(24);
    }

    // Set infor user
    public void setInforUser() {
        lbl_NameOfUser.setText(u.getName());
        lbl_DutyOfUser.setText(u.getDuty());
        lbl_Avatar.setIcon(XImage.read(u.getImg()));
    }

    public void loadLocation() {
        locaPB = lbl_Depratment.getLocation();
        locaCV = lbl_Duty.getLocation();
        locaTD = lbl_Degree.getLocation();
        locaThongKe = lbl_TK.getLocation();
        locaAccount = lbl_Account.getLocation();
    }

    // getTime and show ui
    public void getTime() {

        new Thread(new Runnable() {
            @Override
            public void run() {
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
        }
        ).start();

    }

    public void openDropDown() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                lbl_drop.setIcon(new ImageIcon("src\\com\\Nhom5\\Icons\\upload.png"));
                lbl_Depratment.move(50, 540);
                lbl_Duty.move(50, 600);
                lbl_Degree.move(50, 660);
                lbl_TK.move(50, 720);
                lbl_Account.move(50, 780);
                for (int i = 0; i <= w; i++) {
                    pn_DropDown.setSize(i, h);
                    try {
                        if (i == w) {

                        }
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(HomeFinal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
        ).start();
        checkDrop = true;

    }

    public void closeDropDown(Point locaPB, Point locaCV, Point locaTD, Point locaThongKe, Point locaAccount) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                lbl_drop.setIcon(new ImageIcon("src\\com\\Nhom5\\Icons\\down-arrow.png"));
                for (int i = w; i >= 0; i--) {
                    pn_DropDown.setSize(i, h);
                    try {
                        if (i == 0) {

                        }
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(HomeFinal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                lbl_Depratment.setLocation(locaPB);
                lbl_Account.setLocation(locaAccount);
                lbl_Duty.setLocation(locaCV);
                lbl_Degree.setLocation(locaTD);
                lbl_TK.setLocation(locaThongKe);
            }
        }
        ).start();
        checkDrop = false;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        menu1 = new com.Nhom5.Components.Menu();
        lbl_Staff = new javax.swing.JLabel();
        lbl_Home = new javax.swing.JLabel();
        pn_DropDown = new javax.swing.JPanel();
        lbl_OT = new javax.swing.JLabel();
        lbl_Allowance = new javax.swing.JLabel();
        lbl_SalaryAdvance = new javax.swing.JLabel();
        lbl_TableSalary = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        lbl_Salary = new javax.swing.JLabel();
        lbl_Depratment = new javax.swing.JLabel();
        lbl_Logout = new javax.swing.JLabel();
        lbl_TK = new javax.swing.JLabel();
        lbl_Degree = new javax.swing.JLabel();
        lbl_Duty = new javax.swing.JLabel();
        lbl_Account = new javax.swing.JLabel();
        lbl_drop = new javax.swing.JLabel();
        lbl_Logo = new javax.swing.JLabel();
        pn_Card = new javax.swing.JPanel();
        pn_Main = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbl_NameOfUser = new javax.swing.JLabel();
        lbl_DutyOfUser = new javax.swing.JLabel();
        lbl_Avatar = new com.Nhom5.Swing.BoderRadius();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        pn_Slide = new javax.swing.JPanel();
        lbl_slide0 = new javax.swing.JLabel();
        pn_Message = new com.Nhom5.Components.pnGradientColor();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pn_Contact = new com.Nhom5.Components.pnGradientColor();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        pn_Time = new com.Nhom5.Components.pnGradientColor();
        jLabel7 = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        clock2 = new com.Nhom5.Components.Clock();
        pn_Ranking = new com.Nhom5.Components.pnGradientColor();
        jLabel19 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1920, 1080));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1920, 1080));
        jPanel1.setMinimumSize(new java.awt.Dimension(1920, 1080));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(224, 224, 224));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Images/Minisize.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Images/close.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setText("Five-B HRM");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/app.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1717, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, -1));

        menu1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_Staff.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_Staff.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Staff.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_Staff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/employee (1).png"))); // NOI18N
        lbl_Staff.setText(" Nhân viên");
        lbl_Staff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_StaffMouseClicked(evt);
            }
        });
        menu1.add(lbl_Staff, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 129, 32));

        lbl_Home.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_Home.setForeground(new java.awt.Color(144, 224, 239));
        lbl_Home.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_Home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/home24.png"))); // NOI18N
        lbl_Home.setText(" Trang chủ");
        lbl_Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_HomeMouseClicked(evt);
            }
        });
        menu1.add(lbl_Home, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, 32));

        pn_DropDown.setBackground(new java.awt.Color(255, 255, 255));
        pn_DropDown.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(236, 56, 188)));
        pn_DropDown.setPreferredSize(new java.awt.Dimension(220, 262));
        pn_DropDown.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_OT.setFont(new java.awt.Font("Noto Sans", 2, 16)); // NOI18N
        lbl_OT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_OT.setText("Tăng ca");
        lbl_OT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_OTMouseClicked(evt);
            }
        });
        pn_DropDown.add(lbl_OT, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 13, 92, 31));

        lbl_Allowance.setFont(new java.awt.Font("Noto Sans", 2, 16)); // NOI18N
        lbl_Allowance.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_Allowance.setText("Phụ cấp");
        lbl_Allowance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_AllowanceMouseClicked(evt);
            }
        });
        pn_DropDown.add(lbl_Allowance, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 51, 92, 31));

        lbl_SalaryAdvance.setFont(new java.awt.Font("Noto Sans", 2, 16)); // NOI18N
        lbl_SalaryAdvance.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_SalaryAdvance.setText("Ứng lương");
        lbl_SalaryAdvance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_SalaryAdvanceMouseClicked(evt);
            }
        });
        pn_DropDown.add(lbl_SalaryAdvance, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 89, 92, 31));

        lbl_TableSalary.setFont(new java.awt.Font("Noto Sans", 2, 16)); // NOI18N
        lbl_TableSalary.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_TableSalary.setText("Bảng lương");
        lbl_TableSalary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_TableSalaryMouseClicked(evt);
            }
        });
        pn_DropDown.add(lbl_TableSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 127, 92, 31));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setAlignmentX(1.0F);
        jSeparator1.setAlignmentY(1.0F);
        pn_DropDown.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 68, 20, -1));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setAlignmentX(1.0F);
        jSeparator2.setAlignmentY(1.0F);
        pn_DropDown.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 105, 20, 10));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator3.setAlignmentX(1.0F);
        jSeparator3.setAlignmentY(1.0F);
        pn_DropDown.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 144, 20, 10));

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator4.setAlignmentX(1.0F);
        jSeparator4.setAlignmentY(1.0F);
        pn_DropDown.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 20, 10));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );

        pn_DropDown.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 10, 140));

        menu1.add(pn_DropDown, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 375, 241, 0));

        lbl_Salary.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_Salary.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Salary.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_Salary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/money.png"))); // NOI18N
        lbl_Salary.setText("  Lương");
        lbl_Salary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_SalaryMouseClicked(evt);
            }
        });
        menu1.add(lbl_Salary, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, 106, 32));

        lbl_Depratment.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_Depratment.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Depratment.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_Depratment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/department.png"))); // NOI18N
        lbl_Depratment.setText(" Phòng ban");
        lbl_Depratment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_DepratmentMouseClicked(evt);
            }
        });
        menu1.add(lbl_Depratment, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, -1, 32));

        lbl_Logout.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_Logout.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Logout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/logout.png"))); // NOI18N
        lbl_Logout.setText("Đăng xuất");
        lbl_Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_LogoutMouseClicked(evt);
            }
        });
        menu1.add(lbl_Logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 1000, 140, 31));

        lbl_TK.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_TK.setForeground(new java.awt.Color(255, 255, 255));
        lbl_TK.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_TK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/chart.png"))); // NOI18N
        lbl_TK.setText(" Thống kê");
        lbl_TK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_TKMouseClicked(evt);
            }
        });
        menu1.add(lbl_TK, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 580, 125, 32));

        lbl_Degree.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_Degree.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Degree.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_Degree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/graduation-cap.png"))); // NOI18N
        lbl_Degree.setText(" Trình độ");
        lbl_Degree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_DegreeMouseClicked(evt);
            }
        });
        menu1.add(lbl_Degree, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, -1, 32));

        lbl_Duty.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_Duty.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Duty.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_Duty.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/position.png"))); // NOI18N
        lbl_Duty.setText(" Chức vụ");
        lbl_Duty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_DutyMouseClicked(evt);
            }
        });
        menu1.add(lbl_Duty, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, 104, 32));

        lbl_Account.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbl_Account.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Account.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_Account.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/accout.png"))); // NOI18N
        lbl_Account.setText(" Tài khoản");
        menu1.add(lbl_Account, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 640, 125, 32));

        lbl_drop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_drop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/down-arrow.png"))); // NOI18N
        lbl_drop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_dropMouseClicked(evt);
            }
        });
        menu1.add(lbl_drop, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, -1, -1));

        lbl_Logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Images/LogoHome-removebg-preview.png"))); // NOI18N
        menu1.add(lbl_Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jPanel1.add(menu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 28, -1, 1100));

        pn_Card.setBackground(new java.awt.Color(255, 255, 255));
        pn_Card.setLayout(new java.awt.CardLayout());

        pn_Main.setBackground(new java.awt.Color(255, 255, 255));
        pn_Main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Source Sans Pro Black", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("vơi ứng dụng của chúng tôi");

        jLabel6.setFont(new java.awt.Font("Source Sans Pro Black", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Chuyên nghiệp hoá quản lý thông tin nhân sự");

        lbl_NameOfUser.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbl_NameOfUser.setForeground(new java.awt.Color(236, 56, 188));
        lbl_NameOfUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_NameOfUser.setText("Họ tên");

        lbl_DutyOfUser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_DutyOfUser.setForeground(new java.awt.Color(84, 179, 237));
        lbl_DutyOfUser.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_DutyOfUser.setText("Chức vụ");

        lbl_Avatar.setBackground(new java.awt.Color(255, 255, 255));
        lbl_Avatar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Avatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Images/user.png"))); // NOI18N
        lbl_Avatar.setRoundBottomLeft(130);
        lbl_Avatar.setRoundBottomRight(130);
        lbl_Avatar.setRoundTopLeft(130);
        lbl_Avatar.setRoundTopRight(130);

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(460, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(259, 259, 259)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_NameOfUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_DutyOfUser, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbl_Avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbl_Avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lbl_NameOfUser)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_DutyOfUser)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pn_Main.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 1580, 150));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/twitter.png"))); // NOI18N

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/youtube32.png"))); // NOI18N

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/facebook.png"))); // NOI18N

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/internet.png"))); // NOI18N

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/instagram.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
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
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pn_Main.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1400, 980, -1, -1));

        pn_Slide.setBackground(new java.awt.Color(51, 51, 51));
        pn_Slide.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_slide0.setBackground(new java.awt.Color(204, 204, 204));
        lbl_slide0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_slide0.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Images/opacity3.png"))); // NOI18N
        lbl_slide0.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lbl_slide0.setMaximumSize(new java.awt.Dimension(800, 800));
        lbl_slide0.setMinimumSize(new java.awt.Dimension(800, 800));
        lbl_slide0.setPreferredSize(new java.awt.Dimension(170, 170));
        pn_Slide.add(lbl_slide0, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1475, 830));

        pn_Message.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        jLabel9.setText("- Nộp bổ sung bằng cử nhân tại phòng nhân sự đến hết ngày 28-04");
        pn_Message.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        jLabel10.setText("- Tất cả các nhân viên được nghĩ phép từ 30-04 đến hết ngày 01-05");
        pn_Message.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/message.png"))); // NOI18N
        jLabel4.setText(" Thông báo");
        pn_Message.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, -1));

        pn_Slide.add(pn_Message, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 650, 350));

        pn_Contact.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(236, 56, 203));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/phone-call.png"))); // NOI18N
        jLabel8.setText(" Liên hệ với các admin sau nếu có vấn đề về ứng dụng");
        pn_Contact.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 5, 590, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

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
        jTable1.setSelectionBackground(new java.awt.Color(84, 179, 237));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        pn_Contact.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 650, 150));

        pn_Slide.add(pn_Contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 30, 650, 350));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/clock.png"))); // NOI18N
        jLabel7.setText(" Thời gian");

        time.setBackground(new java.awt.Color(255, 255, 255));
        time.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        time.setForeground(new java.awt.Color(236, 56, 188));
        time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        time.setText("Time");

        javax.swing.GroupLayout pn_TimeLayout = new javax.swing.GroupLayout(pn_Time);
        pn_Time.setLayout(pn_TimeLayout);
        pn_TimeLayout.setHorizontalGroup(
            pn_TimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
            .addGroup(pn_TimeLayout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_TimeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clock2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(198, 198, 198))
        );
        pn_TimeLayout.setVerticalGroup(
            pn_TimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_TimeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clock2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pn_Slide.add(pn_Time, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 650, 350));

        pn_Ranking.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setBackground(new java.awt.Color(236, 56, 203));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/ranking.png"))); // NOI18N
        jLabel19.setText(" Nhân viên tiêu biểu của tháng");
        pn_Ranking.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 610, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        jLabel17.setText("- Bùi Thị Cẩm Lệ  | Trưởng phòng Kế toán");
        pn_Ranking.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        jLabel18.setText("- Võ Quốc Tuấn   | Trưởng phòng Nhân sự");
        pn_Ranking.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 2, 15)); // NOI18N
        jLabel20.setText("- Lê Thanh Quí     | Trưởng phòng Marketing");
        pn_Ranking.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        pn_Slide.add(pn_Ranking, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 440, 650, 350));

        pn_Main.add(pn_Slide, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 1475, 830));

        pn_Card.add(pn_Main, "card2");

        jPanel1.add(pn_Card, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 33, 1680, 1050));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        //        int kq = JOptionPane.showConfirmDialog(this, "Có chắc chắn thoát không!","Thoát",JOptionPane.YES_NO_OPTION);
        //        if (kq == 0) {
        System.exit(0);

    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.setExtendedState(new JFrame().ICONIFIED);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void lbl_dropMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_dropMouseClicked

        if (checkDrop == false) {
            openDropDown();
            return;
        } else {
            closeDropDown(locaPB, locaCV, locaTD, locaThongKe, locaAccount);
        }
    }//GEN-LAST:event_lbl_dropMouseClicked

    private void lbl_SalaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_SalaryMouseClicked
        if (checkDrop == false) {
            openDropDown();
            return;
        } else {
            closeDropDown(locaPB, locaCV, locaTD, locaThongKe, locaAccount);
        }
    }//GEN-LAST:event_lbl_SalaryMouseClicked

    private void lbl_StaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_StaffMouseClicked
       
            changePanel(new PnEmployee(this.u));
            setBackgroudColorMenu(lbl_Staff);
            if (checkDrop == true) {
                checkDrop = false;
                lbl_drop.setIcon(new ImageIcon("src\\com\\Nhom5\\Icons\\down-arrow.png"));
            }
    }//GEN-LAST:event_lbl_StaffMouseClicked

    private void lbl_HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_HomeMouseClicked
        changePanel(new pnHome(this.u));
        setBackgroudColorMenu(lbl_Home);
        if (checkDrop == true) {
            checkDrop = false;
            lbl_drop.setIcon(new ImageIcon("src\\com\\Nhom5\\Icons\\down-arrow.png"));
        }
    }//GEN-LAST:event_lbl_HomeMouseClicked

    private void lbl_OTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_OTMouseClicked
        if (this.u.getRole() == 1) {
            MsgBox.alert(this, "Rất tiếc! Quyền của bạn bị hạn chế chức năng này");
            return;
        } 
        changePanel(new OT_TS(this.u));
        setBackgroudColorMenu(lbl_OT);
        if (checkDrop == true) {
            checkDrop = false;
            lbl_drop.setIcon(new ImageIcon("src\\com\\Nhom5\\Icons\\down-arrow.png"));
        }
//        if (checkDrop == false) {
//            openDropDown();
//            return;
//        } else {
//            closeDropDown(locaPB, locaCV, locaTD, locaThongKe, locaAccount);
//        }

    }//GEN-LAST:event_lbl_OTMouseClicked

    private void lbl_DepratmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_DepratmentMouseClicked
        if (this.u.getRole() == 1) {
            MsgBox.alert(this, "Rất tiếc! Quyền của bạn bị hạn chế chức năng này");
            return;
        } 
        changePanel(new PnDepartment(this.u));
        setBackgroudColorMenu(lbl_Depratment);
        if (checkDrop == true) {
            checkDrop = false;
            lbl_drop.setIcon(new ImageIcon("src\\com\\Nhom5\\Icons\\down-arrow.png"));
        }
    }//GEN-LAST:event_lbl_DepratmentMouseClicked

    private void lbl_AllowanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_AllowanceMouseClicked
        if (this.u.getRole() == 1) {
            MsgBox.alert(this, "Rất tiếc! Quyền của bạn bị hạn chế chức năng này");
            return;
        } 
        changePanel(new Allowance_TA(this.u));
        setBackgroudColorMenu(lbl_Allowance);
        if (checkDrop == true) {
            checkDrop = false;
            lbl_drop.setIcon(new ImageIcon("src\\com\\Nhom5\\Icons\\down-arrow.png"));
        }
    }//GEN-LAST:event_lbl_AllowanceMouseClicked

    private void lbl_SalaryAdvanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_SalaryAdvanceMouseClicked
        if (this.u.getRole() == 1) {
            MsgBox.alert(this, "Rất tiếc! Quyền của bạn bị hạn chế chức năng này");
            return;
        } 
        changePanel(new Advance(this.u));
        setBackgroudColorMenu(lbl_SalaryAdvance);
        if (checkDrop == true) {
            checkDrop = false;
            lbl_drop.setIcon(new ImageIcon("src\\com\\Nhom5\\Icons\\down-arrow.png"));
        }
    }//GEN-LAST:event_lbl_SalaryAdvanceMouseClicked

    private void lbl_LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_LogoutMouseClicked
        new Login().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lbl_LogoutMouseClicked

    private void lbl_TableSalaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_TableSalaryMouseClicked
        changePanel(new TableSalary(this.u));
        setBackgroudColorMenu(lbl_TableSalary);
        if (checkDrop == true) {
            checkDrop = false;
            lbl_drop.setIcon(new ImageIcon("src\\com\\Nhom5\\Icons\\down-arrow.png"));
        }
    }//GEN-LAST:event_lbl_TableSalaryMouseClicked

    private void lbl_DutyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_DutyMouseClicked
       if (this.u.getRole() == 1) {
            MsgBox.alert(this, "Rất tiếc! Quyền của bạn bị hạn chế chức năng này");
            return;
        } 
        changePanel(new PnDuty(this.u));
        setBackgroudColorMenu(lbl_Duty);
        if (checkDrop == true) {
            checkDrop = false;
            lbl_drop.setIcon(new ImageIcon("src\\com\\Nhom5\\Icons\\down-arrow.png"));
        }
    }//GEN-LAST:event_lbl_DutyMouseClicked

    private void lbl_DegreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_DegreeMouseClicked
        if (this.u.getRole() == 1) {
            MsgBox.alert(this, "Rất tiếc! Quyền của bạn bị hạn chế chức năng này");
            return;
        } 
        changePanel(new PnDegree(this.u));
        setBackgroudColorMenu(lbl_Degree);
        if (checkDrop == true) {
            checkDrop = false;
            lbl_drop.setIcon(new ImageIcon("src\\com\\Nhom5\\Icons\\down-arrow.png"));
        }
    }//GEN-LAST:event_lbl_DegreeMouseClicked

    private void lbl_TKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_TKMouseClicked
        if (this.u.getRole() == 1) {
            MsgBox.alert(this, "Rất tiếc! Quyền của bạn bị hạn chế chức năng này");
            return;
        } 
        changePanel(new PnStatistical(this.u));
        setBackgroudColorMenu(lbl_TK);
        if (checkDrop == true) {
            checkDrop = false;
            lbl_drop.setIcon(new ImageIcon("src\\com\\Nhom5\\Icons\\down-arrow.png"));
        }
    }//GEN-LAST:event_lbl_TKMouseClicked

    public void setBackgroudColorMenu(JLabel lbl) {
        lbl_Account.setForeground(new Color(255, 255, 255));
        lbl_Home.setForeground(new Color(255, 255, 255));
        lbl_Degree.setForeground(new Color(255, 255, 255));
        lbl_Depratment.setForeground(new Color(255, 255, 255));
        lbl_Duty.setForeground(new Color(255, 255, 255));
        lbl_TK.setForeground(new Color(255, 255, 255));
        lbl_Staff.setForeground(new Color(255, 255, 255));
        lbl_Logout.setForeground(new Color(255, 255, 255));
        lbl_Salary.setForeground(new Color(255, 255, 255));
        lbl_OT.setForeground(new Color(0, 0, 0));
        lbl_Allowance.setForeground(new Color(0, 0, 0));
        lbl_SalaryAdvance.setForeground(new Color(0, 0, 0));
        lbl_TableSalary.setForeground(new Color(0, 0, 0));
        lbl.setForeground(new Color(84, 179, 237));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(HomeFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeFinal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeFinal().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.Nhom5.Components.Clock clock2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_Account;
    private javax.swing.JLabel lbl_Allowance;
    private com.Nhom5.Swing.BoderRadius lbl_Avatar;
    private javax.swing.JLabel lbl_Degree;
    private javax.swing.JLabel lbl_Depratment;
    private javax.swing.JLabel lbl_Duty;
    private javax.swing.JLabel lbl_DutyOfUser;
    private javax.swing.JLabel lbl_Home;
    private javax.swing.JLabel lbl_Logo;
    private javax.swing.JLabel lbl_Logout;
    private javax.swing.JLabel lbl_NameOfUser;
    private javax.swing.JLabel lbl_OT;
    private javax.swing.JLabel lbl_Salary;
    private javax.swing.JLabel lbl_SalaryAdvance;
    private javax.swing.JLabel lbl_Staff;
    private javax.swing.JLabel lbl_TK;
    private javax.swing.JLabel lbl_TableSalary;
    private javax.swing.JLabel lbl_drop;
    private javax.swing.JLabel lbl_slide0;
    private com.Nhom5.Components.Menu menu1;
    private javax.swing.JPanel pn_Card;
    private com.Nhom5.Components.pnGradientColor pn_Contact;
    private javax.swing.JPanel pn_DropDown;
    private javax.swing.JPanel pn_Main;
    private com.Nhom5.Components.pnGradientColor pn_Message;
    private com.Nhom5.Components.pnGradientColor pn_Ranking;
    private javax.swing.JPanel pn_Slide;
    private com.Nhom5.Components.pnGradientColor pn_Time;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables

    public void changePanel(JPanel pn) {
        pn_Card.removeAll();
        pn_Card.add(pn);
        pn_Card.repaint();
        pn_Card.revalidate();
    }

    private void init() {
        this.setLocationRelativeTo(null);
        this.setIconImage(XImage.getAppIcon());
        //lblUserName.setVisible(false);
        //lblLogin.setIcon(new ImageIcon("src\\com\\nhom4\\icon\\btnLog1.png"));

    }

    public ImageIcon ResizeImage(String ImagePath) {
        ImageIcon myImage = new ImageIcon(ImagePath);
        Image img = myImage.getImage();
        Image newImg = img.getScaledInstance(lbl_Avatar.getWidth(), lbl_Avatar.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }

    //    public void slide(){
//        
//        new Thread(new Runnable()
//        {
//            @Override
//            public void run(){
//                
//                int nb = 0;
//                try {
//                    while (true) {                        
//                        switch(nb){
//
//                                
//                            case 0:
//                                Thread.sleep(1000);
//                                ac.jLabelXLeft(0, -800,12, 10, lbl_slide0);
//                                ac.jLabelXLeft(800, 0,12, 10, lbl_slide1);
//                                ac.jLabelXLeft(16000, 800,12, 10, lbl_slide2);
//                                nb++;
//                            case 1:
//                                Thread.sleep(1000);
//                                ac.jLabelXLeft(-800, -16000,12, 10, lbl_slide0);
//                                ac.jLabelXLeft(0, -800,12, 10, lbl_slide1);
//                                ac.jLabelXLeft(800, 0,12, 10, lbl_slide2);
//                                nb++;
//                            case 2:
//                                Thread.sleep(1000);
//                                ac.jLabelXRight(-16000, -800,12, 10, lbl_slide0);
//                                ac.jLabelXRight(-800, 0,12, 10, lbl_slide1);
//                                ac.jLabelXRight(0, 800,12, 10, lbl_slide2);
//                                nb++;
//                            case 3:
//                                Thread.sleep(1000);
//                                ac.jLabelXRight(-800, 0,12, 10, lbl_slide0);
//                                ac.jLabelXRight(0, 800,12, 10, lbl_slide1);
//                                ac.jLabelXRight(800, 16000,12, 10, lbl_slide2);
//                                nb=0;
//                            
//                               
//                        }
//                    }
//                } catch (Exception e) {
//                }
//                
//            }
//        }
//        ).start();
//    }
}
