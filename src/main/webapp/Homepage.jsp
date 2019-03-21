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
            <%@ include file="./css/checkbox-body.css" %>
            <%@ include file="./css/loader.css" %>
        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script>
            <%@ include file="./js/script-init.js" %>
            <%@ include file="./js/script-filters.js" %>
        </script>

    </head>
    <body style="margin:0px;">
        <div class="mask"></div>
        <div class='loader-mask' align="center">
            <div class="lds-dual-ring"></div>
            <div class="loading">Loading...</div>
        </div>
        <div id="middle">
            <div id="mask-navbar"></div>
            <div id="navbar-container"></div>
            <div id="row-fmain" class="row">
                <div id="mask-filter"></div>
                <div id="filter-container">
                    <div class="filters column">yoyo</div>
                    <div id="filters"></div>
                </div>
                <div id="main-col" class="column">
                    <div id="main-body" class="column">
                        <div class="sortings row">
                            <div class="sort-by" align="center">Sort By :</div>
                            <div class="popularity" align="center">Popularity</div>
                            <div class="recommendation" align="center">Recommendation</div>
                            <div class="filter-result">Filter Results : </div>
                            <div class="filter-result-count">000</div>
                        </div>
                        <%
                            //ArrayList<String> deviceIDs = (ArrayList)request.getAttribute("deviceIDs");
                            ArrayList<ArrayList<Device>> devices = (ArrayList) request.getAttribute("devices");
                            for (ArrayList<Device> deviceRow : devices) {
                        %>
                        <div class="grid-row row">
                            <%for (Device device : deviceRow) {%>
                            <div class="grid-cols column" data-brand="<%=device.brand%>">
                                <div class="img-container" onclick="showdetails(this.parentElement);" align="center">
                                    <img class="thumbnails" src="<%=Globals.decodeNormal(device.info.get("imageURL").get("main"))%>" alt="no image found" onerror="this.src = 'https://device-pics.firebaseapp.com/noimage.png';"/>
                                </div>
                                <label class="container-body">
                                    <input onclick="select_me(this.parentElement.parentElement);" type="checkbox">
                                    <span class="checkmark-body"></span>
                                </label>
                                <h6 class="title" onclick="showdetails(this.parentElement);"><%=device.model%></h6>
                            </div>
                            <%}%>
                        </div>
                        <%
                            }
                        %>
                        <div id="mask-pageno"></div>
                        <div class="page-row row">
                            <div class="prev-page"><i class="fa fa-chevron-left" aria-hidden="true"></i> Previous</div>
                            <div class="next-page">Next <i class="fa fa-chevron-right" aria-hidden="true"></i></div>
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
