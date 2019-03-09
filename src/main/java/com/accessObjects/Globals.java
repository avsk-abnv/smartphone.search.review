/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accessObjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * 
 * @author Abhishek Abhinav
 */
public class Globals {
    public static CountDownLatch cLatch;
    public static ArrayList<String> vectorIndex = new ArrayList<String>(Arrays.asList("brandname","brandvalue","android","blackberry","windows",
        "symbian","other","external","minisd","microsd","internal","ram","4g","battery",
        "selfiecamera","maincamera"));
    public static String encode4Firebase(String str){
        str = str.replace(".", "_dot_").replace("$", "_dollar_").replace("[", "(").replace("]", ")").replace("#", "*").replace("/", " or ");
        return str;
    }
    public static String decodeNormal(String str){
        str = str.replace("_dot_",".").replace("_dollar_","$").replace("*","#").replace(" or ","/").replace("point", ".");
        return str;
    }
}
