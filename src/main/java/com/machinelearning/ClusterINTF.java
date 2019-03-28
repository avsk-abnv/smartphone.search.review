/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.machinelearning;
import com.accessObjects.*;
import java.util.ArrayList;
import java.util.Map;
/**
 *
 * @author Abhishek Abhinav
 */

public interface ClusterINTF {
    ArrayList<Map<String,ArrayList<String>>> getCluster(Map<String,ArrayList<String>> devices, Devicevector deviceNode);
    Map<String,ArrayList<String>> getNodesForCluster(Map<String,ArrayList<String>> devices);
}
