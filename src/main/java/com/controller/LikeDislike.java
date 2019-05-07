/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.weblogics.DBDevice;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import static com.accessObjects.Globals.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Abhishek Abhinav
 */
public class LikeDislike extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String type = request.getParameter("type");
            String username = request.getParameter("username");
            String deviceID = request.getParameter("deviceID");
            deviceID = deviceID.split("%")[0] + "%" + encode4Firebase(deviceID.split("%")[1]);
            if (!type.startsWith("remove")) {
                DBDevice db = new DBDevice();
                boolean binduser = db.setData("Users/" + username.replace(".", "%") + "/like_dislike/" + deviceID, type);
                boolean binddevice = db.setData("Metadata/" + deviceID.split("%")[0] + "/" + deviceID.split("%")[1] + "/like_dislike/" + username.replace(".", "%"), type);
                String count = db.getData("Metadata/" + deviceID.split("%")[0] + "/" + deviceID.split("%")[1] + "/" + type + "s").toString();
                count = Integer.parseInt(count) + 1 + "";
                //--------Cluster operation--------//
                String curr_cluster = db.getData("Users/" + username.replace(".", "%") + "/current_cluster").toString();
                String cl_pref = db.getData("Users/" + username.replace(".", "%") + "/preference_values/cluster/" + curr_cluster).toString();
                if (cl_pref.equals("error")) {
                    cl_pref = "0";
                }
                String devCluster = db.getData("Metadata/" + deviceID.split("%")[0] + "/" + deviceID.split("%")[1] + "/cluster").toString();
                String cl_pref_dev = db.getData("Users/" + username.replace(".", "%") + "/preference_values/cluster/" + devCluster).toString();
                if (cl_pref_dev.equals("error")) {
                    cl_pref_dev = "0";
                }
                if (type.equals("like")) {
                    cl_pref_dev = (Integer.parseInt(cl_pref_dev)+5)+"";
                } else if(type.equals("dislike")){
                    cl_pref_dev = (Integer.parseInt(cl_pref_dev)-5)+"";
                }
                boolean clusterCount_update = db.setData("Users/" + username.replace(".", "%") + "/preference_values/cluster/" + devCluster,cl_pref_dev);
                boolean currentCluster_update = true;
                if(Integer.parseInt(cl_pref_dev)>Integer.parseInt(cl_pref)){
                    db.setData("Users/" + username.replace(".", "%") + "/current_cluster",devCluster);
                    currentCluster_update = db.setData("Users/" + username.replace(".", "%") + "/current_cluster",devCluster);
                }
                //---------------------------------//
                boolean countupdate = db.setData("Metadata/" + deviceID.split("%")[0] + "/" + deviceID.split("%")[1] + "/" + type + "s", count);
                if (binduser && binddevice && countupdate && clusterCount_update && currentCluster_update) {
                    out.println("success," + count);
                } else {
                    out.println("failed");
                }
            } else {
                DBDevice db = new DBDevice();
                boolean binduser = db.remove("Users/" + username.replace(".", "%") + "/like_dislike/" + deviceID);
                boolean binddevice = db.remove("Metadata/" + deviceID.split("%")[0] + "/" + deviceID.split("%")[1] + "/like_dislike/" + username.replace(".", "%"));
                String count = db.getData("Metadata/" + deviceID.split("%")[0] + "/" + deviceID.split("%")[1] + "/" + type.split("-")[1] + "s").toString();
                count = Integer.parseInt(count) - 1 + "";
                
                //--------Cluster operation--------//
                String curr_cluster = db.getData("Users/" + username.replace(".", "%") + "/current_cluster").toString();
                String cl_pref = db.getData("Users/" + username.replace(".", "%") + "/preference_values/cluster/" + curr_cluster).toString();
                if (cl_pref.equals("error")) {
                    cl_pref = "0";
                }
                String devCluster = db.getData("Metadata/" + deviceID.split("%")[0] + "/" + deviceID.split("%")[1] + "/cluster").toString();
                String cl_pref_dev = db.getData("Users/" + username.replace(".", "%") + "/preference_values/cluster/" + devCluster).toString();
                if (cl_pref_dev.equals("error")) {
                    cl_pref_dev = "0";
                }
                if (type.split("-")[1].equals("like")) {
                    cl_pref_dev = (Integer.parseInt(cl_pref_dev)-5)+"";
                } else if(type.split("-")[1].equals("dislike")){
                    cl_pref_dev = (Integer.parseInt(cl_pref_dev)+5)+"";
                }
                boolean clusterCount_update = db.setData("Users/" + username.replace(".", "%") + "/preference_values/cluster/" + devCluster,cl_pref_dev);
                boolean currentCluster_update = true;
                if(Integer.parseInt(cl_pref_dev)>Integer.parseInt(cl_pref)){
                    db.setData("Users/" + username.replace(".", "%") + "/current_cluster",devCluster);
                    currentCluster_update = db.setData("Users/" + username.replace(".", "%") + "/current_cluster",devCluster);
                }
                //---------------------------------//
                boolean countupdate = db.setData("Metadata/" + deviceID.split("%")[0] + "/" + deviceID.split("%")[1] + "/" + type.split("-")[1] + "s", count);
                if (binduser && binddevice && countupdate && clusterCount_update && currentCluster_update) {
                    out.println("success," + count);
                } else {
                    out.println("failed");
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
