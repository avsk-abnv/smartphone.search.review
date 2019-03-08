/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.machinelearning;
import com.accessObjects.*;
import java.util.ArrayList;
/**
 *
 * @author Abhishek Abhinav
 */

public interface MachineLearning {
    ArrayList<ArrayList<Devicevector>> getCluster(ArrayList<Devicevector> devices, Devicevector deviceNode);
    ArrayList<Devicevector> getNodesForCluster(ArrayList<Devicevector> devices);
}
