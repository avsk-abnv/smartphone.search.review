/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.accessObjects.Device;
import static com.accessObjects.Globals.*;
import static com.accessObjects.Globals.encode4Firebase;
import static com.accessObjects.Globals.vectorIndex;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.weblogics.DBDevice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Abhishek Abhinav
 */
public class Homepage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DBDevice dbdevice = new DBDevice();
        //ArrayList<String> deviceIDs = dbdevice.filterByOS("android", 16);
        //request.setAttribute("deviceIDs", deviceIDs);
        File file = new File("L:\\4th Year Project\\ongoing\\devices\\acer.json");
        Map<String, Map<String, Map<String, String>>> jsonData = new HashMap<>();
        BufferedReader jsonReader = new BufferedReader(new FileReader(file));
        jsonData = (new Gson()).fromJson(jsonReader, jsonData.getClass());
        ArrayList<ArrayList<Device>> devices = new ArrayList<>();
        ArrayList<Device> devRow = new ArrayList<>();
        int count = 0;
        for (String model : jsonData.keySet()) {
            Device device = new Device();
            device.info = jsonData.get(model);
            device.brand = "acer";
            device.model = model;
            devRow.add(device);
            if (devRow.size() == 4) {
                devices.add(devRow);
                devRow = new ArrayList<>();
            }
            count++;
            if (count == 16) {
                break;
            }
        }
        request.setAttribute("devices", devices);
        RequestDispatcher rd = request.getRequestDispatcher("Homepage.jsp");
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
