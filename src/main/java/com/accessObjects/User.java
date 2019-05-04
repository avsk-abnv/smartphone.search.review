/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accessObjects;

import java.util.Map;

/**
 *
 * @author Abhishek Abhinav
 */
public class User {
    public String name;
    public String password;
    public String current_cluster;
    public Pref preference_values = new Pref();
    public Map<String, String> comments;
    public Map<String, String> like_dislike;
    public Map<String, String> cluster_index_mapping;
}
