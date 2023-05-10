package com.Nhom5.DAO;

import com.Nhom5.Entity.OverTime;
import com.Nhom5.Helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OverTimeDAO extends MainDAO<OverTime, String> {

    final String INSERT_SQL = "insert into OverTime( Date, HoursNumber, ID_Employee, ID_TypeShift) Values (?,?,?,?)";
    final String UPDATE_SQL = "update OverTime set Date = ?, HoursNumber = ?, ID_Employee = ?, ID_TypeShift = ? Where ID_OverTime = ?";
    final String DELETE_SQL = "delete from OverTime Where Id_OverTime = ?";
    final String SELECT_ALL_SQL = "Select a.ID_OverTime, a.Date, a .HoursNumber, a.ID_Employee, b.Name_Employee,b.Img,c.ID_TypeShift, c.Name_TypeShift, c.Money From OverTime a inner join Employee b on a.ID_Employee = b.ID_Employee inner join TypeShift c on a.ID_TypeShift = c.ID_TypeShift";
    final String SELECT_By_Id_SQL = "Select a.ID_OverTime, a.Date, a .HoursNumber, a.ID_Employee, b.Name_Employee,b.Img,c.ID_TypeShift, c.Name_TypeShift, c.Money From OverTime a inner join Employee b on a.ID_Employee = b.ID_Employee inner join TypeShift c on a.ID_TypeShift = c.ID_TypeShift where a.ID_OverTime= ?";

    @Override
    public void insert(OverTime entity) {
        JdbcHelper.executeUpdate(INSERT_SQL, entity.getDateOverTime(), entity.getHoursNumber(), entity.getIdEmployee(), entity.getIdTypeShift());
    }

    @Override
    public void update(OverTime entity) {
        JdbcHelper.executeUpdate(UPDATE_SQL, entity.getDateOverTime(), entity.getHoursNumber(), entity.getIdEmployee(), entity.getIdTypeShift(), entity.getIdOverTime());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<OverTime> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public OverTime selectById(String id) {
        List<OverTime> list = selectBySql(SELECT_By_Id_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<OverTime> selectBySql(String sql, Object... args) {
        List<OverTime> list = new ArrayList<OverTime>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                OverTime entity = new OverTime();
                entity.setIdOverTime(String.valueOf(rs.getInt("ID_OverTime")));
                entity.setDateOverTime(rs.getString("Date"));
                entity.setHoursNumber(rs.getFloat("HoursNumber"));
                entity.setIdEmployee(rs.getString("ID_Employee"));
                entity.setNameEmployee(rs.getString("Name_Employee"));
                entity.setImgEmployee(rs.getString("Img"));
                entity.setIdTypeShift(String.valueOf(rs.getString("ID_TypeShift")));
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
