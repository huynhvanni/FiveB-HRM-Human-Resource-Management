
package com.Nhom5.Entity;

 
public class Degree {
    private int idDegree;
    private String nameDegree;
    private String img;

    public Degree() {
    }

    public Degree(int idDegree, String nameDegree, String img) {
        this.idDegree = idDegree;
        this.nameDegree = nameDegree;
        this.img = img;
    }

    
    
    public int getIdDegree() {
        return idDegree;
    }

    public void setIdDegree(int idDegree) {
        this.idDegree = idDegree;
    }

    public String getNameDegree() {
        return nameDegree;
    }

    public void setNameDegree(String nameDegree) {
        this.nameDegree = nameDegree;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    
}
