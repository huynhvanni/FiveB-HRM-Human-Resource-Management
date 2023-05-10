/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.Nhom5.UI2;

import com.Nhom5.DAO.DegreeDAO;
import com.Nhom5.DAO.DutyDAO;
import com.Nhom5.DAO.EmloyeeDAO;
import com.Nhom5.DAO.SalaryAdvanceDAO;
import com.Nhom5.DAO.TypeShiftDAO;
import com.Nhom5.Entity.Degree;
import com.Nhom5.Entity.Duty;
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
public class PnDegree extends javax.swing.JPanel {

    User u;
    final DefaultComboBoxModel ModelEmployee = new DefaultComboBoxModel();
    DegreeDAO dao = new DegreeDAO();
    ArrayList<com.Nhom5.Entity.Degree> list = new ArrayList<com.Nhom5.Entity.Degree>();
    int rowOT = -1;

    public PnDegree() {
        initComponents();
        table();

    }

    public PnDegree(User entity) {
        initComponents();
        table();
        this.u = entity;
        setInforUser();
        fillTable();
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

    public Degree getForm() {
        Degree sa = new Degree();
        if(txt_Id.getText().equals("") == false){
             sa.setIdDegree(Integer.valueOf(txt_Id.getText()));
        }
        sa.setNameDegree(txt_Name.getText());
        return sa;
    }

    public void setFormOT(Degree entity) {
        txt_Id.setText(String.valueOf(entity.getIdDegree()));
        txt_Name.setText(entity.getNameDegree());
    }

    

    
    public void insert() {
        dao.insert(getForm());
        fillTable();
        clearForm();
        MsgBox.alert(this, "Thêm thành công");
    }

    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tbl_OT.getModel();
        model.setRowCount(0);
        list.clear();
        try {
            List<com.Nhom5.Entity.Degree> list = dao.selectAll(); // Đọc dữ liệu từ CSDL
            for (com.Nhom5.Entity.Degree e : list) {
                Object[] data = {
                    e.getIdDegree(),
                    e.getNameDegree(),};
                this.list.add(e);
                model.addRow(data);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    void clearForm() {
        txt_Id.setText("");
        txt_Name.setText("");
    }

    public boolean checkInputData() {
        if(txt_Name.getText().equals("") == true){
            MsgBox.alert(this, "Vui lòng nhập tên chức vụ!");
            return false;
        }
        return true;
    }

    public void update() {
        if (checkInputData() == true) {
            com.Nhom5.Entity.Degree sa = getForm();
            try {
                dao.update(sa);
                this.fillTable();
                clearForm();
                MsgBox.alert(this, "Cập nhật thành công");
            } catch (Exception e) {
                MsgBox.alert(this, "Cập nhật thất bại");

            }
        }

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

    public void editOT() {
        String idOT = tbl_OT.getValueAt(this.rowOT, 0).toString();
        com.Nhom5.Entity.Degree ot = dao.selectById_1(idOT);
        this.setFormOT(ot);
    }

    public void delete() {
        String id = txt_Id.getText();
        if (MsgBox.confirm(this, "Bạn thực sự muốn xóa ?")) {
            try {
                dao.delete(id);
                this.fillTable();
                this.clearForm();
                MsgBox.alert(this, "Xóa thành công");
            } catch (Exception e) {
                MsgBox.alert(this, "Xóa thất bại");
            }
        }
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
        jLabel27 = new javax.swing.JLabel();
        btn_Delete1 = new javax.swing.JLabel();
        btn_Add1 = new javax.swing.JLabel();
        btn_Clear1 = new javax.swing.JLabel();
        btn_Update1 = new javax.swing.JLabel();
        btn_Last1 = new javax.swing.JLabel();
        btn_First1 = new javax.swing.JLabel();
        btn_Prev1 = new javax.swing.JLabel();
        btn_Next1 = new javax.swing.JLabel();
        txt_Name = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txt_Id = new javax.swing.JTextField();

        dateChooser1.setForeground(new java.awt.Color(236, 56, 188));

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        tbl_OT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tbl_OT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã trình độ", "Tên trình độ"
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
        jLabel6.setText("Trình độ");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, 210, -1));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 1580, 150));

        pn_infoEmployee1.setBackground(new java.awt.Color(255, 255, 255));
        pn_infoEmployee1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pn_infoEmployee1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel27.setText("Tên trình độ");
        pn_infoEmployee1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, -1, -1));

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

        btn_Add1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Add.png"))); // NOI18N
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

        txt_Name.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        txt_Name.setForeground(new java.awt.Color(236, 56, 188));
        txt_Name.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_Name.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        txt_Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NameActionPerformed(evt);
            }
        });
        pn_infoEmployee1.add(txt_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 110, 180, 19));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel29.setText("Mã trình độ");
        pn_infoEmployee1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, -1, -1));

        txt_Id.setEditable(false);
        txt_Id.setBackground(new java.awt.Color(255, 255, 255));
        txt_Id.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        txt_Id.setForeground(new java.awt.Color(236, 56, 188));
        txt_Id.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_Id.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(84, 179, 237)));
        txt_Id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_IdActionPerformed(evt);
            }
        });
        pn_infoEmployee1.add(txt_Id, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, 180, 19));

        add(pn_infoEmployee1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 1610, 220));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_Delete1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Delete1MouseClicked
        if (tbl_OT.getSelectedRow() == -1) {
            MsgBox.alert(this, "Bạn chưa chọn dòng muốn xoá");
        } else {
            delete();
        }
    }//GEN-LAST:event_btn_Delete1MouseClicked

    private void btn_Delete1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Delete1MouseEntered
        btn_Delete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverDelete.png"))); // NOI18N
    }//GEN-LAST:event_btn_Delete1MouseEntered

    private void btn_Delete1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Delete1MouseExited
        btn_Delete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Delete.png"))); // NOI18N
    }//GEN-LAST:event_btn_Delete1MouseExited

    private void btn_Add1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Add1MouseClicked
        if (checkInputData() == true) {
            insert();
        }
    }//GEN-LAST:event_btn_Add1MouseClicked

    private void btn_Add1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Add1MouseEntered
        btn_Add1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverAdd.png"))); // NOI18N
    }//GEN-LAST:event_btn_Add1MouseEntered

    private void btn_Add1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Add1MouseExited
        btn_Add1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Add.png"))); // NOI18N
    }//GEN-LAST:event_btn_Add1MouseExited

    private void btn_Clear1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Clear1MouseClicked
        clearForm();
    }//GEN-LAST:event_btn_Clear1MouseClicked

    private void btn_Clear1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Clear1MouseEntered
        btn_Clear1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverClear.png"))); // NOI18N
    }//GEN-LAST:event_btn_Clear1MouseEntered

    private void btn_Clear1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Clear1MouseExited
        btn_Clear1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Clear.png"))); // NOI18N
    }//GEN-LAST:event_btn_Clear1MouseExited

    private void btn_Update1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Update1MouseClicked
        if (tbl_OT.getSelectedRow() == -1) {
            MsgBox.alert(this, "Bạn chưa chọn dòng muốn cập nhật");
        } else {
            update();
        }
    }//GEN-LAST:event_btn_Update1MouseClicked

    private void btn_Update1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Update1MouseEntered
        btn_Update1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverUpdate.png"))); // NOI18N
    }//GEN-LAST:event_btn_Update1MouseEntered

    private void btn_Update1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Update1MouseExited
        btn_Update1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Update.png"))); // NOI18N
    }//GEN-LAST:event_btn_Update1MouseExited

    private void btn_Last1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Last1MouseClicked
        lastOT();
    }//GEN-LAST:event_btn_Last1MouseClicked

    private void btn_Last1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Last1MouseEntered
        btn_Last1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverLast.png"))); // NOI18N
    }//GEN-LAST:event_btn_Last1MouseEntered

    private void btn_Last1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Last1MouseExited
        btn_Last1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Last.png"))); // NOI18N
    }//GEN-LAST:event_btn_Last1MouseExited

    private void btn_First1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_First1MouseClicked
        firstOT();
    }//GEN-LAST:event_btn_First1MouseClicked

    private void btn_First1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_First1MouseEntered
        btn_First1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverFirst.png"))); // NOI18N
    }//GEN-LAST:event_btn_First1MouseEntered

    private void btn_First1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_First1MouseExited
        btn_First1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_First.png"))); // NOI18N
    }//GEN-LAST:event_btn_First1MouseExited

    private void btn_Prev1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Prev1MouseClicked
        prevOT();
    }//GEN-LAST:event_btn_Prev1MouseClicked

    private void btn_Prev1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Prev1MouseEntered
        btn_Prev1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverPrev.png"))); // NOI18N
    }//GEN-LAST:event_btn_Prev1MouseEntered

    private void btn_Prev1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Prev1MouseExited
        btn_Prev1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Prev.png"))); // NOI18N
    }//GEN-LAST:event_btn_Prev1MouseExited

    private void btn_Next1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Next1MouseClicked
        nextOT();
    }//GEN-LAST:event_btn_Next1MouseClicked

    private void btn_Next1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Next1MouseEntered
        btn_Next1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_HoverNext.png"))); // NOI18N
    }//GEN-LAST:event_btn_Next1MouseEntered

    private void btn_Next1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Next1MouseExited
        btn_Next1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Nhom5/Icons/btn_Next.png"))); // NOI18N
    }//GEN-LAST:event_btn_Next1MouseExited

    private void txt_NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NameActionPerformed

    private void txt_IdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_IdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_IdActionPerformed

    private void tbl_OTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_OTMouseClicked
        if (evt.getClickCount() == 1) {
            this.rowOT = tbl_OT.getSelectedRow();
            this.editOT();
        }
    }//GEN-LAST:event_tbl_OTMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_Add1;
    private javax.swing.JLabel btn_Clear1;
    private javax.swing.JLabel btn_Delete1;
    private javax.swing.JLabel btn_First1;
    private javax.swing.JLabel btn_Last1;
    private javax.swing.JLabel btn_Next1;
    private javax.swing.JLabel btn_Prev1;
    private javax.swing.JLabel btn_Update1;
    private com.Nhom5.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private com.Nhom5.Swing.BoderRadius lbl_Avatar;
    private javax.swing.JLabel lbl_DutyOfUser;
    private javax.swing.JLabel lbl_NameOfUser;
    private javax.swing.JPanel pn_Contact;
    private com.Nhom5.Components.pnGradientColor pn_Table;
    private javax.swing.JPanel pn_infoEmployee1;
    private javax.swing.JTable tbl_OT;
    private javax.swing.JTextField txt_Id;
    private javax.swing.JTextField txt_Name;
    // End of variables declaration//GEN-END:variables

   
}
