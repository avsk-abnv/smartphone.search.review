<%-- 
    Document   : navbar
    Created on : 13 Mar, 2019, 7:34:13 PM
    Author     : Abhishek Abhinav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="navbar" class="row">
    <div class="logo"><div id="logo-design"onclick="openHomepage();">Smartphone Search & Review</div></div>
    <div class="search-box row">
        <input type="text" placeholder="Search devices" class="search-input" />
        <div class="search-icon"><i style="color:black" class="fa fa-search fa-lg"></i></div>
    </div>
    <div class="login">Login or Sign up</div>
    <div class="about">About</div>
    <div class="contact">Contact us</div>
</div>
<div id="compare" class="row">
    <div id="compare-spacing" class="row" style="height:40px;width:22%;background:transparent;margin:0px;"></div>
    <div id="compare-container" class="row">
        <div class="compare-click" align="center" onclick="compareclick();">Click to Compare :</div>
        <div class="compare-a">
            <div align="center" class="model-container"></div>
        </div>
        <span id="versus">Vs</span>
        <div class="compare-b">
            <div align="center" class="model-container"></div>
        </div>
        <div id="apply-cancel" align="center">Apply</div>
    </div>
</div>
