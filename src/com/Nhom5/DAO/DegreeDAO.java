 
package com.Nhom5.DAO;
 
import com.Nhom5.Entity.Degree;
import com.Nhom5.Entity.Department;
import com.Nhom5.Entity.Duty;
import com.Nhom5.Helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DegreeDAO extends MainDAO<Degree, String>{
    final String INSERT_SQL = "insert into Degree(Name_Degree) Values(?)";
    final String UPDATE_SQL = "Update Degree set Name_Degree = ? where Id_Degree = ?";
    final String DELETE_SQL = "delete from Degree Where Id_Degree = ?";
    final String SELECT_By_Id_SQL = "select * from Degree where Id_Degree = ?";
    final String SELECT_ALL_SQL = "select * from Degree";
    final String SELECT_By_Name_SQL = "select * from Degree where Name_Degree=?";
//    final String SELECT_ALL_SQL_1 = "select * from NhanVien where Xoa = 0";
//    final String SELECT_ALL_SQL_2 = "select * from NhanVien where Xoa = 1";
//    final String HIDE_SQL = "Update NhanVien set xoa=1 where MaNV = ?";
//    final String RESTORE_SQL = "Update NhanVien set xoa=0 where MaNV = ?";
    
    @Override
    public void insert(Degree entity) {
        JdbcHelper.executeUpdate(INSERT_SQL, entity.getNameDegree());
    }

    @Override
    public void update(Degree entity) {
        JdbcHelper.executeUpdate(UPDATE_SQL, entity.getNameDegree(), entity.getIdDegree());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<Degree> selectAll() {
       return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Degree selectById(String id) {
        List<Degree> list = selectBySql(SELECT_By_Name_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    public Degree selectById_1(String id) {
        List<Degree> list = selectBySql(SELECT_By_Id_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Degree> selectBySql(String sql, Object... args) {
        List<Degree> list = new ArrayList<Degree>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                Degree entity = new Degree();
                entity.setIdDegree(rs.getInt("ID_Degree"));
                entity.setNameDegree(rs.getString("Name_Degree"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

     
    
}
