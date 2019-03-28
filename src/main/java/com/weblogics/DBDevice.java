/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weblogics;

import com.accessObjects.Device;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import static com.accessObjects.Globals.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference.CompletionListener;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abhishek Abhinav
 */
public class DBDevice implements DBDeviceINTF {

    static Object deviceInfo = new HashMap<>();
    Object data;
    boolean success;
    public DBDevice() throws IOException {
        try {
            FirebaseApp.getInstance();
        } catch (IllegalStateException e) {
            //Firebase not initialized automatically, do it manually
            FirebaseConnect.getConnection();
        }
    }

    @Override
    public Device getByDeviceID(String deviceID) {
        Device device = new Device();
        String splitted[] = deviceID.split("%");
        String brand = splitted[0];
        String model = splitted[1];
        Map<String, Map<String, String>> devInfo = new HashMap<>();
        DatabaseReference dref = FirebaseDatabase.getInstance().getReference();
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                //System.out.println(ds);
                deviceInfo = ds.getValue(Object.class);
                cLatch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError de) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        };
        cLatch = new CountDownLatch(1);
        dref.child("devices").child(brand).child(model).addValueEventListener(listener);
        try {
            cLatch.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(DBDevice.class.getName()).log(Level.SEVERE, null, ex);
        }
        dref.child("devices").child(brand).child(model).removeEventListener(listener);
        Gson gson = new Gson();
        String infoJSON = gson.toJson(deviceInfo);
        devInfo = gson.fromJson(infoJSON, devInfo.getClass());
        device.brand = brand;
        device.model = model;
        device.info = devInfo;
        return device;
    }

    @Override
    public boolean setData(String ref, String newdata) {
        DatabaseReference dref = FirebaseDatabase.getInstance().getReference();
        success = true;
        
        cLatch = new CountDownLatch(1);
        String refs[] = ref.split("/");
        for(int i=0; i<refs.length; i++){
            dref = dref.child(refs[i]);
        }
        dref.setValue(newdata, new CompletionListener(){
            @Override
            public void onComplete(DatabaseError de, DatabaseReference dr) {
                cLatch.countDown();
                success = true;
            }
        });
        try {
            cLatch.await();
        } catch (InterruptedException ex) {
            return false;
        }        
        return success;
    }

    @Override
    public Object getData(String ref) {
        DatabaseReference dref = FirebaseDatabase.getInstance().getReference();
        
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                //System.out.println(ds);
                data = ds.getValue(Object.class);
                cLatch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError de) {
                data = "error"; //To change body of generated methods, choose Tools | Templates.
                cLatch.countDown();
            }

        };
        cLatch = new CountDownLatch(1);
        String refs[] = ref.split("/");
        for(int i=0; i<refs.length; i++){
            dref = dref.child(refs[i]);
        }
        dref.addValueEventListener(listener);
        try {
            cLatch.await();
        } catch (InterruptedException ex) {
            data = "error";
        }
        dref.removeEventListener(listener);
        return data;
    }

    @Override
    public boolean remove(String ref) {
        DatabaseReference dref = FirebaseDatabase.getInstance().getReference();
        String refs[] = ref.split("/");
        for(int i=0; i<refs.length; i++){
            dref = dref.child(refs[i]);
        }
        cLatch = new CountDownLatch(1);
        dref.removeValue(new CompletionListener(){
            @Override
            public void onComplete(DatabaseError de, DatabaseReference dr) {
                cLatch.countDown();
            }
            
        });
        try {
            cLatch.await();
        } catch (InterruptedException ex) {
            return false;
        }
        return true;
    }

}
