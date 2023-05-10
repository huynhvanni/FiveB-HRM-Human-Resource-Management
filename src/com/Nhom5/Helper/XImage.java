/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Nhom5.Helper;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author ACER
 */
public class XImage {

//    public static Image getAppIcon() {
//        URL url = XImage.class.getResource("hinh/logoo.png");
//        return new ImageIcon(url).getImage();
//    }
    
    public static Image getAppIcon() {
        URL url;
        url = XImage.class.getResource("/com/Nhom5/Icons/app.png");
        return new ImageIcon(url).getImage();
    }

    public static boolean save(File src) {
        File dst = new File("img", src.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();
        }

        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
            return true;

        } catch (Exception e) {
            return false;
        }
    }
    
    public static ImageIcon read(String fileName){
        File path = new File("img",fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}
