 
package com.Nhom5.DAO;
 

import com.Nhom5.Entity.Department;
import com.Nhom5.Helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO extends MainDAO<Department, String>{
    final String INSERT_SQL = "insert into Department(Name_Department) Values(?)";
    final String UPDATE_SQL = "Update Department set Name_Department = ? where Id_Department = ?";
    final String DELETE_SQL = "delete from Department Where Id_Department = ?";
    final String SELECT_By_Id_SQL = "select * from Department where Id_Department = ?";
    final String SELECT_By_Name_SQL = "select * from Department where Name_Department=?";
    final String SELECT_ALL_SQL = "select * from Department";
//    final String SELECT_ALL_SQL_1 = "select * from NhanVien where Xoa = 0";
//    final String SELECT_ALL_SQL_2 = "select * from NhanVien where Xoa = 1";
//    final String HIDE_SQL = "Update NhanVien set xoa=1 where MaNV = ?";
//    final String RESTORE_SQL = "Update NhanVien set xoa=0 where MaNV = ?";

    @Override
    public void insert(Department entity) {
        JdbcHelper.executeUpdate(INSERT_SQL, entity.getNameDepartment());
    }

    @Override
    public void update(Department entity) {
        JdbcHelper.executeUpdate(UPDATE_SQL, entity.getNameDepartment(), entity.getIdDepartment());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<Department> selectAll() {
         return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Department selectById(String id) {
        List<Department> list = selectBySql(SELECT_By_Name_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
     public Department selectById_1(String id) {
        List<Department> list = selectBySql(SELECT_By_Id_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Department> selectBySql(String sql, Object... args) {
        List<Department> list = new ArrayList<Department>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                Department entity = new Department();
                entity.setIdDepartment(rs.getInt("ID_Department"));
                entity.setNameDepartment(rs.getString("Name_Department"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    
   
}
