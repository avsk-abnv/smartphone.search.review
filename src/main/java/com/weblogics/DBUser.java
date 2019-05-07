/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weblogics;

import com.accessObjects.User;
import java.io.IOException;

/**
 *
 * @author Abhishek Abhinav
 */
public class DBUser implements DBUserINTF {

    @Override
    public String registerUser(User user, String email) throws IOException {
        DBDevice db = new DBDevice();

        String data = db.getData("Users/" + email.replace(".", "%")).toString();
        if (data.equals("error")) {
            email = email.replace(".", "%");
            boolean success = db.setData("Users/" + email.replace(".", "%"), user);
            if (success) {
                return "Sucessfully registered";
            } else {
                return "Something went wrong!";
            }
        } else {
            return "Email id already exists!";
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String authenticateUser(String username, String password) throws IOException {
        DBDevice db = new DBDevice();
        try {
            username = username.replace(".", "%");
            String pwrd = db.getData("Users/" + username + "/password").toString();
            if (pwrd.equals(password)) {
                return "Authentication Successful";
            } else if (pwrd.equals("error")){
                return "Email id does not exist!";
            } else {
                return "Wrong Password!";
            }
        } catch (Exception e) {
            return "Something went wrong!";
            //return e.toString();
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateUserInfo(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
