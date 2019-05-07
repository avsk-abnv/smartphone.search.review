<%-- 
    Document   : Comparison
    Created on : 8 Apr, 2019, 8:58:07 PM
    Author     : Abhishek Abhinav
--%>

<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.URL"%>
<%@page import="com.accessObjects.*"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.File"%>
<%@page import="java.util.*"%>
<%@page import="com.weblogics.DBDevice"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String device_a = request.getParameter("id_a").split("%")[1];
    String device_b = request.getParameter("id_b").split("%")[1];
    String id_a = request.getParameter("id_a").split("%")[0] + "%" + Globals.encode4Firebase(device_a);
    String id_b = request.getParameter("id_b").split("%")[0] + "%" + Globals.encode4Firebase(device_b);
    DBDevice db = new DBDevice();
    Device deviceA = db.getByDeviceID(id_a);
    Device deviceB = db.getByDeviceID(id_b);
    Map<String, String> subfeaturesA;
    Map<String, String> subfeaturesB;
    Map<String, Map<String, String>> featuresA = new HashMap<>();
    Map<String, Map<String, String>> featuresB = new HashMap<>();
    for (String key : deviceA.info.keySet()) {
        subfeaturesA = new HashMap<>();
        subfeaturesB = new HashMap<>();
        for (String subkey : deviceA.info.get(key).keySet()) {
            if (deviceB.info.containsKey(key)) {
                if (deviceB.info.get(key).containsKey(subkey) && !subkey.equals("main")) {
                    subfeaturesA.put(subkey, deviceA.info.get(key).get(subkey));
                    subfeaturesB.put(subkey, deviceB.info.get(key).get(subkey));
                    //System.out.println(key+":"+subkey);
                }
            }
        }
        if (subfeaturesA.size() > 0 && subfeaturesB.size() > 0) {
            featuresA.put(key, subfeaturesA);
            featuresB.put(key, subfeaturesB);
        }
    }
%>
<div class="device-a">
    <div class="device-thumbnail">
        <div class="wrap-thumbnail">
            <img class="thumbnail-pic" src="<%=deviceA.info.get("imageURL").get("main")%>" alt="no image found" onerror="this.src = 'https://device-pics.firebaseapp.com/noimage.png';">
        </div>
        <div class="right-fill"></div>
    </div>
    <div class="device-title maintitle" align="left"><%=device_a%></div>
    <div class="features">
        <%for (String key : featuresA.keySet()) {%>
        <div class="device-title"><%=key%></div>
        <%for (String subkey : featuresA.get(key).keySet()) {%>
        <div class="feature-row">
            <div class="feature-title"><%=subkey%></div>
            <div class="feature-value"><%=featuresA.get(key).get(subkey)%></div>
        </div>
        <%}%>
        <%}%>
    </div>
</div>
<div class="device-b">
    <div class="device-thumbnail">
        <div class="wrap-thumbnail">
            <img class="thumbnail-pic" src="<%=deviceB.info.get("imageURL").get("main")%>" alt="no image found" onerror="this.src = 'https://device-pics.firebaseapp.com/noimage.png';">
        </div>
        <div class="right-fill"></div>
    </div>
    <div class="device-title maintitle" align="left"><%=device_b%></div>
    <div class="features">
        <%for (String key : featuresB.keySet()) {%>
        <div class="device-title"><%=key%></div>
        <%for (String subkey : featuresB.get(key).keySet()) {%>
        <div class="feature-row">
            <div class="feature-title"><%=subkey%></div>
            <div class="feature-value"><%=featuresB.get(key).get(subkey)%></div>
        </div>
        <%}%>
        <%}%>
    </div>
</div>
<script>
document.querySelector(".device-b").addEventListener("load", {
    for (let i = 0; i < document.querySelectorAll(".device-a .feature-row").length; i++) {
        var max;
        if (document.querySelectorAll(".device-a .feature-value")[i].scrollHeight > document.querySelectorAll(".device-b .feature-value")[i].scrollHeight) {
            max = document.querySelectorAll(".device-a .feature-value")[i].scrollHeight;
        } else if (document.querySelectorAll(".device-b .feature-value")[i].scrollHeight > document.querySelectorAll(".device-a .feature-value")[i].scrollHeight) {
            max = document.querySelectorAll(".device-b .feature-value")[i].scrollHeight;
        }
        document.querySelectorAll(".device-a .feature-row")[i].style.height = max + "px";
        document.querySelectorAll(".device-b .feature-row")[i].style.height = max + "px";
    }
});
</script>

