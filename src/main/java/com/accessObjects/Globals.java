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
    public static ArrayList<String> VECTOR_INDEX = new ArrayList<String>(Arrays.asList("brandname","brandvalue","android","blackberry","windows",
        "symbian","other","external","minisd","microsd","internal","ram","4g","battery",
        "selfiecam","maincam"));
    public static ArrayList<String> BRANDS = new ArrayList<String>(Arrays.asList("acer","alcatel","allview","amazon","apple","archos","asus",
            "at&t","benq-siemens","benq","blackberry","blackview","blu","bq","casio","cat","celkon","coolpad","dell","energizer","eten",
            "fujitsu siemens","garmin-asus","gigabyte","gionee","google","haier","hp","htc","huawei","i-mate","i-mobile","icemobile","inq",
            "intex","jolla","karbonn","kyocera","lava","leeco","lenovo","lg","maxwest","meizu","micromax","microsoft","mitac","motorola",
            "mwg","nec","niu","nokia","nvidia","o2","oneplus","oppo","orange","palm","panasonic","pantech","parla","philips","plum","posh",
            "prestigio","qmobile","qtek","razer","samsung","sharp","siemens","sonim","sony ericsson","sony","spice","t-mobile","toshiba",
            "unnecto","vertu","verykool","vivo","vodafone","wiko","xiaomi","xolo","yezz","yota","yu","zte"));
    public static TreeMap<String, String> BRAND_MAPPING = (new Gson()).fromJson("{\"acer\":\"pack-0\",\"alcatel\":\"pack-0\",\"allview\":\"pack-1\",\"amazon\":\"pack-1\",\"apple\":\"pack-1\",\"archos\":\"pack-1\",\"asus\":\"pack-2\",\"atandt\":\"pack-2\",\"benq\":\"pack-2\",\"benq-siemens\":\"pack-2\",\"blackberry\":\"pack-2\",\"blackview\":\"pack-2\",\"blu\":\"pack-3\",\"bq\":\"pack-3\",\"casio\":\"pack-3\",\"cat\":\"pack-3\",\"celkon\":\"pack-4\",\"coolpad\":\"pack-4\",\"dell\":\"pack-4\",\"energizer\":\"pack-4\",\"eten\":\"pack-4\",\"fujitsu siemens\":\"pack-4\",\"garmin-asus\":\"pack-4\",\"gigabyte\":\"pack-4\",\"gionee\":\"pack-5\",\"google\":\"pack-5\",\"haier\":\"pack-5\",\"hp\":\"pack-5\",\"htc\":\"pack-6\",\"huawei\":\"pack-7\",\"i-mate\":\"pack-7\",\"i-mobile\":\"pack-7\",\"icemobile\":\"pack-7\",\"inq\":\"pack-7\",\"intex\":\"pack-8\",\"jolla\":\"pack-8\",\"karbonn\":\"pack-8\",\"kyocera\":\"pack-8\",\"lava\":\"pack-8\",\"leeco\":\"pack-8\",\"lenovo\":\"pack-9\",\"lg\":\"pack-10\",\"maxwest\":\"pack-11\",\"meizu\":\"pack-11\",\"micromax\":\"pack-11\",\"microsoft\":\"pack-12\",\"mitac\":\"pack-12\",\"motorola\":\"pack-12\",\"mwg\":\"pack-12\",\"nec\":\"pack-12\",\"niu\":\"pack-12\",\"nokia\":\"pack-13\",\"nvidia\":\"pack-13\",\"o2\":\"pack-13\",\"oneplus\":\"pack-13\",\"oppo\":\"pack-13\",\"orange\":\"pack-13\",\"palm\":\"pack-13\",\"panasonic\":\"pack-14\",\"pantech\":\"pack-14\",\"parla\":\"pack-14\",\"philips\":\"pack-14\",\"plum\":\"pack-14\",\"posh\":\"pack-14\",\"prestigio\":\"pack-14\",\"qmobile\":\"pack-15\",\"qtek\":\"pack-15\",\"razer\":\"pack-15\",\"samsung\":\"pack-16\",\"sharp\":\"pack-17\",\"siemens\":\"pack-17\",\"sonim\":\"pack-17\",\"sony\":\"pack-17\",\"sony ericsson\":\"pack-17\",\"spice\":\"pack-17\",\"t-mobile\":\"pack-17\",\"toshiba\":\"pack-17\",\"unnecto\":\"pack-18\",\"vertu\":\"pack-18\",\"verykool\":\"pack-18\",\"vivo\":\"pack-18\",\"vodafone\":\"pack-18\",\"wiko\":\"pack-18\",\"xiaomi\":\"pack-19\",\"xolo\":\"pack-19\",\"yezz\":\"pack-19\",\"yota\":\"pack-19\",\"yu\":\"pack-19\",\"zte\":\"pack-20\"}",TreeMap.class);
    public static TreeMap<String, TreeSet<String>> EXTERNAL_MAPPING = (new Gson()).fromJson("{\"0.5to16\":[\"pack-1\"],\"0to0.5\":[\"pack-0\"],\"128to256\":[\"pack-10\",\"pack-11\"],\"16to32\":[\"pack-2\",\"pack-3\",\"pack-4\",\"pack-5\",\"pack-6\"],\"256toinf\":[\"pack-12\"],\"32to64\":[\"pack-7\",\"pack-8\"],\"64to128\":[\"pack-9\"]}", TreeMap.class);
    public static TreeMap<String, TreeSet<String>> INTERNAL_MAPPING = (new Gson()).fromJson("{\"0.5to16\":[\"pack-2\",\"pack-3\",\"pack-4\",\"pack-5\",\"pack-6\",\"pack-7\",\"pack-8\"],\"0to0.5\":[\"pack-0\",\"pack-1\"],\"16to32\":[\"pack-10\",\"pack-9\"],\"32to64\":[\"pack-11\"],\"64toinf\":[\"pack-12\"]}", TreeMap.class);
    public static TreeMap<String, TreeSet<String>> RAM_MAPPING = (new Gson()).fromJson("{\"0.25to0.5\":[\"pack-1\",\"pack-2\"],\"0.5to1\":[\"pack-3\",\"pack-4\",\"pack-5\",\"pack-6\"],\"0to0.25\":[\"pack-0\"],\"1to2\":[\"pack-7\",\"pack-8\"],\"2to4\":[\"pack-10\",\"pack-9\"],\"4toinf\":[\"pack-11\"]}", TreeMap.class);
    public static TreeMap<String, TreeSet<String>> SELFIECAM_MAPPING = new Gson().fromJson("{\"0.3to4\":[\"pack-4\",\"pack-5\",\"pack-6\"],\"0to0.3\":[\"pack-0\",\"pack-1\",\"pack-2\",\"pack-3\"],\"4to5\":[\"pack-7\",\"pack-8\"],\"5to8\":[\"pack-9\"],\"8toinf\":[\"pack-10\"]}", TreeMap.class);
    public static TreeMap<String, TreeSet<String>> MAINCAM_MAPPING = new Gson().fromJson("{\"0.3to3\":[\"pack-1\"],\"0to0.3\":[\"pack-0\"],\"12to13\":[\"pack-10\",\"pack-11\"],\"13toinf\":[\"pack-12\"],\"3to4\":[\"pack-2\"],\"4to5\":[\"pack-3\",\"pack-4\",\"pack-5\"],\"5to8\":[\"pack-6\",\"pack-7\",\"pack-8\"],\"8to12\":[\"pack-9\"]}", TreeMap.class);
    public static TreeMap<String, TreeSet<String>> BATTERY_MAPPING = new Gson().fromJson("{\"0to1200\":[\"pack-0\"],\"1200to1400\":[\"pack-1\"],\"1400to1600\":[\"pack-2\"],\"1600to1800\":[\"pack-3\"],\"1800to2000\":[\"pack-4\"],\"2000to2400\":[\"pack-5\"],\"2400to2800\":[\"pack-6\"],\"2800to3000\":[\"pack-7\"],\"3000to3200\":[\"pack-8\"],\"3200to4000\":[\"pack-9\"],\"4000toinf\":[\"pack-10\"]}", TreeMap.class);
    public static TreeMap<String, TreeMap<String, String>> FILTER_SIZE = new Gson().fromJson("{\"battery\":{\"0to1200\":\"561\",\"1200to1400\":\"429\",\"1400to1600\":\"564\",\"1600to1800\":\"401\",\"1800to2000\":\"563\",\"2000to2400\":\"503\",\"2400to2800\":\"559\",\"2800to3000\":\"479\",\"3000to3200\":\"209\",\"3200to4000\":\"520\",\"4000toinf\":\"457\"},\"brand\":{\"acer\":\"96\",\"alcatel\":\"167\",\"allview\":\"129\",\"amazon\":\"17\",\"apple\":\"63\",\"archos\":\"39\",\"asus\":\"156\",\"atandt\":\"1\",\"benq\":\"8\",\"benq-siemens\":\"1\",\"blackberry\":\"67\",\"blackview\":\"6\",\"blu\":\"221\",\"bq\":\"20\",\"casio\":\"2\",\"cat\":\"9\",\"celkon\":\"101\",\"coolpad\":\"28\",\"dell\":\"18\",\"energizer\":\"16\",\"eten\":\"15\",\"fujitsu siemens\":\"2\",\"garmin-asus\":\"5\",\"gigabyte\":\"52\",\"gionee\":\"76\",\"google\":\"7\",\"haier\":\"7\",\"hp\":\"38\",\"htc\":\"250\",\"huawei\":\"245\",\"i-mate\":\"19\",\"i-mobile\":\"2\",\"icemobile\":\"27\",\"inq\":\"2\",\"intex\":\"15\",\"jolla\":\"3\",\"karbonn\":\"43\",\"kyocera\":\"14\",\"lava\":\"113\",\"leeco\":\"9\",\"lenovo\":\"173\",\"lg\":\"270\",\"maxwest\":\"35\",\"meizu\":\"49\",\"micromax\":\"191\",\"microsoft\":\"26\",\"mitac\":\"3\",\"motorola\":\"188\",\"mwg\":\"5\",\"nec\":\"1\",\"niu\":\"16\",\"nokia\":\"138\",\"nvidia\":\"3\",\"o2\":\"26\",\"oneplus\":\"8\",\"oppo\":\"86\",\"orange\":\"11\",\"palm\":\"7\",\"panasonic\":\"73\",\"pantech\":\"13\",\"parla\":\"2\",\"philips\":\"51\",\"plum\":\"54\",\"posh\":\"29\",\"prestigio\":\"56\",\"qmobile\":\"84\",\"qtek\":\"9\",\"razer\":\"2\",\"samsung\":\"505\",\"sharp\":\"19\",\"siemens\":\"1\",\"sonim\":\"3\",\"sony\":\"122\",\"sony ericsson\":\"31\",\"spice\":\"59\",\"t-mobile\":\"41\",\"toshiba\":\"20\",\"unnecto\":\"18\",\"vertu\":\"7\",\"verykool\":\"81\",\"vivo\":\"73\",\"vodafone\":\"38\",\"wiko\":\"63\",\"xiaomi\":\"80\",\"xolo\":\"80\",\"yezz\":\"61\",\"yota\":\"3\",\"yu\":\"13\",\"zte\":\"209\"},\"external\":{\"0.5to16\":\"383\",\"0to0.5\":\"515\",\"128to256\":\"876\",\"16to32\":\"2510\",\"256toinf\":\"109\",\"32to64\":\"680\",\"64to128\":\"172\"},\"internal\":{\"0.5to16\":\"3346\",\"0to0.5\":\"663\",\"16to32\":\"608\",\"32to64\":\"415\",\"64toinf\":\"213\"},\"maincam\":{\"0.3to3\":\"469\",\"0to0.3\":\"133\",\"12to13\":\"965\",\"13toinf\":\"388\",\"3to4\":\"536\",\"4to5\":\"1365\",\"5to8\":\"1208\",\"8to12\":\"181\"},\"os\":{\"android\":\"4576\",\"blackberry\":\"57\",\"other\":\"147\",\"symbian\":\"81\",\"windows\":\"384\"},\"ram\":{\"0.25to0.5\":\"920\",\"0.5to1\":\"2049\",\"0to0.25\":\"461\",\"1to2\":\"767\",\"2to4\":\"781\",\"4toinf\":\"267\"},\"selfiecam\":{\"0.3to4\":\"1477\",\"0to0.3\":\"2028\",\"4to5\":\"981\",\"5to8\":\"490\",\"8toinf\":\"269\"}}", TreeMap.class);
    public static String encode4Firebase(String str){
        str = str.replace(".", "_dot_").replace("$", "_dollar_").replace("[", "(").replace("]", ")").replace("#", "*").replace("/", " or ");
        return str;
    }
    public static String decodeNormal(String str){
        str = str.replace("_dot_",".").replace("_dollar_","$").replace(" or ","/").replace("point", ".");
        return str;
    }
    public static TreeSet<String> toTreeSet(Object obj){
        TreeSet<String> set = new Gson().fromJson(new Gson().toJson(obj),TreeSet.class);
        return set;
    }
    public static TreeMap<String, String> toTreeMap(Object obj){
        TreeMap<String, String> map = new Gson().fromJson(new Gson().toJson(obj),TreeMap.class);
        return map;
    }
}
