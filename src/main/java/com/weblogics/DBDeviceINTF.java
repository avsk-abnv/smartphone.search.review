/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weblogics;
import com.accessObjects.*;
import java.util.ArrayList;
/**
 *
 * @author Abhishek Abhinav
 */
public interface DBDeviceINTF {
    Device getByDeviceID(String deviceID);
    ArrayList<String> filterByModelSubstring(String searchKey,int maxcount);
    ArrayList<String> filterByBrandName(String brand,int maxcount);
    ArrayList<String> filterByOS(String OS,int maxcount);
    ArrayList<String> filterByExtMemoryRange(double fromGB,double toGB,int maxcount);
    ArrayList<String> filterByIntMemoryRange(double fromGB,double toGB,int maxcount);
    ArrayList<String> filterByRAMRange(double fromGB,double toGB,int maxcount);
    ArrayList<String> filterByBatteryStrength(double from_mAh,double to_mAh,int maxcount);
    ArrayList<String> filterByCameraMegapixels(double fromMegaPX,double toMegaPX,int maxcount);
}
