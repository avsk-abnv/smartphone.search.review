/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import static com.accessObjects.Globals.encode4Firebase;
import static com.accessObjects.Globals.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Abhishek Abhinav
 */
public class Test {

    /*public static void main(String... args) throws IOException {
        for(String memKey : extMemory_mapping.keySet()){
            System.out.println(memKey+" :"+ (new Gson()).toJson(extMemory_mapping.get(memKey)));
        }
        //DBDevice db = new DBDevice();
        /*Device device = db.getByDeviceID("alcatel%alcatel Pop 2 (4)");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(device));*/
 /*ArrayList<String> deviceIDs = db.filterByOS("windows", 70);
        for(String devID: deviceIDs){
            System.out.println(devID);
        }*/
        //DBDevice dbdevice = new DBDevice();
        /*URL url = new URL("https://device-pics.firebaseapp.com/devicevector.json");
        JsonReader jsonReader = new JsonReader(new InputStreamReader(url.openStream()));
        Map<String, ArrayList<String>> vector = new HashMap<>();
        jsonReader.beginObject();
        ArrayList<String> deviceIDs = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        int count = 0;
        while (jsonReader.hasNext()) {
            count++;
            String model = jsonReader.nextName();
            ArrayList<String> devarr = gson.fromJson(jsonReader.nextString(), ArrayList.class);
            String brand = devarr.get(0);
            
            System.out.println(model);
            System.out.println("Count: "+count);
            if (devarr.get(vectorIndex.indexOf("android")).equals("100")) {
                String deviceID = brand + "%" + encode4Firebase(model);
                deviceIDs.add(deviceID);
                System.out.println("added");
                if (deviceIDs.size() >= 1000) {
                    break;
                }
            }

            if (deviceIDs.size() >= 1000) {
                break;
            }
            //jsonReader.endObject();
        }
        if(!jsonReader.hasNext())
            jsonReader.endObject();

        jsonReader.close();
        /*for (String deviceID : deviceIDs) {
            System.out.println(deviceID);
        }*/
        /*BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String line ="";
        while(line!=null){
            line = reader.readLine();
            System.out.println(line);
        }*/
        //vector = gson.fromJson(jsonReader, vector.getClass());
        /*jsonReader.beginObject();
        while(jsonReader.hasNext()){
            String name = jsonReader.nextName();
            System.out.println(name);
            ArrayList<String> arr = gson.fromJson(jsonReader.nextString(),ArrayList.class);
            System.out.println(arr.get(0).getClass());
            
        }
        jsonReader.endObject();*/
 /*for(String model: vector.keySet()){
            System.out.println(model);
        }*/
        //jsonReader.close();
        /*ArrayList<String> deviceIDs = dbdevice.filterByOS("android", 16);
        File file = new File("L:\\4th Year Project\\ongoing\\backups\\all-devices.json");
        Map<String, Map<String, Map<String, Map<String, String>>>> jsonData = new HashMap<>();
        BufferedReader jsonReader = new BufferedReader(new FileReader(file));
        jsonData = (new Gson()).fromJson(jsonReader, jsonData.getClass());
        ArrayList<ArrayList<Device>> devices = new ArrayList<>();
        ArrayList<Device> devRow = new ArrayList<>();
        for (String deviceID : deviceIDs) {
            String brand = deviceID.split("%")[0];
            String model = deviceID.split("%")[1];
            Device device = new Device();
            device.info = jsonData.get(brand).get(model);
            device.brand = brand;
            device.model = model;
            devRow.add(device);
            if(devRow.size()==4){
                devices.add(devRow);
                devRow = new ArrayList<>();
            }
        }
        
        for (ArrayList<Device> deviceRow : devices) {
            for(Device dev: deviceRow){
                System.out.println(dev.model);
            }
            System.out.println("-----------");
        }*/
    //}
}
