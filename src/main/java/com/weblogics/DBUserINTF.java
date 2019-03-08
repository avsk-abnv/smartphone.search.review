/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weblogics;
import com.accessObjects.*;
/**
 *
 * @author Abhishek Abhinav
 */
public interface DBUserINTF {
    String registerUser(User user);
    boolean authenticateUser(String username, String password);
    boolean updateUserInfo(User user);
}
