/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accessObjects;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Abhishek Abhinav
 */
public class Device {
    public Map<String, Map<String, String>> info;
    public String brand;
    public String model;
    
    public Device() {
    }

    public Device(Map<String, Map<String, String>> info, String brand, String model) {
        this.info = info;
        this.brand = brand;
        this.model = model;
    }
    
}
