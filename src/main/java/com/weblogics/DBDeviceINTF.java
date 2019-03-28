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
    boolean setData(String ref, String newdata);
    boolean remove(String ref);
    Object getData(String ref);
}