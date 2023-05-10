
package com.Nhom5.DAO;

import com.Nhom5.Entity.Employee;
import com.Nhom5.Helper.JdbcHelper;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class EmloyeeDAO extends MainDAO<Employee, String>{
    final String INSERT_SQL = "insert into Employee( Name_Employee, Gender, Birthday, PhoneNumber, Citizen_identity_card, AddressEmployee, Img, ID_Department, ID_Duty, ID_Degree, SalaryBasic) Values (?,?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "update Employee set Name_Employee = ?, Gender = ?, Birthday = ?, PhoneNumber = ?, Citizen_identity_card = ?, AddressEmployee = ?, Img = ?, ID_Department= ?, ID_Duty = ?, ID_Degree = ?, SalaryBasic = ? Where ID_Employee = ?";
    final String DELETE_SQL = "delete from Employee Where Id_Employee = ?";
    final String SELECT_ALL_SQL = "Select a.ID_Employee, a.Name_Employee, a.Gender, a.Birthday, a.PhoneNumber, a.Citizen_identity_card, a.AddressEmployee, a.Img, a.SalaryBasic, b.Name_Duty, c.Name_Department, d.Name_Degree, b.ID_Duty, c.ID_Department, d.ID_Degree From Employee a inner join Duty b on a.ID_Duty = b.ID_Duty inner join Department c on a.ID_Department = c.ID_Department inner join Degree d on a.ID_Degree = d.ID_Degree";
    final String SELECT_ALL_Raking = "Select top(3) a.ID_Employee, a.Name_Employee, a.Gender, a.Birthday, a.PhoneNumber, a.Citizen_identity_card, a.AddressEmployee, a.Img, a.SalaryBasic, b.Name_Duty, c.Name_Department, d.Name_Degree, b.ID_Duty, c.ID_Department, d.ID_Degree From Employee a inner join Duty b on a.ID_Duty = b.ID_Duty inner join Department c on a.ID_Department = c.ID_Department inner join Degree d on a.ID_Degree = d.ID_Degree ORDER BY a.SalaryBasic DESC";
    final String SELECT_By_Id_SQL = "Select a.ID_Employee, a.Name_Employee, a.Gender, a.Birthday, a.PhoneNumber, a.Citizen_identity_card, a.AddressEmployee, a.Img, a.SalaryBasic, b.Name_Duty, c.Name_Department, d.Name_Degree, b.ID_Duty, c.ID_Department, d.ID_Degree From Employee a inner join Duty b on a.ID_Duty = b.ID_Duty inner join Department c on a.ID_Department = c.ID_Department inner join Degree d on a.ID_Degree = d.ID_Degree Where a.ID_Employee = ?";
    final String SELECT_By_Department = "select a.ID_Department, a.Name_Department, b.Count from Department a left join (select a.ID_Department, count(a.ID_Employee) 'Count' from Employee a\n" +
"								Group by a.ID_Department) b on a.ID_Department  =b.ID_Department";
    @Override
    public void insert(Employee entity) {
        JdbcHelper.executeUpdate(INSERT_SQL, entity.getNameEmployee(), entity.isGender(), entity.getBirthday(), entity.getPhoneNumber(), entity.getCityzenIdentyCard(), entity.getAddress(), entity.getImg(), entity.getIdDepartment(), entity.getIdDuty(), entity.getIdDegree(), entity.getSalaryBasic());
    }

    @Override
    public void update(Employee entity) {
        JdbcHelper.executeUpdate(UPDATE_SQL, entity.getNameEmployee(), entity.isGender(), entity.getBirthday(), entity.getPhoneNumber(), entity.getCityzenIdentyCard(), entity.getAddress(), entity.getImg(), entity.getIdDepartment(), entity.getIdDuty(), entity.getIdDegree(), entity.getSalaryBasic(), entity.getIdEmployee());
    }

    @Override
    public void delete(String id) {
        JdbcHelper.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public List<Employee> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public Employee selectById(String id) {
        List<Employee> list = selectBySql(SELECT_By_Id_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public List<Employee> selectCountEmpByDepartment() {
        List<Employee> list = new ArrayList<Employee>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(SELECT_By_Department);
            while (rs.next()) {
                Employee entity = new Employee();
                entity.setNameDepartment(rs.getString("Name_Department"));
                entity.setCountByDepartment(String.valueOf(rs.getInt("Count")));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    public List<Employee> selectRankingSalary() {
        List<Employee> list = new ArrayList<Employee>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(SELECT_ALL_Raking);
            while (rs.next()) {
                Employee entity = new Employee();
                entity.setIdEmployee(rs.getString("ID_Employee"));
                entity.setNameEmployee(rs.getString("Name_Employee"));
                entity.setGender(rs.getBoolean("Gender"));
                entity.setBirthday(rs.getString("Birthday"));
                entity.setPhoneNumber(rs.getString("PhoneNumber"));
                entity.setCityzenIdentyCard(rs.getString("Citizen_identity_card"));
                entity.setAddress(rs.getString("AddressEmployee"));
                entity.setImg(rs.getString("Img"));
                entity.setNameDepartment(rs.getString("Name_Department"));
                entity.setNameDuty(rs.getString("Name_Duty"));
                entity.setNameDegree(rs.getString("Name_Degree"));
                entity.setSalaryBasic(rs.getString("SalaryBasic"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Employee> selectBySql(String sql, Object... args) {
        List<Employee> list = new ArrayList<Employee>();
        try {
            ResultSet rs = JdbcHelper.executeQuery(sql, args);
            while (rs.next()) {
                Employee entity = new Employee();
                entity.setIdEmployee(rs.getString("ID_Employee"));
                entity.setNameEmployee(rs.getString("Name_Employee"));
                entity.setGender(rs.getBoolean("Gender"));
                entity.setBirthday(rs.getString("Birthday"));
                entity.setPhoneNumber(rs.getString("PhoneNumber"));
                entity.setCityzenIdentyCard(rs.getString("Citizen_identity_card"));
                entity.setAddress(rs.getString("AddressEmployee"));
                entity.setImg(rs.getString("Img"));
                entity.setNameDepartment(rs.getString("Name_Department"));
                entity.setNameDuty(rs.getString("Name_Duty"));
                entity.setNameDegree(rs.getString("Name_Degree"));
                entity.setSalaryBasic(rs.getString("SalaryBasic"));
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
    // Thu Dec 18 00:00:00 ICT 2003
}
