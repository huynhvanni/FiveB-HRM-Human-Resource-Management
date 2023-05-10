/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.Nhom5.UI2;

import com.Nhom5.DAO.EmloyeeDAO;
import com.Nhom5.DAO.SalaryAdvanceDAO;
import com.Nhom5.DAO.TableSalaryDAO;
import com.Nhom5.DAO.TypeShiftDAO;
import com.Nhom5.Entity.Employee;
import com.Nhom5.Entity.SalaryAdvance;
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
public class TableSalary extends javax.swing.JPanel {

    User u;
    final DefaultComboBoxModel ModelEmployee = new DefaultComboBoxModel();
    TableSalaryDAO dao = new TableSalaryDAO();
    ArrayList<com.Nhom5.Entity.TableSalary> list = new ArrayList<com.Nhom5.Entity.TableSalary>();
    int rowOT = -1;

    public TableSalary() {
        initComponents();
        table();

    }

    public TableSalary(User entity) {
        initComponents();
        table();
        fillCbbEmp();
        this.u = entity;
        setInforUser();
        checkRole();

    }

    // Table
    public void table() {
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

    public String chooseCbbToValue(int checkCbb, String input) {
        if (checkCbb == 0) {// cbb id employee
            EmloyeeDAO empDAO = new EmloyeeDAO();
            Employee emp = new Employee();
            emp = empDAO.selectById(input);
            return emp.getNameEmployee();
        }
        if (checkCbb == 2) {// cbb img employee
            EmloyeeDAO empDAO = new EmloyeeDAO();
            Employee emp = new Employee();
            emp = empDAO.selectById(input);
            return emp.getImg();
        }
        return "";
    }

    public com.Nhom5.Entity.TableSalary getForm() {
        com.Nhom5.Entity.TableSalary sa = new com.Nhom5.Entity.TableSalary();
        sa.setIdEmployee(cbb_idEmployee.getSelectedItem().toString());
        sa.setNameEmp(txt_NameEmp.getText());
        sa.setImgEmp(lbl_Avatar.getToolTipText());
        sa.setSalaryBasic(Integer.valueOf(txt_SalaryBasic.getText()));
        sa.setSumAdvance(Integer.valueOf(txt_Advance.getText()));
        sa.setSumAllowance(Integer.valueOf(txt_Allowance.getText()));
        sa.setSumOT(Integer.valueOf(txt_OT.getText()));
        sa.setDate(cbbToDate(cbb_Year.getSelectedItem().toString(), cbb_Month.getSelectedItem().toString()));
        return sa;
    }

    public void setForm(com.Nhom5.Entity.TableSalary entity) {
        lbl_Img.setToolTipText(entity.getImgEmp());
        if (entity.getImgEmp() != null) {
            lbl_Img.setIcon(XImage.read(entity.getImgEmp()));
        }
        txt_SalaryBasic.setText(String.valueOf(entity.getSalaryBasic()));
        txt_NameEmp.setText(entity.getNameEmp());
        txt_Advance.setText(String.valueOf(entity.getSumAdvance()));
        txt_Allowance.setText(String.valueOf(entity.getSumAllowance()));
        txt_OT.setText(String.valueOf(entity.getSumOT()));
        cbb_idEmployee.setSelectedItem(entity.getIdEmployee());
        txt_FinalSalary.setText(String.valueOf(entity.getSalaryBasic() + entity.getSumOT() + entity.getSumAllowance() - entity.getSumAdvance()));
    }

    public String cbbToDate(String year, String month) {
        if (Integer.parseInt(month) == 1 || Integer.parseInt(month) == 3 || Integer.parseInt(month) == 5 || Integer.parseInt(month) == 7 || Integer.parseInt(month) == 8 || Integer.parseInt(month) == 10 || Integer.parseInt(month) == 12) {
            return year + "-" + month + "-" + "31";
        } else if (Integer.parseInt(month) == 4 || Integer.parseInt(month) == 6 || Integer.parseInt(month) == 9 || Integer.parseInt(month) == 11) {
            return year + "-" + month + "-" + "30";
        }
        return year + "-" + month + "-" + "28";
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

    public String dateFormat(String date) {
        String[] s = String.valueOf(date).split("-");
        String x = "";
        x = s[2] + "-" + s[1] + "-" + s[0];
        return x;
    }

    public String dateToSQL(String date) {
        String[] s = String.valueOf(date).split("-");
        String x = "";
        x = s[2] + "-" + s[1] + "-" + s[0];
        return x;
    }

    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tbl_OT.getModel();
        model.setRowCount(0);
        list.clear();
        try {
            String start = cbb_Year.getSelectedItem().toString() + "-" + fomrmatDate(cbb_Month.getSelectedItem().toString()) + "-01";
            String end = cbbToDate(cbb_Year.getSelectedItem().toString(), fomrmatDate(cbb_Month.getSelectedItem().toString()));
            //    List<com.Nhom5.Entity.TableSalary> list = dao.selectAll();
            System.out.println("Start: " + start);
            System.out.println("End: " + end);
            List<com.Nhom5.Entity.TableSalary> list = dao.selectbyDate(start, end); // Đọc dữ liệu từ CSDL
            for (com.Nhom5.Entity.TableSalary e : list) {
                Object[] data = {
                    e.getIdEmployee(),
                    e.getNameEmp(),
                    e.getSalaryBasic(),
                    e.getSumOT(),
                    e.getSumAllowance(),
                    e.getSumAdvance(),
                    e.getSalaryBasic() + e.getSumOT() + e.getSumAllowance() - e.getSumAdvance()};
                this.list.add(e);
                model.addRow(data);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
    
    public void fillTable_1() {
        DefaultTableModel model = (DefaultTableModel) tbl_OT.getModel();
        model.setRowCount(0);
        list.clear();
        try {
            String start = cbb_Year.getSelectedItem().toString() + "-" + fomrmatDate(cbb_Month.getSelectedItem().toString()) + "-01";
            String end = cbbToDate(cbb_Year.getSelectedItem().toString(), fomrmatDate(cbb_Month.getSelectedItem().toString()));
            //    List<com.Nhom5.Entity.TableSalary> list = dao.selectAll();
            System.out.println("Start: " + start);
            System.out.println("End: " + end);
            List<com.Nhom5.Entity.TableSalary> list = dao.selectbyDate_1(start, end, this.u.getId_Emp()); // Đọc dữ liệu từ CSDL
            for (com.Nhom5.Entity.TableSalary e : list) {
                Object[] data = {
                    e.getIdEmployee(),
                    e.getNameEmp(),
                    e.getSalaryBasic(),
                    e.getSumOT(),
                    e.getSumAllowance(),
                    e.getSumAdvance(),
                    e.getSalaryBasic() + e.getSumOT() + e.getSumAllowance() - e.getSumAdvance()};
                this.list.add(e);
                model.addRow(data);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void clearForm() {
        lbl_Img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Images/user.png"))); // NOI18N);
        txt_NameEmp.setText("Họ và tên");
        txt_Advance.setText("");
        txt_SalaryBasic.setText("");
        txt_Allowance.setText("");
        txt_FinalSalary.setText("");
        txt_OT.setText("");
    }

    public String fomrmatDate(String date) {
        String[] s = String.valueOf(date).split(" ");
        return s[1];
    }

//    public void firstOT() {
//        this.rowOT = 0;
//        tbl_OT.setRowSelectionInterval(rowOT, rowOT);
//        this.editOT();
//
//    }
//
//    public void prevOT() {
//        if (this.rowOT > 0) {
//            this.rowOT--;
//            tbl_OT.setRowSelectionInterval(rowOT, rowOT);
//            this.editOT();
//        }
//    }
//
//    public void nextOT() {
//        if (this.rowOT < tbl_OT.getRowCount() - 1) {
//            this.rowOT++;
//            tbl_OT.setRowSelectionInterval(rowOT, rowOT);
//            this.editOT();
//        }
//    }
//
//    public void lastOT() {
//        this.rowOT = tbl_OT.getRowCount() - 1;
//        tbl_OT.setRowSelectionInterval(rowOT, rowOT);
//        this.editOT();
//    }
//    public void editOT() {
//        String idOT = (String) tbl_OT.getValueAt(this.rowOT, 0);
//        com.Nhom5.Entity.TableSalary ot = dao.selectById(idOT);
//        this.setFormOT(ot);
//    }
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
        tbl_OT = new javax.swing.JTable();
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
        pn_infoEmployee1 = new javax.swing.JPanel();
        lbl_Img = new com.Nhom5.Swing.BoderRadius();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        cbb_Month = new javax.swing.JComboBox<>();
        btn_Last1 = new javax.swing.JLabel();
        btn_First1 = new javax.swing.JLabel();
        btn_Prev1 = new javax.swing.JLabel();
        btn_Next1 = new javax.swing.JLabel();
        txt_OT = new javax.swing.JTextField();
        lbl_VND1 = new javax.swing.JLabel();
        txt_NameEmp = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        lbl_VND2 = new javax.swing.JLabel();
        txt_SalaryBasic = new javax.swing.JTextField();
        lbl_VND3 = new javax.swing.JLabel();
        txt_Allowance = new javax.swing.JTextField();
        lbl_VND4 = new javax.swing.JLabel();
        txt_Advance = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txt_FinalSalary = new javax.swing.JTextField();
        lbl_VND5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        cbb_idEmployee = new javax.swing.JComboBox<>();
        cbb_Year = new javax.swing.JComboBox<>();

        dateChooser1.setForeground(new java.awt.Color(236, 56, 188));

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tbl_OT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tbl_OT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Lương cơ bản", "Tổng tăng ca", "Tổng Phụ cấp", "Tổng ứng lương", "Thực lãnh"
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
        jScrollPane1.setViewportView(tbl_OT);

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
        jLabel6.setText("Bảng Lương");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, 210, -1));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 1580, 150));

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

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel26.setText("Tháng");
        pn_infoEmployee1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 70, 60, -1));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel27.setText("Phụ cấp");
        pn_infoEmployee1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, -1, -1));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel28.setText("Ứng lương");
        pn_infoEmployee1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, -1, -1));

        cbb_Month.setBackground(new java.awt.Color(253, 239, 249));
        cbb_Month.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        cbb_Month.setMaximumRowCount(20);
        cbb_Month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng 01", "Tháng 02", "Tháng 03", "Tháng 04", "Tháng 05", "Tháng 06", "Tháng 07", "Tháng 08", "Tháng 09", "Tháng 10", "Tháng 11", "Tháng 12" }));
        cbb_Month.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 224, 239)));
        cbb_Month.setName(""); // NOI18N
        cbb_Month.setOpaque(false);
        cbb_Month.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_MonthActionPerformed(evt);
            }
        });
        pn_infoEmployee1.add(cbb_Month, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 70, 90, 22));

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

        txt_OT.setEditable(false);
        txt_OT.setBackground(new java.awt.Color(255, 255, 255));
        txt_OT.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        txt_OT.setForeground(new java.awt.Color(236, 56, 188));
        txt_OT.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_OT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        txt_OT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_OTActionPerformed(evt);
            }
        });
        pn_infoEmployee1.add(txt_OT, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, 100, 19));

        lbl_VND1.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        lbl_VND1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_VND1.setText("VNĐ");
        lbl_VND1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        pn_infoEmployee1.add(lbl_VND1, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 50, 78, -1));

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
        jLabel29.setText("Lương cơ bản");
        pn_infoEmployee1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, -1, -1));

        lbl_VND2.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        lbl_VND2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_VND2.setText("VNĐ");
        lbl_VND2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        pn_infoEmployee1.add(lbl_VND2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 78, -1));

        txt_SalaryBasic.setEditable(false);
        txt_SalaryBasic.setBackground(new java.awt.Color(255, 255, 255));
        txt_SalaryBasic.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        txt_SalaryBasic.setForeground(new java.awt.Color(236, 56, 188));
        txt_SalaryBasic.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_SalaryBasic.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        txt_SalaryBasic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SalaryBasicActionPerformed(evt);
            }
        });
        pn_infoEmployee1.add(txt_SalaryBasic, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 100, 19));

        lbl_VND3.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        lbl_VND3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_VND3.setText("VNĐ");
        lbl_VND3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        pn_infoEmployee1.add(lbl_VND3, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 90, 78, -1));

        txt_Allowance.setEditable(false);
        txt_Allowance.setBackground(new java.awt.Color(255, 255, 255));
        txt_Allowance.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        txt_Allowance.setForeground(new java.awt.Color(236, 56, 188));
        txt_Allowance.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_Allowance.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        txt_Allowance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_AllowanceActionPerformed(evt);
            }
        });
        pn_infoEmployee1.add(txt_Allowance, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 90, 100, 19));

        lbl_VND4.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        lbl_VND4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_VND4.setText("VNĐ");
        lbl_VND4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        pn_infoEmployee1.add(lbl_VND4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 130, 78, -1));

        txt_Advance.setEditable(false);
        txt_Advance.setBackground(new java.awt.Color(255, 255, 255));
        txt_Advance.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        txt_Advance.setForeground(new java.awt.Color(236, 56, 188));
        txt_Advance.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_Advance.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        txt_Advance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_AdvanceActionPerformed(evt);
            }
        });
        pn_infoEmployee1.add(txt_Advance, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, 100, 19));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel30.setText("Thực lãnh");
        pn_infoEmployee1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 190, -1, -1));

        txt_FinalSalary.setEditable(false);
        txt_FinalSalary.setBackground(new java.awt.Color(255, 255, 255));
        txt_FinalSalary.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        txt_FinalSalary.setForeground(new java.awt.Color(236, 56, 188));
        txt_FinalSalary.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_FinalSalary.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        txt_FinalSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_FinalSalaryActionPerformed(evt);
            }
        });
        pn_infoEmployee1.add(txt_FinalSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 190, 100, 19));

        lbl_VND5.setFont(new java.awt.Font("Segoe UI", 2, 13)); // NOI18N
        lbl_VND5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_VND5.setText("VNĐ");
        lbl_VND5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        pn_infoEmployee1.add(lbl_VND5, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 190, 78, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );

        pn_infoEmployee1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 165, 300, 10));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("-");
        pn_infoEmployee1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 110, 30, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("+");
        pn_infoEmployee1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 30, 30, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("+");
        pn_infoEmployee1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 70, 30, -1));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel31.setText("Tăng ca");
        pn_infoEmployee1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, -1, -1));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel32.setText("Năm");
        pn_infoEmployee1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 70, 50, -1));

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

        cbb_Year.setBackground(new java.awt.Color(253, 239, 249));
        cbb_Year.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        cbb_Year.setMaximumRowCount(20);
        cbb_Year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2022", "2023", "2024", "2025", "2026", "2027", " " }));
        cbb_Year.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(144, 224, 239)));
        cbb_Year.setName(""); // NOI18N
        cbb_Year.setOpaque(false);
        cbb_Year.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_YearActionPerformed(evt);
            }
        });
        pn_infoEmployee1.add(cbb_Year, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 70, 90, 22));

        add(pn_infoEmployee1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 1610, 220));
    }// </editor-fold>//GEN-END:initComponents

    private void cbb_MonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_MonthActionPerformed
        if(this.u.getRole() == 0){
            fillTable();
        }else{
            fillTable_1();
        }
    }//GEN-LAST:event_cbb_MonthActionPerformed

    public void checkRole(){
        if(this.u.getRole() == 1){
            btn_First1.setEnabled(false);
            btn_Last1.setEnabled(false);
            btn_Next1.setEnabled(false);
            btn_Prev1.setEnabled(false);
        }
    }
    
    private void txt_OTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_OTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_OTActionPerformed

    private void txt_NameEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NameEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NameEmpActionPerformed

    private void tbl_OTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_OTMouseClicked
        if (evt.getClickCount() == 1) {
            this.rowOT = tbl_OT.getSelectedRow();
            this.edit();
        }
    }//GEN-LAST:event_tbl_OTMouseClicked

    public void edit() {
        String idOT = (String) tbl_OT.getValueAt(this.rowOT, 0);
        String start = cbb_Year.getSelectedItem().toString() + "-" + fomrmatDate(cbb_Month.getSelectedItem().toString()) + "-01";
        String end = cbbToDate(cbb_Year.getSelectedItem().toString(), fomrmatDate(cbb_Month.getSelectedItem().toString()));
        com.Nhom5.Entity.TableSalary entity = dao.selectByIdEmp(start, end, idOT);
        this.setForm(entity);
    }

    private void txt_SalaryBasicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SalaryBasicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SalaryBasicActionPerformed

    private void txt_AllowanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_AllowanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_AllowanceActionPerformed

    private void txt_AdvanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_AdvanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_AdvanceActionPerformed

    private void txt_FinalSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_FinalSalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_FinalSalaryActionPerformed

    private void cbb_idEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_idEmployeeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_idEmployeeActionPerformed

    private void cbb_YearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_YearActionPerformed
        if(this.u.getRole() == 0){
            fillTable();
        }else{
            fillTable_1();
        }
    }//GEN-LAST:event_cbb_YearActionPerformed

    private void btn_Last1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Last1MouseExited
        btn_Last1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Last.png"))); // NOI18N
    }//GEN-LAST:event_btn_Last1MouseExited

    private void btn_Last1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Last1MouseEntered
        btn_Last1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverLast.png"))); // NOI18N
    }//GEN-LAST:event_btn_Last1MouseEntered

    private void btn_Last1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Last1MouseClicked
        lastOT();
    }//GEN-LAST:event_btn_Last1MouseClicked

    private void btn_Next1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Next1MouseExited
        btn_Next1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Next.png"))); // NOI18N
    }//GEN-LAST:event_btn_Next1MouseExited

    private void btn_Next1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Next1MouseEntered
        btn_Next1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverNext.png"))); // NOI18N
    }//GEN-LAST:event_btn_Next1MouseEntered

    private void btn_Next1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Next1MouseClicked
        nextOT();
    }//GEN-LAST:event_btn_Next1MouseClicked

    private void btn_Prev1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Prev1MouseExited
        btn_Prev1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Prev.png"))); // NOI18N
    }//GEN-LAST:event_btn_Prev1MouseExited

    private void btn_Prev1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Prev1MouseEntered
        btn_Prev1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverPrev.png"))); // NOI18N
    }//GEN-LAST:event_btn_Prev1MouseEntered

    private void btn_Prev1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Prev1MouseClicked
         prevOT();
    }//GEN-LAST:event_btn_Prev1MouseClicked

    private void btn_First1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_First1MouseExited
        btn_First1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_First.png"))); // NOI18N
    }//GEN-LAST:event_btn_First1MouseExited

    private void btn_First1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_First1MouseEntered
        btn_First1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverFirst.png"))); // NOI18N
    }//GEN-LAST:event_btn_First1MouseEntered

    private void btn_First1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_First1MouseClicked
         firstOT();
    }//GEN-LAST:event_btn_First1MouseClicked
    
    public void firstOT() {
        this.rowOT = 0;
        tbl_OT.setRowSelectionInterval(rowOT, rowOT);
        this.edit();

    }

    public void prevOT() {
        if (this.rowOT > 0) {
            this.rowOT--;
            tbl_OT.setRowSelectionInterval(rowOT, rowOT);
            this.edit();
        }
    }

    public void nextOT() {
        if (this.rowOT < tbl_OT.getRowCount() - 1) {
            this.rowOT++;
            tbl_OT.setRowSelectionInterval(rowOT, rowOT);
            this.edit();
        }
    }

    public void lastOT() {
        this.rowOT = tbl_OT.getRowCount() - 1;
        tbl_OT.setRowSelectionInterval(rowOT, rowOT);
        this.edit();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_First1;
    private javax.swing.JLabel btn_Last1;
    private javax.swing.JLabel btn_Next1;
    private javax.swing.JLabel btn_Prev1;
    private javax.swing.JComboBox<String> cbb_Month;
    private javax.swing.JComboBox<String> cbb_Year;
    private javax.swing.JComboBox<String> cbb_idEmployee;
    private com.Nhom5.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private com.Nhom5.Swing.BoderRadius lbl_Avatar;
    private javax.swing.JLabel lbl_DutyOfUser;
    private com.Nhom5.Swing.BoderRadius lbl_Img;
    private javax.swing.JLabel lbl_NameOfUser;
    private javax.swing.JLabel lbl_VND1;
    private javax.swing.JLabel lbl_VND2;
    private javax.swing.JLabel lbl_VND3;
    private javax.swing.JLabel lbl_VND4;
    private javax.swing.JLabel lbl_VND5;
    private javax.swing.JPanel pn_Contact;
    private com.Nhom5.Components.pnGradientColor pn_Table;
    private javax.swing.JPanel pn_infoEmployee1;
    private javax.swing.JTable tbl_OT;
    private javax.swing.JTextField txt_Advance;
    private javax.swing.JTextField txt_Allowance;
    private javax.swing.JTextField txt_FinalSalary;
    private javax.swing.JTextField txt_NameEmp;
    private javax.swing.JTextField txt_OT;
    private javax.swing.JTextField txt_SalaryBasic;
    // End of variables declaration//GEN-END:variables

}
