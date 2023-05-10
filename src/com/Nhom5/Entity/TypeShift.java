 
package com.Nhom5.Entity;
 
public class TypeShift {
    private String id_TypeShift;
    private String name_TypeShift;
    private int monney;

    public TypeShift() {
    }

    public TypeShift(String id_TypeShift, String name_TypeShift, int monney) {
        this.id_TypeShift = id_TypeShift;
        this.name_TypeShift = name_TypeShift;
        this.monney = monney;
    }

    public String getId_TypeShift() {
        return id_TypeShift;
    }

    public void setId_TypeShift(String id_TypeShift) {
        this.id_TypeShift = id_TypeShift;
    }

    public String getName_TypeShift() {
        return name_TypeShift;
    }

    public void setName_TypeShift(String name_TypeShift) {
        this.name_TypeShift = name_TypeShift;
    }

    public int getMonney() {
        return monney;
    }

    public void setMonney(int monney) {
        this.monney = monney;
    }
    
    
}
