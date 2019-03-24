/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.accessObjects.Globals.*;
import static com.controller.HomepageData.home_deviceIDs;
import static com.controller.HomepageData.jsonReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author Abhishek Abhinav
 */
public class SearchAjax extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String reqStr = request.getParameter("request");
            if (reqStr.equalsIgnoreCase("ping")) {
                String value = request.getParameter("value");
                ArrayList<String> devIDs = new ArrayList<>();
                boolean brandMatched = false;
                int count = 0;
                tobreak:
                for (String brand : BRANDS) {
                    if (brand.startsWith(value.toLowerCase())) {
                        //if(!brandMatched) {brandMatched = true;}
                        String urlstr = "https://device-pics.firebaseapp.com/filters/brand/devicevector-brand-" + BRAND_MAPPING.get(brand) + ".json";
                        boolean exception = false;
                        try {
                            Gson gson = new GsonBuilder().create();
                            //System.out.println(jsonReader.hasNext());
                            URL url = new URL(urlstr);
                            JsonReader jsonreader = new JsonReader(new InputStreamReader(url.openStream()));
                            jsonreader.beginObject();
                            while (jsonreader.hasNext()) {
                                String model = jsonreader.nextName();
                                ArrayList<String> devarr = gson.fromJson(jsonreader.nextString(), ArrayList.class);
                                String brandLocal = devarr.get(0);

                                String deviceID = brandLocal + "%" + model;
                                if (brand.equalsIgnoreCase(brandLocal)) {
                                    count++;
                                    out.println(deviceID);
                                    if (value.length() == 1) {
                                        if (count >= 25) {
                                            jsonreader.endObject();
                                            jsonreader.close();
                                            break tobreak;
                                        }
                                    }
                                }
                                //jsonReader.endObject();
                            }
                            jsonreader.endObject();
                            jsonreader.close();

                            //System.out.println("--" + jsonreader.hasNext());
                        } catch (MalformedURLException ex) {
                            exception = true;
                        } catch (IOException ex) {
                            exception = true;
                        } finally {

                        }
                        if (exception) {

                        }
                    }
                }
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
