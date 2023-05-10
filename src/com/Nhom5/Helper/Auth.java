
package com.Nhom5.Helper;

import com.Nhom5.Entity.Account;

public class Auth {
    public static Account user = null;
    
    public static void clearUser(){
        Auth.user = null;
    }
    
    public static boolean  islogin(){
        return Auth.user !=null;
    }
    
    public static String isRole(){
        return user.getIdDuty();
    }
}
