/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import static com.accessObjects.Globals.encode4Firebase;
import com.weblogics.DBDevice;
import java.io.IOException;
import java.io.PrintWriter;
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
public class Comment extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String comment = request.getParameter("comment");
            String email = request.getParameter("email");
            String deviceID = request.getParameter("deviceID");
            String type = request.getParameter("type");
            DBDevice db = new DBDevice();
            //Object dObj = db.getData("Comments/Device_comment");
            //Object uObj = db.getData("Comments/User_comment");
            if (type.equals("send")) {
                int total_comments = Integer.parseInt(db.getData("Comments/comment/size").toString());
                boolean success1 = db.setData("Comments/comment/" + "c" + (total_comments + 1) + "/comment_text", comment);
                boolean success2 = db.setData("Comments/comment/" + "c" + (total_comments + 1) + "/sentiment_value", "0");
                boolean success3 = db.setData("Comments/comment/" + "c" + (total_comments + 1) + "/commented_by", email.replace(".","%"));
                boolean success4 = db.setData("Comments/comment/" + "c" + (total_comments + 1) + "/commented_on", encode4Firebase(deviceID));
                boolean success5 = db.setData("Comments/comment/size", (total_comments + 1) + "");
                boolean success6 = db.setData("Comments/User_comment/" + email.replace(".", "%")+"/c" + (total_comments + 1),comment);
                boolean success7 = db.setData("Comments/Device_comment/" + encode4Firebase(deviceID)+"/c" + (total_comments + 1),comment);
                String name = db.getData("Users/" + email.replace(".", "%") + "/name").toString();
                if (success1 && success2 && success3 && success4 && success5 && success6 && success7) {
                    out.println(comment + " ," + name);
                } else {
                    out.println("failed");
                }
            } else if (type.equals("get")) {
                Map<String,String> dMap = new HashMap<>();
                Object dObj = db.getData("Comments/Device_comment/"+encode4Firebase(deviceID));
                if(!dObj.toString().equals("error")){
                    dMap = (Map) dObj;
                }
                if(dMap.isEmpty()){
                    out.println("null");
                } else{
                    String toprint = "";
                    for(String cid : dMap.keySet()){
                        toprint += dMap.get(cid)+"==";
                        String uid = db.getData("Comments/comment/"+cid+"/commented_by").toString();
                        String uname = db.getData("Users/" + uid.replace(".", "%") + "/name").toString();
                        toprint += uname+";";
                    }
                    if(toprint.length()>0){
                        toprint = toprint.substring(0, toprint.length()-1);
                    } else {
                        toprint = "null";
                    }
                    out.println(toprint);
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
