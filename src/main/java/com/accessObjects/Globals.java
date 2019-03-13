/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.accessObjects;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;
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
    public static TreeMap<String, String> brand_mapping = (new Gson()).fromJson("{\"acer\":\"Set 0\",\"alcatel\":\"Set 0\",\"allview\":\"Set 1\",\"amazon\":\"Set 1\",\"apple\":\"Set 1\",\"archos\":\"Set 1\",\"asus\":\"Set 2\",\"atandt\":\"Set 2\",\"benq\":\"Set 2\",\"benq-siemens\":\"Set 2\",\"blackberry\":\"Set 2\",\"blackview\":\"Set 2\",\"blu\":\"Set 3\",\"bq\":\"Set 3\",\"casio\":\"Set 3\",\"cat\":\"Set 3\",\"celkon\":\"Set 4\",\"coolpad\":\"Set 4\",\"dell\":\"Set 4\",\"energizer\":\"Set 4\",\"eten\":\"Set 4\",\"fujitsu siemens\":\"Set 4\",\"garmin-asus\":\"Set 4\",\"gigabyte\":\"Set 4\",\"gionee\":\"Set 5\",\"google\":\"Set 5\",\"haier\":\"Set 5\",\"hp\":\"Set 5\",\"htc\":\"Set 6\",\"huawei\":\"Set 7\",\"i-mate\":\"Set 7\",\"i-mobile\":\"Set 7\",\"icemobile\":\"Set 7\",\"inq\":\"Set 7\",\"intex\":\"Set 8\",\"jolla\":\"Set 8\",\"karbonn\":\"Set 8\",\"kyocera\":\"Set 8\",\"lava\":\"Set 8\",\"leeco\":\"Set 8\",\"lenovo\":\"Set 9\",\"lg\":\"Set 10\",\"maxwest\":\"Set 11\",\"meizu\":\"Set 11\",\"micromax\":\"Set 11\",\"microsoft\":\"Set 12\",\"mitac\":\"Set 12\",\"motorola\":\"Set 12\",\"mwg\":\"Set 12\",\"nec\":\"Set 12\",\"niu\":\"Set 12\",\"nokia\":\"Set 13\",\"nvidia\":\"Set 13\",\"o2\":\"Set 13\",\"oneplus\":\"Set 13\",\"oppo\":\"Set 13\",\"orange\":\"Set 13\",\"palm\":\"Set 13\",\"panasonic\":\"Set 14\",\"pantech\":\"Set 14\",\"parla\":\"Set 14\",\"philips\":\"Set 14\",\"plum\":\"Set 14\",\"posh\":\"Set 14\",\"prestigio\":\"Set 14\",\"qmobile\":\"Set 15\",\"qtek\":\"Set 15\",\"razer\":\"Set 15\",\"samsung\":\"Set 16\",\"sharp\":\"Set 17\",\"siemens\":\"Set 17\",\"sonim\":\"Set 17\",\"sony\":\"Set 17\",\"sony ericsson\":\"Set 17\",\"spice\":\"Set 17\",\"t-mobile\":\"Set 17\",\"toshiba\":\"Set 17\",\"unnecto\":\"Set 18\",\"vertu\":\"Set 18\",\"verykool\":\"Set 18\",\"vivo\":\"Set 18\",\"vodafone\":\"Set 18\",\"wiko\":\"Set 18\",\"xiaomi\":\"Set 19\",\"xolo\":\"Set 19\",\"yezz\":\"Set 19\",\"yota\":\"Set 19\",\"yu\":\"Set 19\",\"zte\":\"Set 20\"}",TreeMap.class);
    public static TreeMap<String, TreeSet<String>> extMemory_mapping = (new Gson()).fromJson("{\"0.5to16\":[\"pack-1\"],\"0to0.5\":[\"pack-0\"],\"128to256\":[\"pack-10\",\"pack-11\"],\"16to32\":[\"pack-2\",\"pack-3\",\"pack-4\",\"pack-5\",\"pack-6\"],\"256toinf\":[\"pack-12\"],\"32to64\":[\"pack-7\",\"pack-8\"],\"64to128\":[\"pack-9\"]}", TreeMap.class);
    public static TreeMap<String, TreeSet<String>> intMemory_mapping = (new Gson()).fromJson("{\"0.5to16\":[\"pack-2\",\"pack-3\",\"pack-4\",\"pack-5\",\"pack-6\",\"pack-7\",\"pack-8\"],\"0to0.5\":[\"pack-0\",\"pack-1\"],\"16to32\":[\"pack-10\",\"pack-9\"],\"32to64\":[\"pack-11\"],\"64toinf\":[\"pack-12\"]}", TreeMap.class);
    public static TreeMap<String, TreeSet<String>> ram_mapping = (new Gson()).fromJson("{\"0.25to0.5\":[\"pack-1\",\"pack-2\"],\"0.5to1\":[\"pack-3\",\"pack-4\",\"pack-5\",\"pack-6\"],\"0to0.25\":[\"pack-0\"],\"1to2\":[\"pack-7\",\"pack-8\"],\"2to4\":[\"pack-10\",\"pack-9\"],\"4toinf\":[\"pack-11\"]}", TreeMap.class);
    public static TreeMap<String, TreeSet<String>> selfiecamera_mapping = new Gson().fromJson("{\"0.3to4\":[\"pack-4\",\"pack-5\",\"pack-6\"],\"0to0.3\":[\"pack-0\",\"pack-1\",\"pack-2\",\"pack-3\"],\"4to5\":[\"pack-7\",\"pack-8\"],\"5to8\":[\"pack-9\"],\"8toinf\":[\"pack-10\"]}", TreeMap.class);
    public static TreeMap<String, TreeSet<String>> maincamera_mapping = new Gson().fromJson("{\"0.3to3\":[\"pack-1\"],\"0to0.3\":[\"pack-0\"],\"12to13\":[\"pack-10\",\"pack-11\"],\"13toinf\":[\"pack-12\"],\"3to4\":[\"pack-2\"],\"4to5\":[\"pack-3\",\"pack-4\",\"pack-5\"],\"5to8\":[\"pack-6\",\"pack-7\",\"pack-8\"],\"8to12\":[\"pack-9\"]}", TreeMap.class);
    public static TreeMap<String, TreeSet<String>> battery_mapping = new Gson().fromJson("{\"0to1200\":[\"pack-0\"],\"1200to1400\":[\"pack-1\"],\"1400to1600\":[\"pack-2\"],\"1600to1800\":[\"pack-3\"],\"1800to2000\":[\"pack-4\"],\"2000to2400\":[\"pack-5\"],\"2400to2800\":[\"pack-6\"],\"2800to3000\":[\"pack-7\"],\"3000to3200\":[\"pack-8\"],\"3200to4000\":[\"pack-9\"],\"4000toinf\":[\"pack-10\"]}", TreeMap.class);
    public static String encode4Firebase(String str){
        str = str.replace(".", "_dot_").replace("$", "_dollar_").replace("[", "(").replace("]", ")").replace("#", "*").replace("/", " or ");
        return str;
    }
    public static String decodeNormal(String str){
        str = str.replace("_dot_",".").replace("_dollar_","$").replace("*","#").replace(" or ","/").replace("point", ".");
        return str;
    }
}
