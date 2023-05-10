/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Nhom5.DAO;

import java.util.List;

/**
 *
 * @author ACER
 */
public abstract class MainDAO <Enttity, Key> {
    public abstract void insert (Enttity entity);
    public abstract void update (Enttity entity);
    public abstract void delete (Key id);
    public abstract List<Enttity> selectAll();
    public abstract Enttity selectById(Key id);
    public abstract List<Enttity> selectBySql(String sql, Object...args);
}
