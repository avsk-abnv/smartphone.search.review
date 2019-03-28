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
        /*String data = db.getData("Users/avsk%abnv@gmail%com/Password").toString();
        System.out.println(data);*/
        boolean success = db.setData("Users/avsk%abnv@gmail%com/Name","Abhishek Abhinav");
        System.out.println(success);
        /*boolean success = db.remove("Users/avsk%abnv@gmail%com/Name");
        System.out.println(success);*/
    }
}
