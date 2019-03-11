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
    public static ArrayList<String> brands = new ArrayList<String>(Arrays.asList("acer","alcatel","allview","amazon","apple","archos","asus",
            "at&t","benq-siemens","benq","blackberry","blackview","blu","bq","casio","cat","celkon","coolpad","dell","energizer","eten",
            "fujitsu siemens","garmin-asus","gigabyte","gionee","google","haier","hp","htc","huawei","i-mate","i-mobile","icemobile","inq",
            "intex","jolla","karbonn","kyocera","lava","leeco","lenovo","lg","maxwest","meizu","micromax","microsoft","mitac","motorola",
            "mwg","nec","niu","nokia","nvidia","o2","oneplus","oppo","orange","palm","panasonic","pantech","parla","philips","plum","posh",
            "prestigio","qmobile","qtek","razer","samsung","sharp","siemens","sonim","sony ericsson","sony","spice","t-mobile","toshiba",
            "unnecto","vertu","verykool","vivo","vodafone","wiko","xiaomi","xolo","yezz","yota","yu","zte"));
    public static String encode4Firebase(String str){
        str = str.replace(".", "_dot_").replace("$", "_dollar_").replace("[", "(").replace("]", ")").replace("#", "*").replace("/", " or ");
        return str;
    }
    public static String decodeNormal(String str){
        str = str.replace("_dot_",".").replace("_dollar_","$").replace("*","#").replace(" or ","/").replace("point", ".");
        return str;
    }
}
