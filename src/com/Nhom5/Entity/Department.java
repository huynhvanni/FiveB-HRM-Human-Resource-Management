 
package com.Nhom5.Entity;
 
public class Department {
    private int idDepartment;
    private String nameDepartment;
    private String img;

    public Department() {
    }

    public Department(int idDepartment, String nameDepartment, String img) {
        this.idDepartment = idDepartment;
        this.nameDepartment = nameDepartment;
        this.img = img;
    }
    
    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    
}
