<%-- 
    Document   : Details
    Created on : 17 Mar, 2019, 9:41:12 AM
    Author     : Abhishek Abhinav
--%>

<%@page import="com.google.gson.Gson"%>
<%@page import="com.accessObjects.Globals"%>
<%@page import="com.accessObjects.Globals.*"%>
<%@page import="com.weblogics.GetFeature"%>
<%@page import="com.accessObjects.Device"%>
<%@page import="com.weblogics.DBDevice"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=request.getParameter("model")%></title>
        <link rel="stylesheet" type="text/css" href="https://device-pics.firebaseapp.com/bootstrap.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <style>
            <%@ include file="./css/style-details.css" %>
        </style>
    </head>
    <body style="margin:0px;">
        <div class="mask"></div>
        <div id="middle">
            <div id="mask-navbar"></div>
            <div id="navbar-container">
                <jsp:include page="navbar.jsp"></jsp:include>
                </div>
                <div id="row-fmain" class="row">
                <%
                    String brand = request.getParameter("brand");
                    String model = Globals.encode4Firebase(request.getParameter("model").replace("_and_", "&"));
                    DBDevice dev = new DBDevice();
                    Device device;
                    String os = "";
                    device = dev.getByDeviceID(brand + "%" + model);
                    if(device.info.containsKey("Platform")){
                        if(device.info.get("Platform").containsKey("OS")){
                            os = device.info.get("Platform").get("OS");
                        }
                    }
                    GetFeature gft = new GetFeature();
                %>
                <center>
                    <table class="shortdetails">
                        <tr>
                            <td class="model-details-1">
                                <!--<div class="title row">
                                    
                                </div>-->
                                <div class="container-left row">
                                    <i class="fa fa-camera" aria-hidden="true"></i>
                                    <div class="selfiecamera">Selfie Camera : <%=gft.getProperty(brand+"%"+model,"selfiecam")%></div>
                                </div>
                                <div class="container-left row">
                                    <i class="fa fa-microchip" aria-hidden="true"></i>
                                    <div class="ram">RAM : <%=gft.getProperty(brand+"%"+model,"ram")%></div>
                                </div>
                                <div class="container-left row">
                                    <i class="fa fa-code" aria-hidden="true"></i>
                                    <div class="os">OS : <%=os%></div>
                                </div>
                            </td>
                            <td class="image-container">
                                <img src="<%=device.info.get("imageURL").get("main")%>" alt="no image found" onerror="this.src = 'https://device-pics.firebaseapp.com/noimage.png';">
                            </td>
                            <td class="model-details-2">
                                <div class="container-right row">
                                    <i class="fa fa-camera" aria-hidden="true"></i>
                                    <div class="maincamera">Main Camera : <%=gft.getProperty(brand+"%"+model,"maincam")%></div>
                                    
                                </div>
                                <div class="container-right row">
                                    <i class="fa fa-microchip" aria-hidden="true"></i>
                                    <div class="internal">Internal : <%=gft.getProperty(brand+"%"+model,"internal")%></div>
                                </div>
                                <div class="container-right row">
                                    <i class="fa fa-battery-full" aria-hidden="true"></i>
                                    <div class="battery">Battery :  <%=gft.getProperty(brand+"%"+model,"battery")%></div>
                                </div>
                            </td>
                        </tr>
                    </table>
                    <div class="popularity">Popularity 87%</div>
                    <div class="title-container row">
                        <div class="like">
                            <i class="fa fa-thumbs-up" aria-hidden="true"></i>
                            <div class="likecount">2575</div>
                        </div>
                        <div class="left-triangle" style="float:left;"></div>
                        <div class="title"><%=device.model%></div>
                        <div class="right-triangle"></div>
                        <div class="dislike">
                            <i class="fa fa-thumbs-down" aria-hidden="true"></i>
                            <div class="dislikecount">75</div>
                        </div>
                    </div>
                </center>
            </div>
        </div>
    </body>
    <script>
        //document.querySelector(".left-triangle").style.marginLeft = (document.body.scrollWidth - document.querySelector(".title").offsetWidth - 80)/2 + "px";
        document.querySelector(".like").style.marginLeft = (document.body.scrollWidth - document.querySelector(".title").offsetWidth - 160)/2 + "px";
        document.querySelector(".popularity").style.width = document.querySelector("table").offsetWidth*0.87+"px";
    </script>
</html>
