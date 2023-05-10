package com.Nhom5.DAO;

import com.Nhom5.Entity.Account;
import com.Nhom5.Helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends MainDAO<Account, String> {

    final String INSERT_SQL = "insert into Account(UserName, Password, ID_Employee) values(?,?,?))";
    final String UPDATE_SQL = "Update Account set Password = ? where UserName = ?";
    final String DELETE_SQL = "delete from Account Where UserName = ?";
    final String SELECT_By_Id_SQL = "select a.UserName, a.Password, b.Name_Employee, b.Birthday, b.Img, c.ID_Duty, a.ID_Employee from Account a inner join Employee b on a.ID_Employee = b.ID_Employee inner  join Duty c on b.ID_Duty= c.ID_Duty where UserName= ?";
//    final String SELECT_ALL_SQL = "select * from NhanVien";
//    final String SELECT_ALL_SQL_1 = "select * from NhanVien where Xoa = 0";
//    final String SELECT_ALL_SQL_2 = "select * from NhanVien where Xoa = 1";
//    final String HIDE_SQL = "Update NhanVien set xoa=1 where MaNV = ?";
//    final String RESTORE_SQL = "Update NhanVien set xoa=0 where MaNV = ?";

    @Override
    public void insert(Account entity) {
        JdbcHelper.executeUpdate(INSERT_SQL, entity.getUserName(), entity.getPassWord(), entity.getIdEmployee());
    }

    @Override
    public void update(Account entity) {
        JdbcHelper.executeUpdate(UPDATE_SQL, entity.getPassWord(), entity.getUserName());
    }

    @Override
    public void delete(String userName) {
        JdbcHelper.executeUpdate(INSERT_SQL, userName);
    }

    @Override
    public List<Account> selectAll() {
//        List<Account> list = selectBySql(SELECT_By_Id_SQL, id);
//        if (list.isEmpty()) {
//            return null;
//        }
//        return list.get(0);
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Account selectById(String id) {
        List<Account> list = selectBySql(SELECT_By_Id_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Account> selectBySql(String sql, Object... args) {
        List<Account> list = new ArrayList<Account>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                Account entity = new Account();
                entity.setUserName(rs.getString("UserName"));
                entity.setPassWord(rs.getString("Password"));
                entity.setIdDuty(String.valueOf(rs.getInt("ID_Duty")));
                entity.setIdEmployee(String.valueOf(rs.getInt("ID_Employee")));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
