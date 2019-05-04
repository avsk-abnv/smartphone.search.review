/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.accessObjects.Device;
import static com.accessObjects.Globals.encode4Firebase;
import com.weblogics.DBDevice;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Abhishek Abhinav
 */
public class Fetch extends HttpServlet {

    protected static int processed;
    ArrayList<String> sortedBy_Feature;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String reqData = request.getParameter("request");
            if (reqData.equalsIgnoreCase("ping")||reqData.equalsIgnoreCase("ping-homepage")||reqData.equalsIgnoreCase("ping-search")) {
                int index = Integer.parseInt(request.getParameter("index"));
                DBDevice db = new DBDevice();
                if(index == 0 && reqData.equalsIgnoreCase("ping"))
                    sortedBy_Feature = Filter.filter_DeviceIDs;
                if(index == 0 && reqData.equalsIgnoreCase("ping-homepage"))
                    sortedBy_Feature = HomepageData.home_deviceIDs;
                if(index == 0 && reqData.equalsIgnoreCase("ping-search"))
                    sortedBy_Feature = SearchResult.devIDs;
                    //sortedBy_Feature = More4Servlets.sortBy_Feature(Filter.filter_DeviceIDs, Filter.traversedData);
                String brand = sortedBy_Feature.get(index).split("%")[0];
                String model = sortedBy_Feature.get(index).split("%")[1];
                String price = db.getData("devices/"+brand+"/"+model+"/Misc/Price").toString().split(" ")[1];
                String likes = db.getData("Metadata/"+brand+"/"+model+"/likes").toString();
                String dislikes = db.getData("Metadata/"+brand+"/"+model+"/dislikes").toString();
                Device device = db.getByDeviceID(brand+"%"+encode4Firebase(model));
                String toPrint1 = device.brand+","+model.replace("_dot_",".")+","+device.info.get("imageURL").get("main").replace("_dot_", ".")+",&#8377; "+price+","+likes+","+dislikes;
                HttpSession session = request.getSession();
                String toPrint2 ="";
                if(!session.getAttribute("username").equals("null")){
                    String username = session.getAttribute("username").toString();
                    String interaction = db.getData("Users/"+username.replace(".","%")+"/like_dislike/"+brand+"%"+encode4Firebase(model)).toString();
                    if(!interaction.equals("error")){
                        toPrint2 += "~"+interaction;
                    }
                }
                
                out.println(toPrint1+toPrint2);
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
    