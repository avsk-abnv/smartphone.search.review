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
    public ArrayList<String> filterByModelSubstring(String searchKey, int maxcount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> filterByBrandName(String brand, int maxcount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> filterByOS(String OS, int maxcount) {
        JsonReader jsonReader = null;
        ArrayList<String> deviceIDs = new ArrayList<>();
        boolean exception = false;
        try {
            URL url = new URL("https://device-pics.firebaseapp.com/devicevector.json");
            jsonReader = new JsonReader(new InputStreamReader(url.openStream()));
            Map<String, ArrayList<String>> vector = new HashMap<>();
            jsonReader.beginObject();
            Gson gson = new GsonBuilder().create();
            while (jsonReader.hasNext()) {
                String model = jsonReader.nextName();
                ArrayList<String> devarr = gson.fromJson(jsonReader.nextString(), ArrayList.class);
                String brand = devarr.get(0);
                if (devarr.get(VECTOR_INDEX.indexOf("android")).equals("100")) {
                    String deviceID = brand + "%" + encode4Firebase(model);
                    deviceIDs.add(deviceID);
                    if (deviceIDs.size() >= 16) {
                        break;
                    }
                }

                if (deviceIDs.size() >= 16) {
                    break;
                }
                //jsonReader.endObject();
            }
            if (!jsonReader.hasNext()) {
                jsonReader.endObject();
            }

            jsonReader.close();
        } catch (MalformedURLException ex) {
            exception = true;
        } catch (IOException ex) {
            exception = true;
        } finally {
            try {
                if (!exception) {
                    jsonReader.close();
                }
            } catch (IOException ex) {

            }
        }
        if (exception) {
            deviceIDs.removeAll(deviceIDs);
            deviceIDs.add("error");
        }
        return deviceIDs;
    }

    @Override
    public ArrayList<String> filterByExtMemoryRange(double fromGB, double toGB, int maxcount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> filterByIntMemoryRange(double fromGB, double toGB, int maxcount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> filterByRAMRange(double fromGB, double toGB, int maxcount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> filterByBatteryStrength(double from_mAh, double to_mAh, int maxcount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> filterByCameraMegapixels(double fromMegaPX, double toMegaPX, int maxcount) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
