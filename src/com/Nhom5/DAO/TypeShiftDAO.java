/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Nhom5.DAO;

import com.Nhom5.Entity.Employee;
import com.Nhom5.Entity.TypeShift;
import com.Nhom5.Helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TypeShiftDAO extends MainDAO<TypeShift, String>{

    final String INSERT_SQL = "insert into TypeShift( Name_TypeShift, Money) values (?,?)";
    final String UPDATE_SQL = "update TypeShift set Name_TypeShift = ?, Money = ? Where Id_TypeShift = ?";
    final String DELETE_SQL = "delete from TypeShift Where Id_TypeShift = ?";
    final String SELECT_ALL_SQL = "Select * From TypeShift";
    final String SELECT_By_Id_SQL = "Select * From TypeShift where ID_TypeShift = ?";
    final String SELECT_By_Name_SQL = "Select * From TypeShift where Name_TypeShift = ?";
    @Override
    public void insert(TypeShift entity) {
        JdbcHelper.executeUpdate(INSERT_SQL, entity.getName_TypeShift(), entity.getMonney());
    }

    @Override
    public void update(TypeShift entity) {
        JdbcHelper.executeUpdate(UPDATE_SQL, entity.getName_TypeShift(), entity.getMonney(), entity.getId_TypeShift());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<TypeShift> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public TypeShift selectById(String id) {
        List<TypeShift> list = selectBySql(SELECT_By_Id_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public TypeShift selectByName(String name) {
        List<TypeShift> list = selectBySql(SELECT_By_Name_SQL, name);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<TypeShift> selectBySql(String sql, Object... args) {
        List<TypeShift> list = new ArrayList<TypeShift>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                TypeShift entity = new TypeShift();
                entity.setId_TypeShift(rs.getString("ID_TypeShift"));
                entity.setName_TypeShift(rs.getString("Name_TypeShift"));
                entity.setMonney(rs.getInt("Money"));
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
