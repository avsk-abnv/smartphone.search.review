/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weblogics;

import static com.accessObjects.Globals.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Abhishek Abhinav
 */
public class More4Servlets {

    public static ArrayList<String> getFilterPacks(String filterType, String filterValue) {
        double from = Double.parseDouble(filterValue.split("to")[0]);
        double to = 0;
        String startkey = "";
        String endkey = "";
        TreeMap<String, TreeSet<String>> currMap;
        if(filterType.equalsIgnoreCase("internal")){
            currMap = INTERNAL_MAPPING;
        } else if(filterType.equalsIgnoreCase("external")) {
            currMap = EXTERNAL_MAPPING;
        } else if(filterType.equalsIgnoreCase("ram")) {
            currMap = RAM_MAPPING;
        } else if(filterType.equalsIgnoreCase("selfiecam")) {
            currMap = SELFIECAM_MAPPING;
        } else if(filterType.equalsIgnoreCase("maincam")) {
            currMap = MAINCAM_MAPPING;
        } else if(filterType.equalsIgnoreCase("battery")) {
            currMap = BATTERY_MAPPING;
        } else {
            currMap = new TreeMap<>();
        }
        ArrayList<String> filterPacks = new ArrayList<>();
        if (filterValue.split("to")[1].equalsIgnoreCase("inf")) {
            to = 99999;
        } else {
            to = Double.parseDouble(filterValue.split("to")[1]);
        }
        for (String key : currMap.keySet()) {
            double from_key = Double.parseDouble(key.split("to")[0]);
            double to_key = 0;
            if (key.split("to")[1].equalsIgnoreCase("inf")) {
                to_key = 99999;
            } else {
                to_key = Double.parseDouble(key.split("to")[1]);
            }
            if (from > from_key || (from == 0 && from >= from_key)) {
                if (startkey.length() == 0) {
                    startkey = key;
                } else {
                    double startkey_from = Double.parseDouble(startkey.split("to")[0]);
                    double startkey_to = 0;
                    if (startkey.split("to")[1].equalsIgnoreCase("inf")) {
                        startkey_to = 99999;
                    } else {
                        startkey_to = Double.parseDouble(startkey.split("to")[1]);
                    }
                    if (from_key > startkey_from) {
                        startkey = key;
                    }
                }
            }
            if (to <= to_key) {
                if (endkey.length() == 0) {
                    endkey = key;
                } else {
                    double endkey_from = Double.parseDouble(endkey.split("to")[0]);
                    double endkey_to = 0;
                    if (endkey.split("to")[1].equalsIgnoreCase("inf")) {
                        endkey_to = 99999;
                    } else {
                        endkey_to = Double.parseDouble(endkey.split("to")[1]);
                    }
                    if (to_key < endkey_to) {
                        endkey = key;
                    }
                }
            }

        }
        String tempkey = startkey;
        int packSize = 0;
        while (tempkey != endkey) {
            for (String pack : toTreeSet(currMap.get(tempkey))) {
                filterPacks.add(pack);
            }
            packSize += Integer.parseInt(toTreeMap(FILTER_SIZE.get(filterType.toLowerCase())).get(tempkey));
            tempkey = nextKey(tempkey, currMap.keySet());
        }
        packSize += Integer.parseInt(toTreeMap(FILTER_SIZE.get(filterType.toLowerCase())).get(tempkey));
        for (String pack : toTreeSet(currMap.get(tempkey))) {
            filterPacks.add(pack);
        }
        filterPacks.add(0,packSize + "");
        return filterPacks;
    }

    public static String nextKey(String currKey, Set<String> keyset) {
        double from = Double.parseDouble(currKey.split("to")[0]);
        double to = 0;
        String nextkey = "";
        if (currKey.split("to")[1].equalsIgnoreCase("inf")) {
            to = 99999;
        } else {
            to = Double.parseDouble(currKey.split("to")[1]);
        }
        for (String key : keyset) {

            double from_key = Double.parseDouble(key.split("to")[0]);
            double to_key = 0;
            if (key.split("to")[1].equalsIgnoreCase("inf")) {
                to_key = 99999;
            } else {
                to_key = Double.parseDouble(key.split("to")[1]);
            }
            if (to == from_key) {
                nextkey = key;
                break;
            }
        }
        return nextkey;
    }
}
