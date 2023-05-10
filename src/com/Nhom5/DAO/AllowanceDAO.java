
package com.Nhom5.DAO;

import com.Nhom5.Entity.Allowance;
import com.Nhom5.Entity.OverTime;
import com.Nhom5.Helper.JdbcHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class AllowanceDAO extends MainDAO<Allowance, String>{
    final String INSERT_SQL = "insert into Employee_Allowance( ID_Employee, ID_Allowance, Date, Money) Values (?,?,?,?)";
    final String UPDATE_SQL = "update Employee_Allowance set ID_Employee = ?, ID_Allowance = ?, Date = ?, Money = ? Where ID_Employee_Allowance = ?";
    final String DELETE_SQL = "delete from Employee_Allowance Where ID_Employee_Allowance = ?";
    final String SELECT_ALL_SQL = "Select a.ID_Employee_Allowance, a.Date, a.ID_Employee, b.Name_Employee, b.Img, c.ID_Allowance,  c.Name_Allowance, c.Money from Employee_Allowance a inner join Employee b on a.ID_Employee = b.ID_Employee inner join Allowance c on a.ID_Allowance = c.ID_Allowance";
    final String SELECT_By_Id_SQL = "Select a.ID_Employee_Allowance, a.Date, a.ID_Employee, b.Name_Employee, b.Img, c.ID_Allowance,  c.Name_Allowance, c.Money from Employee_Allowance a inner join Employee b on a.ID_Employee = b.ID_Employee inner join Allowance c on a.ID_Allowance = c.ID_Allowance where a.ID_Employee_Allowance= ?";

    @Override
    public void insert(Allowance entity) {
        JdbcHelper.executeUpdate(INSERT_SQL, entity.getIdEmployee(), entity.getIdTA(), entity.getDateAllowance(), entity.getMoney());
    }

    @Override
    public void update(Allowance entity) {
        JdbcHelper.executeUpdate(UPDATE_SQL, entity.getIdEmployee(), entity.getIdAllowance(), entity.getDateAllowance(), entity.getMoney(), entity.getIdAllowance());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<Allowance> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Allowance selectById(String id) {
        List<Allowance> list = selectBySql(SELECT_By_Id_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Allowance> selectBySql(String sql, Object... args) {
        List<Allowance> list = new ArrayList<Allowance>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                Allowance entity = new Allowance();
                entity.setIdAllowance(String.valueOf(rs.getInt("ID_Employee_Allowance")));
                entity.setDateAllowance(rs.getString("Date"));
                entity.setIdTA(String.valueOf(rs.getInt("ID_Allowance")));
                entity.setNameTA(rs.getString("Name_Allowance"));
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
