 
package com.Nhom5.DAO;
 
import com.Nhom5.Entity.Duty;
import com.Nhom5.Helper.JdbcHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;


public class DutyDAO extends MainDAO<Duty, String>{

    final String INSERT_SQL = "insert into Duty(Name_Duty) Values(?)";
    final String UPDATE_SQL = "Update Duty set Name_Duty = ? where Id_Duty = ?";
    final String DELETE_SQL = "delete from Duty Where Id_Duty = ?";
    final String SELECT_By_Id_SQL = "select * from Duty where Id_Duty = ?";
    final String SELECT_ALL_SQL = "select * from Duty";
    final String SELECT_By_Name_SQL = "select * from Duty where Name_Duty=?";
//    final String SELECT_ALL_SQL_1 = "select * from NhanVien where Xoa = 0";
//    final String SELECT_ALL_SQL_2 = "select * from NhanVien where Xoa = 1";
//    final String HIDE_SQL = "Update NhanVien set xoa=1 where MaNV = ?";
//    final String RESTORE_SQL = "Update NhanVien set xoa=0 where MaNV = ?";
    
    @Override
    public void insert(Duty entity) {
        JdbcHelper.executeUpdate(INSERT_SQL, entity.getNameDuty());
    }

    @Override
    public void update(Duty entity) {
        JdbcHelper.executeUpdate(UPDATE_SQL, entity.getNameDuty(), entity.getIdDuty());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<Duty> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Duty selectById(String id) {
        List<Duty> list = selectBySql(SELECT_By_Name_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public Duty selectById_1(String id) {
        List<Duty> list = selectBySql(SELECT_By_Id_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Duty> selectBySql(String sql, Object... args) {
        List<Duty> list = new ArrayList<Duty>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                Duty entity = new Duty();
                entity.setIdDuty(rs.getInt("ID_Duty"));
                entity.setNameDuty(rs.getString("Name_Duty"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
}
