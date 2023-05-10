/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Nhom5.DAO;

import com.Nhom5.Entity.OverTime;
import com.Nhom5.Entity.SalaryAdvance;
import com.Nhom5.Helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hvn45
 */
public class SalaryAdvanceDAO extends MainDAO<SalaryAdvance, String>{
    final String INSERT_SQL = "insert into SalaryAdvance( Date, Money, ID_Employee, Contents) Values (?,?,?,?)";
    final String UPDATE_SQL = "update SalaryAdvance set Date = ?, Money = ?, ID_Employee = ?, Contents = ? Where ID_SalaryAdvance = ?";
    final String DELETE_SQL = "delete from SalaryAdvance Where ID_SalaryAdvance = ?";
    final String SELECT_ALL_SQL = "select a.Contents, a.Date, a.ID_Employee, a.ID_SalaryAdvance, a.Money, b.Name_Employee, b.Img from SalaryAdvance a inner join Employee b on a.ID_Employee = b.ID_Employee";
    final String SELECT_By_Id_SQL = "select a.Contents, a.Date, a.ID_Employee, a.ID_SalaryAdvance, a.Money, b.Name_Employee, b.Img from SalaryAdvance a inner join Employee b on a.ID_Employee = b.ID_Employee where a.ID_SalaryAdvance= ?";

    @Override
    public void insert(SalaryAdvance entity) {
        JdbcHelper.executeUpdate(INSERT_SQL, entity.getDateSalaryAdvance(), entity.getMoney(), entity.getIdEmployee(), entity.getContent());
    }

    @Override
    public void update(SalaryAdvance entity) {
        JdbcHelper.executeUpdate(UPDATE_SQL, entity.getDateSalaryAdvance(), entity.getMoney(), entity.getIdEmployee(), entity.getContent(), entity.getIdSalaryAdvance());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<SalaryAdvance> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public SalaryAdvance selectById(String id) {
        List<SalaryAdvance> list = selectBySql(SELECT_By_Id_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<SalaryAdvance> selectBySql(String sql, Object... args) {
        List<SalaryAdvance> list = new ArrayList<SalaryAdvance>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                SalaryAdvance entity = new SalaryAdvance();
                entity.setIdSalaryAdvance(String.valueOf(rs.getInt("ID_SalaryAdvance")));
                entity.setContent(rs.getString("Contents"));
                entity.setDateSalaryAdvance(String.valueOf(rs.getDate("Date")));
                entity.setIdEmployee(rs.getString("ID_Employee"));
                entity.setNameEmployee(rs.getString("Name_Employee"));
                entity.setImgEmployee(rs.getString("Img"));
                entity.setMoney(rs.getInt("Money"));
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
