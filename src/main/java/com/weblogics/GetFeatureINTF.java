/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weblogics;

/**
 *
 * @author Abhishek Abhinav
 */
public interface GetFeatureINTF {
    public String getProperty(String deviceID,String prop);
    public String getCardType(String deviceID);
    public String has4G(String deviceID);
}
