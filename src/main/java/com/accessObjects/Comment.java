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
public class Comment {
    String userID;
    String deviceID; //deviceID = brand+"%"+modelname
    String comment;
    int sentimentValue;

    public Comment(String userID, String deviceID, String comment, int sentimentValue) {
        this.userID = userID;
        this.deviceID = deviceID;
        this.comment = comment;
        this.sentimentValue = sentimentValue;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getSentimentValue() {
        return sentimentValue;
    }

    public void setSentimentValue(int sentimentValue) {
        this.sentimentValue = sentimentValue;
    }
    
    
    
}
