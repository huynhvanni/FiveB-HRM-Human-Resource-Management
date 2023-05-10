/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.Nhom5.UI2;

import com.Nhom5.DAO.EmloyeeDAO;
import com.Nhom5.DAO.OverTimeDAO;
import com.Nhom5.DAO.TypeShiftDAO;
import com.Nhom5.Entity.Employee;
import com.Nhom5.Entity.TypeShift;
import com.Nhom5.Entity.User;
import com.Nhom5.Helper.MsgBox;
import com.Nhom5.Helper.XImage;
import com.Nhom5.datechooser.EventDateChooser;
import com.Nhom5.datechooser.SelectedAction;
import com.Nhom5.datechooser.SelectedDate;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hvn45
 */
public class OT_TS extends javax.swing.JPanel {

    User u;
    OverTimeDAO otDAO = new OverTimeDAO();
    TypeShiftDAO tsDAO = new TypeShiftDAO();
    ArrayList<com.Nhom5.Entity.OverTime> listOT = new ArrayList<com.Nhom5.Entity.OverTime>();
    ArrayList<TypeShift> listTS = new ArrayList<TypeShift>();
    final DefaultComboBoxModel ModelTS = new DefaultComboBoxModel();
    final DefaultComboBoxModel ModelEmployee = new DefaultComboBoxModel();
    int rowTS = -1;
    int rowOT = -1;

    public OT_TS() {
        initComponents();
        table();
        checkLimitDate();
        fillTableOT();
        fillTableTS();
    }

    public OT_TS(User entity) {
        initComponents();
        table();
        checkLimitDate();
        this.u = entity;
        setInforUser();
        fillTableOT();
        fillTableTS();
        fillCbbEmp();
        fillCbbTS();
    }

    public void table() {
        setBackground(new Color(255, 255, 255));
        tbl_TS.getTableHeader().setFont(new Font("Seoge UI", Font.BOLD, 16));
        tbl_TS.getTableHeader().setOpaque(false);
        tbl_TS.getTableHeader().setBackground(new Color(236, 56, 188));
        tbl_TS.getTableHeader().setForeground(new Color(255, 255, 255));
        tbl_TS.setRowHeight(24);
        tbl_TS.setSelectionBackground(new Color(84, 179, 237));
        tbl_TS.setSelectionForeground(new Color(255, 255, 255));

        setBackground(new Color(255, 255, 255));
        tbl_OT.getTableHeader().setFont(new Font("Seoge UI", Font.BOLD, 16));
        tbl_OT.getTableHeader().setOpaque(false);
        tbl_OT.getTableHeader().setBackground(new Color(236, 56, 188));
        tbl_OT.getTableHeader().setForeground(new Color(255, 255, 255));
        tbl_OT.setRowHeight(24);
        tbl_OT.setSelectionBackground(new Color(84, 179, 237));
        tbl_OT.setSelectionForeground(new Color(255, 255, 255));
    }

    // Set infor user
    public void setInforUser() {
        lbl_NameOfUser.setText(u.getName());
        lbl_DutyOfUser.setText(u.getDuty());
        lbl_Avatar.setIcon(XImage.read(u.getImg()));

        lbl_NameOfUser1.setText(u.getName());
        lbl_DutyOfUser1.setText(u.getDuty());
        lbl_Avatar1.setIcon(XImage.read(u.getImg()));

    }

    public void checkLimitDate() {
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

                if (date.getYear() > year) {
                    JOptionPane.showMessageDialog(OT_TS.this, "Không thể xét ngày tăng ca trong tương lai!", "Message", JOptionPane.INFORMATION_MESSAGE);
                    dateChooser1.showPopup();
                    txt_DateOT.setText("");
                } else if (date.getYear() == year && date.getMonth() > (month + 1)) {
                    JOptionPane.showMessageDialog(OT_TS.this, "Không thể xét ngày tăng ca trong tương lai!", "Message", JOptionPane.INFORMATION_MESSAGE);
                    dateChooser1.showPopup();
                    txt_DateOT.setText("");
                } else if (date.getYear() == year && date.getMonth() == (month + 1) && date.getDay() > day) {
                    JOptionPane.showMessageDialog(OT_TS.this, "Không thể xét ngày tăng ca trong tương lai!", "Message", JOptionPane.INFORMATION_MESSAGE);
                    dateChooser1.showPopup();
                    txt_DateOT.setText("");
                }
            }
        });

    }

    public void fillTableTS() {
        DefaultTableModel model = (DefaultTableModel) tbl_TS.getModel();
        model.setRowCount(0);
        listTS.clear();
        try {
            List<TypeShift> list = tsDAO.selectAll(); // Đọc dữ liệu từ CSDL
            for (TypeShift e : list) {
                Object[] data = {
                    e.getId_TypeShift(),
                    e.getName_TypeShift(),
                    e.getMonney(),};
                listTS.add(e);
                model.addRow(data);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public void fillTableOT() {
        DefaultTableModel model = (DefaultTableModel) tbl_OT.getModel();
        model.setRowCount(0);
        listOT.clear();
        try {
            List<com.Nhom5.Entity.OverTime> list = otDAO.selectAll(); // Đọc dữ liệu từ CSDL
            for (com.Nhom5.Entity.OverTime e : list) {
                Object[] data = {
                    e.getIdOverTime(),
                    idToName(1, e.getIdTypeShift()),
                    e.getIdEmployee(),
                    e.getNameEmployee(),
                    e.getHoursNumber(),
                    dateFormat(e.getDateOverTime()),};
                listOT.add(e);
                model.addRow(data);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public void setFormTS(TypeShift entity) {
        txt_IdTS.setText(entity.getId_TypeShift());
        txt_NameTS.setText(entity.getName_TypeShift());
        txt_Money.setText(String.valueOf(entity.getMonney()));
    }

    public void setFormOT(com.Nhom5.Entity.OverTime entity) {
        lbl_Img.setToolTipText(entity.getImgEmployee());
        if (entity.getImgEmployee() != null) {
            lbl_Img.setIcon(XImage.read(entity.getImgEmployee()));
        }
        cbb_idEmployee.setSelectedItem(entity.getIdEmployee());
        txt_NameEmp.setText(entity.getNameEmployee());
        txt_IdOT.setText(entity.getIdOverTime());
        cbb_TS.setSelectedItem(idToName(1, entity.getIdTypeShift()));
        txt_MoneyOT.setText(String.valueOf(entity.getMoney()));
        txt_HN.setText(String.valueOf(entity.getHoursNumber()));
        txt_DateOT.setText(dateFormat(entity.getDateOverTime()));
    }

    public void fillCbbTS() {
        ModelTS.removeAllElements();
        TypeShiftDAO tsDAO = new TypeShiftDAO();
        List<TypeShift> list = new ArrayList<TypeShift>();
        list = tsDAO.selectAll();
        for (TypeShift x : list) {
            this.ModelTS.addElement(x.getName_TypeShift());
        }
        cbb_TS.setModel(this.ModelTS);
    }

    public void fillCbbEmp() {
        ModelEmployee.removeAllElements();
        EmloyeeDAO EmpDAO = new EmloyeeDAO();
        List<Employee> list = new ArrayList<Employee>();
        list = EmpDAO.selectAll();
        for (Employee x : list) {
            this.ModelEmployee.addElement(x.getIdEmployee());
        }
        cbb_idEmployee.setModel(this.ModelEmployee);
    }

    public String idToName(int checkCbb, String id) {
        if (checkCbb == 1) {// TS
            TypeShiftDAO tsDAO = new TypeShiftDAO();
            TypeShift ts = new TypeShift();
            ts = tsDAO.selectById(id);
            return ts.getName_TypeShift();
        } else if (checkCbb == 0) { // Emp
            EmloyeeDAO empDAO = new EmloyeeDAO();
            Employee emp = new Employee();
            emp = empDAO.selectById(id);
            return emp.getNameEmployee();
        }
        return "";
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        pn_Table1 = new com.Nhom5.Components.pnGradientColor();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_OT = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        lbl_NameOfUser1 = new javax.swing.JLabel();
        lbl_DutyOfUser1 = new javax.swing.JLabel();
        lbl_Avatar1 = new com.Nhom5.Swing.BoderRadius();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        pn_infoEmployee1 = new javax.swing.JPanel();
        lbl_Img = new com.Nhom5.Swing.BoderRadius();
        lbl_dateFormat = new javax.swing.JLabel();
        txt_DateOT = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        cbb_idEmployee = new javax.swing.JComboBox<>();
        btn_Delete1 = new javax.swing.JLabel();
        btn_Add1 = new javax.swing.JLabel();
        btn_Clear1 = new javax.swing.JLabel();
        btn_Update1 = new javax.swing.JLabel();
        btn_Last1 = new javax.swing.JLabel();
        btn_First1 = new javax.swing.JLabel();
        btn_Prev1 = new javax.swing.JLabel();
        btn_Next1 = new javax.swing.JLabel();
        txt_HN = new javax.swing.JTextField();
        cbb_TS = new javax.swing.JComboBox<>();
        txt_MoneyOT = new javax.swing.JTextField();
        lbl_VND1 = new javax.swing.JLabel();
        txt_NameEmp = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txt_IdOT = new javax.swing.JTextField();
        pn_Contact1 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        lbl_NameOfUser = new javax.swing.JLabel();
        lbl_DutyOfUser = new javax.swing.JLabel();
        lbl_Avatar = new com.Nhom5.Swing.BoderRadius();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        pn_Contact = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        pn_infoEmployee = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        btn_Delete = new javax.swing.JLabel();
        btn_Add = new javax.swing.JLabel();
        btn_Clear = new javax.swing.JLabel();
        btn_Update = new javax.swing.JLabel();
        btn_Last = new javax.swing.JLabel();
        btn_First = new javax.swing.JLabel();
        btn_Prev = new javax.swing.JLabel();
        btn_Next = new javax.swing.JLabel();
        txt_IdTS = new javax.swing.JTextField();
        txt_NameTS = new javax.swing.JTextField();
        txt_Money = new javax.swing.JTextField();
        lbl_VND2 = new javax.swing.JLabel();
        pn_Table = new com.Nhom5.Components.pnGradientColor();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_TS = new javax.swing.JTable();

        dateChooser1.setForeground(new java.awt.Color(236, 56, 188));
        dateChooser1.setTextRefernce(txt_DateOT);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        tbl_OT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tbl_OT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Huỳnh Văn Ni", "0123123123", "nihvpc04493@fpt.edu.vn", null, null},
                {"2", "Võ Quốc Tuấn", "0123456789", "tuanvq09999@fpt.edu.vn", null, null},
                {"3", "Bùi Thanh Bùi", "01648163518", "BTB090@gmail.com", null, null},
                {"4", "Philip Lee", "0987654321", "LeDuy@gmail.com", null, null}
            },
            new String [] {
                "Mã tăng ca", "Loại ca", "Mã nhân viên", "Tên nhân viên", "Số giờ", "Ngày tăng ca"
            }
        ));
        tbl_OT.setFocusable(false);
        tbl_OT.setGridColor(new java.awt.Color(236, 56, 188));
        tbl_OT.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tbl_OT.setRowHeight(25);
        tbl_OT.setSelectionBackground(new java.awt.Color(84, 179, 237));
        tbl_OT.getTableHeader().setReorderingAllowed(false);
        tbl_OT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_OTMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_OT);

        javax.swing.GroupLayout pn_Table1Layout = new javax.swing.GroupLayout(pn_Table1);
        pn_Table1.setLayout(pn_Table1Layout);
        pn_Table1Layout.setHorizontalGroup(
            pn_Table1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Table1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pn_Table1Layout.setVerticalGroup(
            pn_Table1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_Table1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_NameOfUser1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lbl_NameOfUser1.setForeground(new java.awt.Color(236, 56, 188));
        lbl_NameOfUser1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_NameOfUser1.setText("Họ tên");
        jPanel4.add(lbl_NameOfUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1262, 49, 139, -1));

        lbl_DutyOfUser1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_DutyOfUser1.setForeground(new java.awt.Color(84, 179, 237));
        lbl_DutyOfUser1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_DutyOfUser1.setText("Chức vụ");
        jPanel4.add(lbl_DutyOfUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1262, 78, 139, -1));

        lbl_Avatar1.setBackground(new java.awt.Color(255, 255, 255));
        lbl_Avatar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Avatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Images/user.png"))); // NOI18N
        lbl_Avatar1.setRoundBottomLeft(130);
        lbl_Avatar1.setRoundBottomRight(130);
        lbl_Avatar1.setRoundTopLeft(130);
        lbl_Avatar1.setRoundTopRight(130);
        jPanel4.add(lbl_Avatar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1419, 13, 130, 130));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1256, 49, -1, -1));

        jLabel7.setFont(new java.awt.Font("Source Sans Pro Black", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Tăng Ca");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, 210, -1));

        pn_infoEmployee1.setBackground(new java.awt.Color(255, 255, 255));
        pn_infoEmployee1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pn_infoEmployee1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_Img.setBackground(new java.awt.Color(255, 255, 255));
        lbl_Img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Images/user.png"))); // NOI18N
        lbl_Img.setRoundBottomLeft(130);
        lbl_Img.setRoundBottomRight(130);
        lbl_Img.setRoundTopLeft(130);
        lbl_Img.setRoundTopRight(130);
        pn_infoEmployee1.add(lbl_Img, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 130, 130));

        lbl_dateFormat.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        lbl_dateFormat.setText("(dd-MM-yyyy)");
        lbl_dateFormat.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        lbl_dateFormat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_dateFormatMouseClicked(evt);
            }
        });
        pn_infoEmployee1.add(lbl_dateFormat, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 190, -1, -1));

        txt_DateOT.setBackground(new java.awt.Color(255, 255, 255));
        txt_DateOT.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        txt_DateOT.setForeground(new java.awt.Color(236, 56, 188));
        txt_DateOT.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_DateOT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        txt_DateOT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_DateOTActionPerformed(evt);
            }
        });
        pn_infoEmployee1.add(txt_DateOT, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 190, 100, 19));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel25.setText("Loại ca");
        pn_infoEmployee1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, -1, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel26.setText("Số tiền/giờ");
        pn_infoEmployee1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 110, -1, -1));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel27.setText("Số giờ");
        pn_infoEmployee1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 150, -1, -1));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel28.setText("Ngày tăng ca");
        pn_infoEmployee1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 190, -1, -1));

        cbb_idEmployee.setBackground(new java.awt.Color(253, 239, 249));
        cbb_idEmployee.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        cbb_idEmployee.setMaximumRowCount(20);
        cbb_idEmployee.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_idEmployee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 224, 239)));
        cbb_idEmployee.setName(""); // NOI18N
        cbb_idEmployee.setOpaque(false);
        cbb_idEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_idEmployeeActionPerformed(evt);
            }
        });
        pn_infoEmployee1.add(cbb_idEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, 130, 22));

        btn_Delete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Delete.png"))); // NOI18N
        btn_Delete1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_Delete1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_Delete1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_Delete1MouseExited(evt);
            }
        });
        pn_infoEmployee1.add(btn_Delete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 20, 169, 44));

        btn_Add1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_SetOverTime.png"))); // NOI18N
        btn_Add1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_Add1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_Add1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_Add1MouseExited(evt);
            }
        });
        pn_infoEmployee1.add(btn_Add1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 20, 169, 44));

        btn_Clear1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Clear.png"))); // NOI18N
        btn_Clear1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_Clear1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_Clear1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_Clear1MouseExited(evt);
            }
        });
        pn_infoEmployee1.add(btn_Clear1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 80, 169, 44));

        btn_Update1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Update.png"))); // NOI18N
        btn_Update1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_Update1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_Update1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_Update1MouseExited(evt);
            }
        });
        pn_infoEmployee1.add(btn_Update1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 80, 169, 44));

        btn_Last1.setBackground(new java.awt.Color(255, 255, 255));
        btn_Last1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_Last1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Last.png"))); // NOI18N
        btn_Last1.setToolTipText("");
        btn_Last1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_Last1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_Last1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_Last1MouseExited(evt);
            }
        });
        pn_infoEmployee1.add(btn_Last1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 150, 74, 49));

        btn_First1.setBackground(new java.awt.Color(255, 255, 255));
        btn_First1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_First1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_First.png"))); // NOI18N
        btn_First1.setToolTipText("");
        btn_First1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_First1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_First1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_First1MouseExited(evt);
            }
        });
        pn_infoEmployee1.add(btn_First1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 150, 74, 49));

        btn_Prev1.setBackground(new java.awt.Color(255, 255, 255));
        btn_Prev1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_Prev1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Prev.png"))); // NOI18N
        btn_Prev1.setToolTipText("");
        btn_Prev1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_Prev1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_Prev1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_Prev1MouseExited(evt);
            }
        });
        pn_infoEmployee1.add(btn_Prev1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 150, 74, 49));

        btn_Next1.setBackground(new java.awt.Color(255, 255, 255));
        btn_Next1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_Next1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Next.png"))); // NOI18N
        btn_Next1.setToolTipText("");
        btn_Next1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_Next1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_Next1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_Next1MouseExited(evt);
            }
        });
        pn_infoEmployee1.add(btn_Next1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 150, 74, 49));

        txt_HN.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        txt_HN.setForeground(new java.awt.Color(236, 56, 188));
        txt_HN.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_HN.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        txt_HN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_HNActionPerformed(evt);
            }
        });
        pn_infoEmployee1.add(txt_HN, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 150, 180, 19));

        cbb_TS.setBackground(new java.awt.Color(253, 239, 249));
        cbb_TS.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        cbb_TS.setMaximumRowCount(20);
        cbb_TS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbb_TS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 224, 239)));
        cbb_TS.setName(""); // NOI18N
        cbb_TS.setOpaque(false);
        cbb_TS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_TSActionPerformed(evt);
            }
        });
        pn_infoEmployee1.add(cbb_TS, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 70, 180, 22));

        txt_MoneyOT.setEditable(false);
        txt_MoneyOT.setBackground(new java.awt.Color(255, 255, 255));
        txt_MoneyOT.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        txt_MoneyOT.setForeground(new java.awt.Color(236, 56, 188));
        txt_MoneyOT.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_MoneyOT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        txt_MoneyOT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MoneyOTActionPerformed(evt);
            }
        });
        pn_infoEmployee1.add(txt_MoneyOT, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 110, 100, 19));

        lbl_VND1.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        lbl_VND1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_VND1.setText("VNĐ");
        lbl_VND1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        pn_infoEmployee1.add(lbl_VND1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 110, 78, -1));

        txt_NameEmp.setEditable(false);
        txt_NameEmp.setBackground(new java.awt.Color(255, 255, 255));
        txt_NameEmp.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        txt_NameEmp.setForeground(new java.awt.Color(236, 56, 203));
        txt_NameEmp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_NameEmp.setText("Họ và tên");
        txt_NameEmp.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        txt_NameEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NameEmpActionPerformed(evt);
            }
        });
        pn_infoEmployee1.add(txt_NameEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 100, 19));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel29.setText("Mã tăng ca");
        pn_infoEmployee1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, -1, -1));

        txt_IdOT.setEditable(false);
        txt_IdOT.setBackground(new java.awt.Color(255, 255, 255));
        txt_IdOT.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        txt_IdOT.setForeground(new java.awt.Color(236, 56, 188));
        txt_IdOT.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_IdOT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        txt_IdOT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_IdOTActionPerformed(evt);
            }
        });
        pn_infoEmployee1.add(txt_IdOT, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 30, 180, 19));

        pn_Contact1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/twitter.png"))); // NOI18N

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/youtube32.png"))); // NOI18N

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/facebook.png"))); // NOI18N

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/internet.png"))); // NOI18N

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/instagram.png"))); // NOI18N

        javax.swing.GroupLayout pn_Contact1Layout = new javax.swing.GroupLayout(pn_Contact1);
        pn_Contact1.setLayout(pn_Contact1Layout);
        pn_Contact1Layout.setHorizontalGroup(
            pn_Contact1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Contact1Layout.createSequentialGroup()
                .addGap(0, 48, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pn_Contact1Layout.setVerticalGroup(
            pn_Contact1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn_Contact1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pn_Contact1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1580, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pn_infoEmployee1, javax.swing.GroupLayout.PREFERRED_SIZE, 1610, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1370, 1370, 1370)
                        .addComponent(pn_Contact1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pn_Table1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(147, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(pn_infoEmployee1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(pn_Table1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pn_Contact1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 113, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tăng ca", jPanel2);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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
        jLabel6.setText("Loại  Ca");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, 210, -1));

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

        pn_infoEmployee.setBackground(new java.awt.Color(255, 255, 255));
        pn_infoEmployee.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pn_infoEmployee.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel22.setText("Mã loại ca");
        pn_infoEmployee.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, -1, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel23.setText("Tên loại ca");
        pn_infoEmployee.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, -1, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel24.setText("Số tiền");
        pn_infoEmployee.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, -1, -1));

        btn_Delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Delete.png"))); // NOI18N
        btn_Delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_DeleteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_DeleteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_DeleteMouseExited(evt);
            }
        });
        pn_infoEmployee.add(btn_Delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 20, 169, 44));

        btn_Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Add.png"))); // NOI18N
        btn_Add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_AddMouseClicked(evt);
            }
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ClearMouseClicked(evt);
            }
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_UpdateMouseClicked(evt);
            }
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_LastMouseClicked(evt);
            }
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_FirstMouseClicked(evt);
            }
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_PrevMouseClicked(evt);
            }
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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_NextMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_NextMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_NextMouseExited(evt);
            }
        });
        pn_infoEmployee.add(btn_Next, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 150, 74, 49));

        txt_IdTS.setEditable(false);
        txt_IdTS.setBackground(new java.awt.Color(255, 255, 255));
        txt_IdTS.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        txt_IdTS.setForeground(new java.awt.Color(236, 56, 188));
        txt_IdTS.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_IdTS.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        txt_IdTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_IdTSActionPerformed(evt);
            }
        });
        pn_infoEmployee.add(txt_IdTS, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 70, 180, 19));

        txt_NameTS.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        txt_NameTS.setForeground(new java.awt.Color(236, 56, 188));
        txt_NameTS.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_NameTS.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        txt_NameTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NameTSActionPerformed(evt);
            }
        });
        pn_infoEmployee.add(txt_NameTS, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 110, 180, 19));

        txt_Money.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        txt_Money.setForeground(new java.awt.Color(236, 56, 188));
        txt_Money.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_Money.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        txt_Money.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MoneyActionPerformed(evt);
            }
        });
        pn_infoEmployee.add(txt_Money, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, 100, 19));

        lbl_VND2.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        lbl_VND2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_VND2.setText("VNĐ");
        lbl_VND2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        pn_infoEmployee.add(lbl_VND2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, 78, -1));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tbl_TS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tbl_TS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "Huỳnh Văn Ni", "0123123123"},
                {"2", "Võ Quốc Tuấn", "0123456789"},
                {"3", "Bùi Thanh Bùi", "01648163518"},
                {"4", "Philip Lee", "0987654321"}
            },
            new String [] {
                "Mã loại ca", "Tên loại ca", "Sô tiền"
            }
        ));
        tbl_TS.setFocusable(false);
        tbl_TS.setGridColor(new java.awt.Color(236, 56, 188));
        tbl_TS.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tbl_TS.setRowHeight(25);
        tbl_TS.setSelectionBackground(new java.awt.Color(84, 179, 237));
        tbl_TS.getTableHeader().setReorderingAllowed(false);
        tbl_TS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_TSMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_TS);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1580, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pn_infoEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 1610, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pn_Table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1370, 1370, 1370)
                        .addComponent(pn_Contact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(pn_infoEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pn_Table, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(550, 550, 550)
                        .addComponent(pn_Contact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 120, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Loại Tăng ca", jPanel1);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1830, 1170));
    }// </editor-fold>//GEN-END:initComponents

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

    private void txt_NameTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NameTSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NameTSActionPerformed

    private void txt_IdTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_IdTSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_IdTSActionPerformed

    private void txt_MoneyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MoneyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MoneyActionPerformed

    private void lbl_dateFormatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_dateFormatMouseClicked
        dateChooser1.showPopup();
    }//GEN-LAST:event_lbl_dateFormatMouseClicked

    private void txt_DateOTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_DateOTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_DateOTActionPerformed

    private void btn_Delete1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Delete1MouseEntered
        btn_Delete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverDelete.png"))); // NOI18N
    }//GEN-LAST:event_btn_Delete1MouseEntered

    private void btn_Delete1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Delete1MouseExited
        btn_Delete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Delete.png"))); // NOI18N
    }//GEN-LAST:event_btn_Delete1MouseExited

    private void btn_Add1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Add1MouseEntered
         btn_Add1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverSetOT.png"))); // NOI18N
    }//GEN-LAST:event_btn_Add1MouseEntered

    private void btn_Add1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Add1MouseExited
        btn_Add1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_SetOverTime.png"))); // NOI18N
    }//GEN-LAST:event_btn_Add1MouseExited

    private void btn_Clear1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Clear1MouseEntered
        btn_Clear1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverClear.png"))); // NOI18N
    }//GEN-LAST:event_btn_Clear1MouseEntered

    private void btn_Clear1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Clear1MouseExited
        btn_Clear1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Clear.png"))); // NOI18N
    }//GEN-LAST:event_btn_Clear1MouseExited

    private void btn_Update1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Update1MouseEntered
        btn_Update1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverUpdate.png"))); // NOI18N
    }//GEN-LAST:event_btn_Update1MouseEntered

    private void btn_Update1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Update1MouseExited
        btn_Update1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Update.png"))); // NOI18N
    }//GEN-LAST:event_btn_Update1MouseExited

    private void btn_Last1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Last1MouseEntered
        btn_Last1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverLast.png"))); // NOI18N
    }//GEN-LAST:event_btn_Last1MouseEntered

    private void btn_Last1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Last1MouseExited
        btn_Last1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Last.png"))); // NOI18N
    }//GEN-LAST:event_btn_Last1MouseExited

    private void btn_First1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_First1MouseEntered
        btn_First1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverFirst.png"))); // NOI18N
    }//GEN-LAST:event_btn_First1MouseEntered

    private void btn_First1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_First1MouseExited
        btn_First1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_First.png"))); // NOI18N
    }//GEN-LAST:event_btn_First1MouseExited

    private void btn_Prev1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Prev1MouseEntered
        btn_Prev1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverPrev.png"))); // NOI18N
    }//GEN-LAST:event_btn_Prev1MouseEntered

    private void btn_Prev1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Prev1MouseExited
        btn_Prev1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Prev.png"))); // NOI18N
    }//GEN-LAST:event_btn_Prev1MouseExited

    private void btn_Next1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Next1MouseEntered
        btn_Next1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverNext.png"))); // NOI18N
    }//GEN-LAST:event_btn_Next1MouseEntered

    private void btn_Next1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Next1MouseExited
        btn_Next1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Next.png"))); // NOI18N
    }//GEN-LAST:event_btn_Next1MouseExited

    private void txt_HNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_HNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_HNActionPerformed

    private void txt_MoneyOTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MoneyOTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MoneyOTActionPerformed

    private void txt_NameEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NameEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NameEmpActionPerformed

    private void btn_AddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_AddMouseClicked
        if(checkInputDataTS() == true){
            insertTS();
        }
    }//GEN-LAST:event_btn_AddMouseClicked

    private void txt_IdOTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_IdOTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_IdOTActionPerformed

    private void tbl_TSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_TSMouseClicked
        if (evt.getClickCount() == 1) {
            this.rowTS = tbl_TS.getSelectedRow();
            this.editTS();
        }
    }//GEN-LAST:event_tbl_TSMouseClicked

    private void tbl_OTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_OTMouseClicked
        if (evt.getClickCount() == 1) {
            this.rowOT = tbl_OT.getSelectedRow();
            this.editOT();
        }
    }//GEN-LAST:event_tbl_OTMouseClicked

    private void btn_ClearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ClearMouseClicked
        clearFormTS();
    }//GEN-LAST:event_btn_ClearMouseClicked

    private void btn_Clear1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Clear1MouseClicked
        clearFormOT();
    }//GEN-LAST:event_btn_Clear1MouseClicked

    private void btn_Add1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Add1MouseClicked
        if(checkInputDataOT() == true){
            insertOT();
        }
    }//GEN-LAST:event_btn_Add1MouseClicked

    private void cbb_idEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_idEmployeeActionPerformed
        if(cbb_idEmployee.getSelectedItem() != null){
            txt_NameEmp.setText(chooseCbbToValue(0, cbb_idEmployee.getSelectedItem().toString()));
            lbl_Img.setToolTipText(chooseCbbToValue(2, cbb_idEmployee.getSelectedItem().toString()));
            if (cbb_idEmployee.getSelectedItem() != null) {
                lbl_Img.setIcon(XImage.read(chooseCbbToValue(2, cbb_idEmployee.getSelectedItem().toString())));
            }
        }
    }//GEN-LAST:event_cbb_idEmployeeActionPerformed

    private void cbb_TSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_TSActionPerformed
        if(cbb_TS.getSelectedItem() != null){
            txt_MoneyOT.setText(chooseCbbToValue(1, cbb_TS.getSelectedItem().toString()));
        }
    }//GEN-LAST:event_cbb_TSActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        fillTableOT();
        fillTableTS();
        fillCbbEmp();
        fillCbbTS();
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void btn_Delete1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Delete1MouseClicked
        if (tbl_OT.getSelectedRow() == -1) {
            MsgBox.alert(this, "Bạn chưa chọn dòng muốn xoá");
        } else {
            deleteOT();
        }
    }//GEN-LAST:event_btn_Delete1MouseClicked

    private void btn_Update1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Update1MouseClicked
        if (tbl_OT.getSelectedRow() == -1) {
            MsgBox.alert(this, "Bạn chưa chọn dòng muốn xoá");
        } else {
            updateOT();
        }
    }//GEN-LAST:event_btn_Update1MouseClicked

    private void btn_DeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_DeleteMouseClicked
        if (tbl_TS.getSelectedRow() == -1) {
            MsgBox.alert(this, "Bạn chưa chọn dòng muốn xoá");
        } else {
            deleteTS();
        }
    }//GEN-LAST:event_btn_DeleteMouseClicked

    private void btn_UpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_UpdateMouseClicked
        if (tbl_TS.getSelectedRow() == -1) {
            MsgBox.alert(this, "Bạn chưa chọn dòng muốn xoá");
        } else {
            updateTS();
        }
    }//GEN-LAST:event_btn_UpdateMouseClicked

    private void btn_FirstMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_FirstMouseClicked
        firstTS();
    }//GEN-LAST:event_btn_FirstMouseClicked

    private void btn_PrevMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_PrevMouseClicked
        prevTS();
    }//GEN-LAST:event_btn_PrevMouseClicked

    private void btn_NextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_NextMouseClicked
        nextTS();
    }//GEN-LAST:event_btn_NextMouseClicked

    private void btn_LastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LastMouseClicked
        lastTS();
    }//GEN-LAST:event_btn_LastMouseClicked

    private void btn_First1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_First1MouseClicked
        firstOT();
    }//GEN-LAST:event_btn_First1MouseClicked

    private void btn_Prev1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Prev1MouseClicked
        prevOT();
    }//GEN-LAST:event_btn_Prev1MouseClicked

    private void btn_Next1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Next1MouseClicked
        nextOT();
    }//GEN-LAST:event_btn_Next1MouseClicked

    private void btn_Last1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Last1MouseClicked
        lastOT();
    }//GEN-LAST:event_btn_Last1MouseClicked

    public void deleteOT() {
        String id = txt_IdOT.getText();
        if (MsgBox.confirm(this, "Bạn thực sự muốn xóa ?")) {
            try {
                otDAO.delete(id);
                this.fillTableOT();
                this.clearFormOT();
                MsgBox.alert(this, "Xóa thành công");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại");
            }
        }
    }

    public void updateOT() {
        if (checkInputDataOT()== true) {
            com.Nhom5.Entity.OverTime ot = getFormOT();
            try {
                otDAO.update(ot);
                this.fillTableOT();
                clearFormOT();
                MsgBox.alert(this, "Cập nhật thành công");
            } catch (Exception e) {
                MsgBox.alert(this, "Cập nhật thất bại");

            }
        }

    }
    
    public void deleteTS() {
        String id = txt_IdTS.getText();
        if (MsgBox.confirm(this, "Bạn thực sự muốn xóa ?")) {
            try {
                tsDAO.delete(id);
                this.fillTableTS();
                this.clearFormTS();
                MsgBox.alert(this, "Xóa thành công");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại");
            }
        }
    }

    public void updateTS() {
        if (checkInputDataTS()== true) {
            com.Nhom5.Entity.TypeShift ts = getFormTS();
            try {
                tsDAO.update(ts);
                this.fillTableTS();
                clearFormTS();
                MsgBox.alert(this, "Cập nhật thành công");
            } catch (Exception e) {
                MsgBox.alert(this, "Cập nhật thất bại");

            }
        }

    }
    
    public String chooseCbbToValue(int checkCbb, String input) {
        if (checkCbb == 0) {// cbb id employee
            EmloyeeDAO empDAO = new EmloyeeDAO();
            Employee emp = new Employee();
            emp = empDAO.selectById(input);
            return emp.getNameEmployee();
        } else if (checkCbb == 1) { // cbb name ts
            TypeShiftDAO tsDAO = new TypeShiftDAO();
            TypeShift ts = new TypeShift();
            ts = tsDAO.selectById(nameCbbToId(input));
            return String.valueOf(ts.getMonney());
        }else if (checkCbb == 2) {// cbb img employee
            EmloyeeDAO empDAO = new EmloyeeDAO();
            Employee emp = new Employee();
            emp = empDAO.selectById(input);
            return emp.getImg();
        }
        return "";
    }

    public void editTS() {
        String idTS = (String) tbl_TS.getValueAt(this.rowTS, 0);
        TypeShift ts = tsDAO.selectById(idTS);
        this.setFormTS(ts);
    }

    public void editOT() {
        String idOT = (String) tbl_OT.getValueAt(this.rowOT, 0);
        com.Nhom5.Entity.OverTime ot = otDAO.selectById(idOT);
        this.setFormOT(ot);
    }

    public void insertOT() {
        otDAO.insert(getFormOT());
        fillTableOT();
        clearFormOT();
        MsgBox.alert(this, "Thêm thành công");
    }

    public com.Nhom5.Entity.OverTime getFormOT() {
        com.Nhom5.Entity.OverTime ot = new com.Nhom5.Entity.OverTime();
        ot.setIdOverTime(txt_IdOT.getText());
        ot.setIdTypeShift(nameCbbToId(cbb_TS.getSelectedItem().toString()));
        ot.setIdEmployee(cbb_idEmployee.getSelectedItem().toString());
        ot.setNameEmployee(txt_NameEmp.getText());
        ot.setImgEmployee(lbl_Img.getToolTipText());
        ot.setHoursNumber(Float.parseFloat(txt_HN.getText()));
        ot.setDateOverTime(dateToSQL(txt_DateOT.getText()));
        ot.setNameTypeShift(cbb_TS.getSelectedItem().toString());
        ot.setMoney(Integer.parseInt(txt_MoneyOT.getText()));
        return ot;
    }

    public void insertTS() {
        tsDAO.insert(getFormTS());
        fillTableTS();
        clearFormTS();
        MsgBox.alert(this, "Thêm thành công");
    }

    public TypeShift getFormTS() {
        TypeShift ts = new TypeShift();
        ts.setId_TypeShift(txt_IdTS.getText());
        ts.setName_TypeShift(txt_NameTS.getText());
        ts.setMonney(Integer.parseInt(txt_Money.getText()));
        return ts;
    }

    void clearFormTS() {
        txt_IdTS.setText("");
        txt_NameTS.setText("");
        txt_Money.setText("");
    }

    void clearFormOT() {
        lbl_Img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Images/user.png"))); // NOI18N);
        txt_NameEmp.setText("Họ và tên");
        txt_IdOT.setText("");
        txt_HN.setText("");
        txt_MoneyOT.setText("");
        txt_DateOT.setText("");
    }

    public String nameCbbToId(String name) {
        TypeShiftDAO tsDAO = new TypeShiftDAO();
        TypeShift ts = new TypeShift();
        ts = tsDAO.selectByName(name);
        return ts.getId_TypeShift();
    }

    public String dateFormat(String date){
        String[] s = String.valueOf(date).split("-");
        String x = "";
        x = s[2]+"-"+s[1]+"-"+s[0];
        return x;
    }
    public String dateToSQL(String date){
        String[] s = String.valueOf(date).split("-");
        String x = "";
        x = s[2]+"-"+s[1]+"-"+s[0];
        return x;
    }
    
    public void firstOT() {
        this.rowOT = 0;
        tbl_OT.setRowSelectionInterval(rowOT, rowOT);
        this.editOT();

    }

    public void prevOT() {
        if (this.rowOT > 0) {
            this.rowOT--;
            tbl_OT.setRowSelectionInterval(rowOT, rowOT);
            this.editOT();
        }
    }

    public void nextOT() {
        if (this.rowOT < tbl_OT.getRowCount() - 1) {
            this.rowOT++;
            tbl_OT.setRowSelectionInterval(rowOT, rowOT);
            this.editOT();
        }
    }

    public void lastOT() {
        this.rowOT = tbl_OT.getRowCount() - 1;
        tbl_OT.setRowSelectionInterval(rowOT, rowOT);
        this.editOT();
    }
    
    public void firstTS() {
        this.rowTS = 0;
        tbl_TS.setRowSelectionInterval(rowTS, rowTS);
        this.editTS();

    }

    public void prevTS() {
        if (this.rowTS > 0) {
            this.rowTS--;
            tbl_TS.setRowSelectionInterval(rowTS, rowTS);
            this.editTS();
        }
    }

    public void nextTS() {
        if (this.rowTS < tbl_TS.getRowCount() - 1) {
            this.rowTS++;
            tbl_TS.setRowSelectionInterval(rowTS, rowTS);
            this.editTS();
        }
    }

    public void lastTS() {
        this.rowTS = tbl_TS.getRowCount() - 1;
        tbl_TS.setRowSelectionInterval(rowTS, rowTS);
        this.editTS();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_Add;
    private javax.swing.JLabel btn_Add1;
    private javax.swing.JLabel btn_Clear;
    private javax.swing.JLabel btn_Clear1;
    private javax.swing.JLabel btn_Delete;
    private javax.swing.JLabel btn_Delete1;
    private javax.swing.JLabel btn_First;
    private javax.swing.JLabel btn_First1;
    private javax.swing.JLabel btn_Last;
    private javax.swing.JLabel btn_Last1;
    private javax.swing.JLabel btn_Next;
    private javax.swing.JLabel btn_Next1;
    private javax.swing.JLabel btn_Prev;
    private javax.swing.JLabel btn_Prev1;
    private javax.swing.JLabel btn_Update;
    private javax.swing.JLabel btn_Update1;
    private javax.swing.JComboBox<String> cbb_TS;
    private javax.swing.JComboBox<String> cbb_idEmployee;
    private com.Nhom5.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.Nhom5.Swing.BoderRadius lbl_Avatar;
    private com.Nhom5.Swing.BoderRadius lbl_Avatar1;
    private javax.swing.JLabel lbl_DutyOfUser;
    private javax.swing.JLabel lbl_DutyOfUser1;
    private com.Nhom5.Swing.BoderRadius lbl_Img;
    private javax.swing.JLabel lbl_NameOfUser;
    private javax.swing.JLabel lbl_NameOfUser1;
    private javax.swing.JLabel lbl_VND1;
    private javax.swing.JLabel lbl_VND2;
    private javax.swing.JLabel lbl_dateFormat;
    private javax.swing.JPanel pn_Contact;
    private javax.swing.JPanel pn_Contact1;
    private com.Nhom5.Components.pnGradientColor pn_Table;
    private com.Nhom5.Components.pnGradientColor pn_Table1;
    private javax.swing.JPanel pn_infoEmployee;
    private javax.swing.JPanel pn_infoEmployee1;
    private javax.swing.JTable tbl_OT;
    private javax.swing.JTable tbl_TS;
    private javax.swing.JTextField txt_DateOT;
    private javax.swing.JTextField txt_HN;
    private javax.swing.JTextField txt_IdOT;
    private javax.swing.JTextField txt_IdTS;
    private javax.swing.JTextField txt_Money;
    private javax.swing.JTextField txt_MoneyOT;
    private javax.swing.JTextField txt_NameEmp;
    private javax.swing.JTextField txt_NameTS;
    // End of variables declaration//GEN-END:variables
    public boolean checkInputDataOT() {
        if (txt_HN.getText().equals("") == true || txt_MoneyOT.getText().equals("") == true || txt_DateOT.equals("") == true ) {
            MsgBox.alert(this, "Dữ liệu đầu vào không hợp lệ");
            return false;
        }
        try {
            float x = Float.parseFloat(txt_HN.getText());
        } catch (Exception e) {
            MsgBox.alert(this, "Không nhập ký tự cho giờ !");
            return false;
        }
        return true;
    }
    public boolean checkInputDataTS() {
        if (txt_NameTS.getText().equals("") == true || txt_Money.getText().equals("") == true ) {
            MsgBox.alert(this, "Dữ liệu đầu vào không hợp lệ");
            return false;
        }
        try {
            int x = Integer.parseInt(txt_Money.getText());
        } catch (Exception e) {
            MsgBox.alert(this, "Không nhập ký tự cho Tiền !");
            return false;
        }
        return true;
    }
}
