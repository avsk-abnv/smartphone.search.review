/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.weblogics.DBDevice;
import java.io.IOException;

/**
 *
 * @author Abhishek Abhinav
 */
public class Test {

    public static void main(String... args) throws IOException {
        DBDevice db = new DBDevice();
        String firstName="Abhishek";
        String lastName="Kumar";
        String email="abhishek%kumar@gmail%com";
        String password="password";
        /*try
        {
            String data=db.getData("Users/"+email).toString();
            System.out.println("Emailid already exists! Please try different email id.");
        }
        catch(Exception e)
        {
            boolean success = db.setData("Users/"+email+"/comments/device_id_demo","comment_id_demo");
            success = db.setData("Users/"+email+"/current_cluster","Cluster-1");
            success = db.setData("Users/"+email+"/Name",firstName+" "+lastName);
            success = db.setData("Users/"+email+"/Password",password);
            success = db.setData("Users/"+email+"/preference_value/device_id_demo","0");
            if(success)
                System.out.println("Sucessfully registered");
            else
                System.out.println("Something went wrong!");
        }*/
        
        
        /*try
        {
            String data=db.getData("Users/"+email).toString();
            if(db.getData("Users/"+email+"/Password").toString().equals(password))
            {
                System.out.println("Authentication Successful");
            }
            else
                System.out.println("Wrong password");
        }
        catch(Exception e)
        {
            System.out.println("Email id does not exist!");
        }*/
        
        
        //String data = db.getData("Users/avsk%abnv@gmail%com").toString();
        //System.out.println(data+"+");
        //boolean success = db.setData("Users/abhishek%kumar@gmail%com/Name","Abhishek Kumar");
        //System.out.println(success);
        //boolean success = db.remove("Users/abhishek%kumar@gmail%comg");
        //System.out.println(success);
        
        
    }
}
