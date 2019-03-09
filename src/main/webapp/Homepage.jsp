<%-- 
    Document   : Homepage
    Created on : 8 Mar, 2019, 4:39:27 PM
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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Smartphone Search and Review</title>
        <link rel="stylesheet" type="text/css" href="https://device-pics.firebaseapp.com/bootstrap.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <style>
            <%@ include file="./css/style.css" %>
        </style>
    </head>
    <body style="margin:0px;">
        <div id="middle">
            <div id="navbar-container">
                <div id="navbar" class="row">
                    <div class="logo"><div id="logo-design">Smartphone Search & Review</div></div>
                    <div class="search-box row">
                        <input type="text" placeholder="Search devices" class="search-input" />
                        <div class="search-icon"><i style="color:black" class="fa fa-search fa-lg"></i></div>
                    </div>
                    <div class="login">Login or Sign up</div>
                    <div class="about">About</div>
                    <div class="contact">Contact us</div>
                </div>
            </div>
            <div id="row-fmain" class="row">
                <div id="filter-container">
                    <div id="filters" class="column">
                        <div class="filter-title">Filters</div>
                        
                    </div>
                </div>
                <div id="main-col" class="column">
                    <div id="main-body" class="column">
                        <%
                            //ArrayList<String> deviceIDs = (ArrayList)request.getAttribute("deviceIDs");
                            ArrayList<ArrayList<Device>> devices = (ArrayList)request.getAttribute("devices");
                            for (ArrayList<Device> deviceRow: devices) {
                        %>
                        <div class="grid-row row">
                            <%for (Device device: deviceRow) {%>
                            <div class="grid-cols column">
                                <div class="img-container" align="center">
                                    <img class="thumbnails" src="<%=Globals.decodeNormal(device.info.get("imageURL").get("main"))%>" alt="no image found" onerror="this.src = 'https://device-pics.firebaseapp.com/noimage.png';"/>
                                </div>
                                <h6 class="title"><%=device.model%></h6>
                            </div>
                            <%}%>
                        </div>
                        <%
                            }
                        %>
                        <div class="page-row row">
                            <div class="left-spacing"></div>
                            <div class="page-no" style="color: white;background-color: #0087a6;">1</div>
                            <div class="page-no">2</div>
                            <div class="page-no">3</div>
                            <div class="page-no">4</div>
                            <div class="page-no">5</div>
                            <div class="page-no">6</div>
                            <div class="page-no">7</div>
                            <div class="page-no">8</div>
                            <div class="page-no">9</div>
                            <div class="page-no">10</div>
                            <div class="next-page">NEXT</div>
                            <div class="right-spacing"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="footer"></div>
        </div>
    </body>
</html>
