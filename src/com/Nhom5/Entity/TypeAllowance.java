/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Nhom5.Entity;


public class TypeAllowance {
    private String id_TypeAllowance;
    private String name_TypeAllowance;
    private int monney;

    public TypeAllowance(String id_TypeAllowance, String name_TypeAllowance, int monney) {
        this.id_TypeAllowance = id_TypeAllowance;
        this.name_TypeAllowance = name_TypeAllowance;
        this.monney = monney;
    }

    public TypeAllowance() {
    }

    public String getId_TypeAllowance() {
        return id_TypeAllowance;
    }

    public void setId_TypeAllowance(String id_TypeAllowance) {
        this.id_TypeAllowance = id_TypeAllowance;
    }

    public String getName_TypeAllowance() {
        return name_TypeAllowance;
    }

    public void setName_TypeAllowance(String name_TypeAllowance) {
        this.name_TypeAllowance = name_TypeAllowance;
    }

    public int getMonney() {
        return monney;
    }

    public void setMonney(int monney) {
        this.monney = monney;
    }
    
}
