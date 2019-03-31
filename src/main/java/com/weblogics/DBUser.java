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
    public String registerUser(User user) throws IOException {
        DBDevice db = new DBDevice();
        String firstName=user.getFirstName();
        String lastName=user.getLastName();
        String email=user.getEmail();
        String password=user.getPassword();
        try
        {
            String data=db.getData("Users/"+email).toString();
            return "Email id already exists! Please try different email id.";
        }
        catch(Exception e)
        {
            boolean success = db.setData("Users/"+email+"/comments/device_id_demo","comment_id_demo");
            success = db.setData("Users/"+email+"/current_cluster","Cluster-1");
            success = db.setData("Users/"+email+"/Name",firstName+" "+lastName);
            success = db.setData("Users/"+email+"/Password",password);
            success = db.setData("Users/"+email+"/preference_value/device_id_demo","0");
            if(success)
                return "Sucessfully registered";
            else
                return "Something went wrong!";
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean authenticateUser(String username, String password) throws IOException{
        DBDevice db = new DBDevice();
        try
        {
            String data=db.getData("Users/"+username).toString();
            if(db.getData("Users/"+username+"/Password").toString().equals(password))
            {
                System.out.println("Authentication Successful");
                return true;
            }
            else
            {
                System.out.println("Wrong password");
                return false;
            }
        }
        catch(Exception e)
        {
            System.out.println("Email id does not exist!");
            return false;
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateUserInfo(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
