/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weblogics;
import com.accessObjects.*;
import java.io.IOException;
/**
 *
 * @author Abhishek Abhinav
 */
public interface DBUserINTF {
    String registerUser(User user,String email) throws IOException;
    String authenticateUser(String username, String password) throws IOException;
    boolean updateUserInfo(User user) throws IOException;
}
