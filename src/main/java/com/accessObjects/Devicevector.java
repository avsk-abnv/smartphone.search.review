/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accessObjects;

/**
 * 
 * @author Abhishek Abhinav
 */
public class Devicevector {
    public String deviceModel;
    public double deviceVector[];

    public Devicevector(String deviceModel, double[] deviceVector) {
        this.deviceModel = deviceModel;
        this.deviceVector = deviceVector;
    }
    
    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public double[] getDeviceVector() {
        return deviceVector;
    }

    public void setDeviceVector(double[] deviceVector) {
        this.deviceVector = deviceVector;
    }
    
}
