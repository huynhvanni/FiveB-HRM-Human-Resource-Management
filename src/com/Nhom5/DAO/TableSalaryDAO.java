/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Nhom5.DAO; 

import com.Nhom5.Entity.TableSalary;
import com.Nhom5.Helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TableSalaryDAO extends MainDAO<TableSalary, String>{
     final String SELECT_ALL_SQL = "Select a.ID_Employee, a.Name_Employee, a.Img, a.SalaryBasic, b.[Sum OT], c.[Sum Allowance], d.[Sum Advance] from Employee a	left join (select a.ID_Employee, SUM(b.HoursNumber*c.Money) 'Sum OT'from Employee a inner join OverTime b  on a.ID_Employee = b.ID_Employee inner join TypeShift c on b.ID_TypeShift = c.ID_TypeShift\n" +
"									\n" +
"									Group by a.ID_Employee ) b On a.ID_Employee = b.ID_Employee\n" +
"							left join (select a.ID_Employee, SUM(c.Money) 'Sum Allowance' from Employee a inner join Employee_Allowance b on a.ID_Employee = b.ID_Employee inner join Allowance c on c.ID_Allowance = b.ID_Allowance\n" +
"								\n" +
"									Group by a.ID_Employee) c on a.ID_Employee = c.ID_Employee\n" +
"							left join (select a.ID_Employee, SUM(b.Money)'Sum Advance' from Employee a inner join SalaryAdvance b on a.ID_Employee = b.ID_Employee\n" +
"								\n" +
"									Group by a.ID_Employee) d on a.ID_Employee = d.ID_Employee";
    final String SELECT_By_Id_SQL = "Select a.ID_Employee, a.Name_Employee, a.Img, a.SalaryBasic, b.[Sum OT], c.[Sum Allowance], d.[Sum Advance] from Employee a	left join (select a.ID_Employee, SUM(b.HoursNumber*c.Money) 'Sum OT'from Employee a inner join OverTime b  on a.ID_Employee = b.ID_Employee inner join TypeShift c on b.ID_TypeShift = c.ID_TypeShift\n" +
"									Where b.Date between ? and ?\n" +
"									Group by a.ID_Employee ) b On a.ID_Employee = b.ID_Employee\n" +
"							left join (select a.ID_Employee, SUM(c.Money) 'Sum Allowance' from Employee a inner join Employee_Allowance b on a.ID_Employee = b.ID_Employee inner join Allowance c on c.ID_Allowance = b.ID_Allowance\n" +
"									Where b.Date between ? and ?\n" +
"									Group by a.ID_Employee) c on a.ID_Employee = c.ID_Employee\n" +
"							left join (select a.ID_Employee, SUM(b.Money)'Sum Advance' from Employee a inner join SalaryAdvance b on a.ID_Employee = b.ID_Employee\n" +
"									Where b.Date between ? and ?\n" +
"									Group by a.ID_Employee) d on a.ID_Employee = d.ID_Employee\n"
            + "Where a.ID_Employee = ?";
    
    final String SELECT_By_Date_SQL = "Select a.ID_Employee, a.Name_Employee, a.Img, a.SalaryBasic, b.[Sum OT], c.[Sum Allowance], d.[Sum Advance] from Employee a	left join (select a.ID_Employee, SUM(b.HoursNumber*c.Money) 'Sum OT'from Employee a inner join OverTime b  on a.ID_Employee = b.ID_Employee inner join TypeShift c on b.ID_TypeShift = c.ID_TypeShift\n" +
"									Where b.Date between ? and ?\n" +
"									Group by a.ID_Employee ) b On a.ID_Employee = b.ID_Employee\n" +
"							left join (select a.ID_Employee, SUM(c.Money) 'Sum Allowance' from Employee a inner join Employee_Allowance b on a.ID_Employee = b.ID_Employee inner join Allowance c on c.ID_Allowance = b.ID_Allowance\n" +
"									Where b.Date between ? and ?\n" +
"									Group by a.ID_Employee) c on a.ID_Employee = c.ID_Employee\n" +
"							left join (select a.ID_Employee, SUM(b.Money)'Sum Advance' from Employee a inner join SalaryAdvance b on a.ID_Employee = b.ID_Employee\n" +
"									Where b.Date between ? and ?\n" +
"									Group by a.ID_Employee) d on a.ID_Employee = d.ID_Employee";
    
    @Override
    public void insert(TableSalary entity) {
        //JdbcHelper.executeUpdate(INSERT_SQL, entity.getDateOverTime(), entity.getHoursNumber(), entity.getIdEmployee(), entity.getIdTypeShift());
    }

    @Override
    public void update(TableSalary entity) {
        //JdbcHelper.executeUpdate(UPDATE_SQL, entity.getDateOverTime(), entity.getHoursNumber(), entity.getIdEmployee(), entity.getIdTypeShift(), entity.getIdOverTime());
    }

    @Override
    public void delete(String id) {
        //JdbcHelper.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<TableSalary> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public TableSalary selectById(String id) {
        List<TableSalary> list = selectBySql(SELECT_By_Id_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public  List<TableSalary> selectbyDate(String start, String end){
        List<TableSalary> list = new ArrayList<TableSalary>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(SELECT_By_Date_SQL, start, end, start, end, start, end);
            while (rs.next()) {
                TableSalary entity = new TableSalary();
                entity.setIdEmployee(String.valueOf(rs.getInt("ID_Employee")));
                entity.setNameEmp(rs.getString("Name_Employee"));
                entity.setImgEmp(rs.getString("Img"));
                entity.setSalaryBasic(rs.getInt("SalaryBasic"));
                entity.setSumOT(rs.getInt("Sum OT"));
                entity.setSumAllowance(rs.getInt("Sum Allowance"));
                entity.setSumAdvance(rs.getInt("Sum Advance"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public  List<TableSalary> selectbyDate_1(String start, String end,int  id){
        List<TableSalary> list = new ArrayList<TableSalary>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(SELECT_By_Id_SQL, start, end, start, end, start, end, id);
            while (rs.next()) {
                TableSalary entity = new TableSalary();
                entity.setIdEmployee(String.valueOf(rs.getInt("ID_Employee")));
                entity.setNameEmp(rs.getString("Name_Employee"));
                entity.setImgEmp(rs.getString("Img"));
                entity.setSalaryBasic(rs.getInt("SalaryBasic"));
                entity.setSumOT(rs.getInt("Sum OT"));
                entity.setSumAllowance(rs.getInt("Sum Allowance"));
                entity.setSumAdvance(rs.getInt("Sum Advance"));
                entity.setIdEmployee(String.valueOf(rs.getInt("ID_Employee")));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public TableSalary selectByIdEmp(String start, String end, String id) {
        List<TableSalary> list = new ArrayList<TableSalary>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(SELECT_By_Id_SQL, start, end, start, end, start, end, id);
            while (rs.next()) {
                TableSalary entity = new TableSalary();
                entity.setIdEmployee(String.valueOf(rs.getInt("ID_Employee")));
                entity.setNameEmp(rs.getString("Name_Employee"));
                entity.setImgEmp(rs.getString("Img"));
                entity.setSalaryBasic(rs.getInt("SalaryBasic"));
                entity.setSumOT(rs.getInt("Sum OT"));
                entity.setSumAllowance(rs.getInt("Sum Allowance"));
                entity.setSumAdvance(rs.getInt("Sum Advance"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    @Override
    public List<TableSalary> selectBySql(String sql, Object... args) {
        List<TableSalary> list = new ArrayList<TableSalary>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                TableSalary entity = new TableSalary();
                entity.setIdEmployee(String.valueOf(rs.getInt("ID_Enployee")));
                entity.setNameEmp(rs.getString("Name_Enployee"));
                entity.setImgEmp(rs.getString("Img"));
                entity.setSalaryBasic(rs.getInt("SalaryBasic"));
                entity.setSumOT(rs.getInt("Sum OT"));
                entity.setSumAllowance(rs.getInt("Sum Allowance"));
                entity.setSumAdvance(rs.getInt("Sum Advance"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public String dateSqlTo_ddMMYYYY(String date){
        String[] s = date.split("-");
        String x = "";
        x = s[2]+"-"+s[1]+"-"+s[0];
        return x;
    }
}
