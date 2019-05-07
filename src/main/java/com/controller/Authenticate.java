/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.accessObjects.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.weblogics.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Abhishek Abhinav
 */
public class Authenticate extends HttpServlet {

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
            String type = request.getParameter("type");

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            DBUser dbuser = new DBUser();
            if (type.equals("login")) {
                String message = dbuser.authenticateUser(email, password);
                out.println(message);
            } else if (type.equals("register")) {
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                User user = getDefaultUser(firstname+" "+lastname,password);
                
                String message = dbuser.registerUser(user, email);
                out.println(message);
            }
        }
    }

    public static User getDefaultUser(String name, String password) {
        User newuser = new User();
        Map<String, String> test = new HashMap<>();
        test.put("device_id_test", "like");
        newuser.name = name;
        newuser.password = password;
        newuser.like_dislike = test;
        newuser.current_cluster = "Cluster-1";
        Map<String, String> comments = new HashMap<>();
        comments.put("comment_id_test", "This is a test");
        newuser.comments = comments;
        Map<String, Map<String, String>> test_pref = new HashMap<>();
        test = new HashMap<>();
        test.put("device_id_test", "0");
        newuser.preference_values.devices = test;
        test = new HashMap<>();
        test.put("cluster_name", "Cluster-test");
        test.put("preference_value", "0");
        test_pref.put("index-test", test);
        newuser.preference_values.clusters = test_pref;
        test = new HashMap<>();
        test.put("cluster-test", "index-test");
        newuser.cluster_index_mapping = test;
        return newuser;
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
