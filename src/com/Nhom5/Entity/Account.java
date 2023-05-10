 
package com.Nhom5.Entity;
 
public class Account {
    private String userName;
    private String passWord;
    private String idEmployee;
    private String idDuty;
    private String nameDuty;
    private String name_Emp;
    private String role;
    public String getUserName() {
        return userName;
    }

    public String getName_Emp() {
        return name_Emp;
    }

    public void setName_Emp(String name_Emp) {
        this.name_Emp = name_Emp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getIdDuty() {
        return idDuty;
    }

    public void setIdDuty(String idDuty) {
        this.idDuty = idDuty;
    }

    public String getNameDuty() {
        return nameDuty;
    }

    public void setNameDuty(String nameDuty) {
        this.nameDuty = nameDuty;
    }

    public Account() {
    }

    public Account(String userName, String passWord, String idEmployee, String idDuty, String nameDuty) {
        this.userName = userName;
        this.passWord = passWord;
        this.idEmployee = idEmployee;
        this.idDuty = idDuty;
        this.nameDuty = nameDuty;
    }
    
    
}
