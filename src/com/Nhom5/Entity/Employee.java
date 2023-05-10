 
package com.Nhom5.Entity;
 
import java.util.Date;

public class Employee {
    private String idEmployee;
    private String nameEmployee;
    private boolean gender;
    private String birthday;
    private String phoneNumber;
    private String cityzenIdentyCard;
    private String address;
    private String img;
    private int idDepartment;
    private int idDuty;
    private int idDegree;
    private String salaryBasic;
    
    private String nameDepartment;
    private String nameDuty;
    private String nameDegree;
    
    
    private String countByDepartment;

    public String getCountByDepartment() {
        return countByDepartment;
    }

    public void setCountByDepartment(String countByDepartment) {
        this.countByDepartment = countByDepartment;
    }
 

    public Employee() {
    }

    public Employee(String idEmployee, String nameEmployee, boolean gender, String birthday, String phoneNumber, String cityzenIdentyCard, String address, String img, int idDepartment, int idDuty, int idDegree, String salaryBasic) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.gender = gender;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.cityzenIdentyCard = cityzenIdentyCard;
        this.address = address;
        this.img = img;
        this.idDepartment = idDepartment;
        this.idDuty = idDuty;
        this.idDegree = idDegree;
        this.salaryBasic = salaryBasic;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public String getNameDuty() {
        return nameDuty;
    }

    public void setNameDuty(String nameDuty) {
        this.nameDuty = nameDuty;
    }

    public String getNameDegree() {
        return nameDegree;
    }

    public void setNameDegree(String nameDegree) {
        this.nameDegree = nameDegree;
    }

    
    
    
    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCityzenIdentyCard() {
        return cityzenIdentyCard;
    }

    public void setCityzenIdentyCard(String cityzenIdentyCard) {
        this.cityzenIdentyCard = cityzenIdentyCard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public int getIdDuty() {
        return idDuty;
    }

    public void setIdDuty(int idDuty) {
        this.idDuty = idDuty;
    }

    public int getIdDegree() {
        return idDegree;
    }

    public void setIdDegree(int idDegree) {
        this.idDegree = idDegree;
    }

    public String getSalaryBasic() {
        return salaryBasic;
    }

    public void setSalaryBasic(String salaryBasic) {
        this.salaryBasic = salaryBasic;
    }
    
    
}
