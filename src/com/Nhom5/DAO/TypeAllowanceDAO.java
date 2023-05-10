/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Nhom5.DAO;

import com.Nhom5.Entity.TypeAllowance;
import com.Nhom5.Entity.TypeShift;
import com.Nhom5.Helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TypeAllowanceDAO extends MainDAO<TypeAllowance, String>{
    final String INSERT_SQL = "insert into Allowance( Name_Allowance, Money) values (?,?)";
    final String UPDATE_SQL = "update Allowance set Name_Allowance = ?, Money = ? Where Id_Allowance = ?";
    final String DELETE_SQL = "delete from Allowance Where Id_Allowance = ?";
    final String SELECT_ALL_SQL = "Select * From Allowance";
    final String SELECT_By_Id_SQL = "Select * From Allowance where ID_Allowance = ?";
    final String SELECT_By_Name_SQL = "Select * From Allowance where Name_Allowance = ?";
    @Override
    public void insert(TypeAllowance entity) {
        JdbcHelper.executeUpdate(INSERT_SQL, entity.getName_TypeAllowance(), entity.getMonney());
    }

    @Override
    public void update(TypeAllowance entity) {
        JdbcHelper.executeUpdate(UPDATE_SQL, entity.getName_TypeAllowance(), entity.getMonney(), entity.getId_TypeAllowance());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<TypeAllowance> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public TypeAllowance selectById(String id) {
        List<TypeAllowance> list = selectBySql(SELECT_By_Id_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public TypeAllowance selectByName(String name) {
        List<TypeAllowance> list = selectBySql(SELECT_By_Name_SQL, name);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<TypeAllowance> selectBySql(String sql, Object... args) {
        List<TypeAllowance> list = new ArrayList<TypeAllowance>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                TypeAllowance entity = new TypeAllowance();
                entity.setId_TypeAllowance(rs.getString("ID_Allowance"));
                entity.setName_TypeAllowance(rs.getString("Name_Allowance"));
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
