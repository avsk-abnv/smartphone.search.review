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
public class DBUser implements DBUserINTF{
    @Override
    public String registerUser(User user,String email) throws IOException {
        DBDevice db = new DBDevice();
        
        try
        {
            String data=db.getData("Users/"+email.replace(".","%")).toString();
            return "Email id already exists!";
        }
        catch(Exception e)
        {   email = email.replace(".","%");
            boolean success = db.setData("Users/"+email.replace(".","%"), user);
            if(success)
                return "Sucessfully registered";
            else
                return "Something went wrong!";
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String authenticateUser(String username, String password) throws IOException{
        DBDevice db = new DBDevice();
        try
        {   username = username.replace(".", "%");
            if(db.getData("Users/"+username+"/password").toString().equals(password))
            {
                return "Authentication Successful";
            }
            else
            {
                return "Wrong password";
            }
        }
        catch(Exception e)
        {
            return "Email id does not exist!";
            //return e.toString();
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateUserInfo(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
