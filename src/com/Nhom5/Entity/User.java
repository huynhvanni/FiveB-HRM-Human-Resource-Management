 
package com.Nhom5.Entity;

 
public class User {
    private String idDuty;
    private String name;
    private String duty;
    private String img;
    private int role;
    private int id_Emp;

    public User(String idDuty, String name, String duty, String img, int role, int id_Emp) {
        this.idDuty = idDuty;
        this.name = name;
        this.duty = duty;
        this.img = img;
        this.role = role;
        this.id_Emp = id_Emp;
    }

    public int getId_Emp() {
        return id_Emp;
    }

    public void setId_Emp(int id_Emp) {
        this.id_Emp = id_Emp;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public User(String idDuty, String name, String duty, String img, int role) {
        this.idDuty = idDuty;
        this.name = name;
        this.duty = duty;
        this.img = img;
        this.role = role;
    }
    

    public User() {
    }

    public User(String idDuty, String name, String duty, String img) {
        this.idDuty = idDuty;
        this.name = name;
        this.duty = duty;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIdDuty() {
        return idDuty;
    }

    public void setIdDuty(String idDepartment) {
        this.idDuty = idDepartment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }
    
    
    
}
