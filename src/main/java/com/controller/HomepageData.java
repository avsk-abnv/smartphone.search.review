/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import static com.accessObjects.Globals.VECTOR_INDEX;
import static com.accessObjects.Globals.encode4Firebase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Abhishek Abhinav
 */
public class HomepageData extends HttpServlet {

    public static JsonReader jsonReader = null;
    public static ArrayList<String> home_deviceIDs=new ArrayList<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int page = Integer.parseInt(request.getParameter("page").toString());
            System.out.println(page);
            if (page == 1 || home_deviceIDs.isEmpty()) {
                home_deviceIDs = new ArrayList<>();
                URL url = new URL("https://device-pics.firebaseapp.com/devicevector_sorted.json");
                jsonReader = new JsonReader(new InputStreamReader(url.openStream()));
                jsonReader.beginObject();
                
            }
            
            boolean exception = false;
            try {
                Gson gson = new GsonBuilder().create();
                //System.out.println(jsonReader.hasNext());
                while (jsonReader.hasNext()) {
                    String model = jsonReader.nextName();
                    ArrayList<String> devarr = gson.fromJson(jsonReader.nextString(), ArrayList.class);
                    String brand = devarr.get(0);
                    String deviceID = brand + "%" + encode4Firebase(model);
                    home_deviceIDs.add(deviceID);
                    if (home_deviceIDs.size() >= 16 * page) {
                        break;
                    }
                    //jsonReader.endObject();
                }
                if (!jsonReader.hasNext()) {
                    jsonReader.endObject();
                    jsonReader.close();
                    out.println("Finished:"+(home_deviceIDs.size()-16*(page-1)));
                }else{
                    out.println("Page loaded");
                }
                System.out.println("--"+jsonReader.hasNext());

            } catch (MalformedURLException ex) {
                exception = true;
            } catch (IOException ex) {
                exception = true;
            } finally {
                
            }
            if (exception) {
                home_deviceIDs.removeAll(home_deviceIDs);
                home_deviceIDs.add("error");
            }
        }

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
