/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.accessObjects.Device;
import static com.accessObjects.Globals.vectorIndex;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weblogics.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Abhishek Abhinav
 */
public class Test {

    public static void main(String... args) throws IOException {
        DBDevice db = new DBDevice();
        /*Device device = db.getByDeviceID("alcatel%alcatel Pop 2 (4)");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(device));*/
        ArrayList<String> deviceIDs = db.filterByOS("windows", 70);
        for(String devID: deviceIDs){
            System.out.println(devID);
        }
    }
}
