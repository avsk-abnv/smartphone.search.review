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
            <%@ include file="./css/checkbox.css" %>
        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script>
            <%@ include file="./js/script-init.js" %>
        </script>

    </head>
    <body style="margin:0px;">
        <div class="mask"></div>
        <div id="middle">
            <%@ include file="./navbar.src" %>
            <div id="row-fmain" class="row">
                <div id="filter-container">
                    <div class="filters column">yoyo</div>
                    <div id="filters">
                        <div class="filter-title">
                            <i class="fa fa-filter" aria-hidden="true"></i>
                            <span style="margin-left:10px;">Filters</span>
                        </div>
                        <div class="filter-brand filter-all">
                            <span id="brand-text">Brands </span>
                            <ul class="brand-ul">
                                <%for (int i = 0; i < 4; i++) {
                                    String capitalized = Globals.brands.get(i).substring(0,1).toUpperCase()+Globals.brands.get(i).substring(1);
                                %>

                                <li>
                                    <label class="container"><%=capitalized%><input type="checkbox">
                                        <span class="checkmark"></span>
                                    </label>
                                </li>

                                <%}%>
                                <li id="showmore" onclick="showbrands();">Show more ...</li>
                            </ul>
                        </div>
                        <%@ include file="./allbrands.src" %>
                        <div class="filter-os filter-all"></div>
                        <div class="filter-external filter-all"></div>
                        <div class="filter-internal filter-all"></div>
                        <div class="filter-ram filter-all"></div>
                        <div class="filter-battery filter-all"></div>
                        <div class="filter-camera filter-all"s></div>
                    </div>
                </div>
                <div id="main-col" class="column">
                    <div id="main-body" class="column">
                        <div class="sortings row">
                            <div class="sort-by" align="center">Sort By :</div>
                            <div class="popularity" align="center">Popularity</div>
                            <div class="recommendation" align="center">Recommendation</div>
                        </div>
                        <%
                            //ArrayList<String> deviceIDs = (ArrayList)request.getAttribute("deviceIDs");
                            ArrayList<ArrayList<Device>> devices = (ArrayList) request.getAttribute("devices");
                            for (ArrayList<Device> deviceRow : devices) {
                        %>
                        <div class="grid-row row">
                            <%for (Device device : deviceRow) {%>
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
    <script>
        <%@ include file="./js/script.js" %>
    </script>
    <noscript>Your browser does not support JavaScript</noscript>
</html>
