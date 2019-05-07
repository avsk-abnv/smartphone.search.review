<%-- 
    Document   : register_login
    Created on : 27 Apr, 2019, 12:02:16 PM
    Author     : Abhishek Abhinav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="cols login-container">
    <div class="close-row row">
        <div class="close-popup" onclick="close_popup();">x</div>
    </div>
    <div class="login-header" align="center">Login to Continue</div>
    <div class="error-message" align="center"></div>
    <div class="field-container" align="center">
        <input class="username field-input" type="text" autofocus="true" placeholder="Username or Email" required="true">
    </div>
    <div class="field-container" align="center">
        <input class="password field-input" type="password" autofocus="true" placeholder="Password" required="true">
    </div>
    <div class="login-button-container">
        <div class="login-button" align="center" onclick="clicked_login();">Login</div>
    </div>
    <div class="show-register" onclick="show_register();" align="center">New User ? Click to Register</div>
</div>
<div class="cols register-container">
    <div class="close-row row">
        <div class="close-popup" onclick="close_popup();">x</div>
    </div>
    <div class="login-header" align="center">Register</div>
    <div class="error-message" align="center"></div>
    <div class="field-container" align="center">
        <input class="first-name field-input" type="text" autofocus="true" placeholder="First Name" required="true">
    </div>
    <div class="field-container" align="center">
        <input class="last-name field-input" type="text" autofocus="true" placeholder="Last Name" required="true">
    </div>
    <div class="field-container" align="center">
        <input class="email field-input" type="text" autofocus="true" placeholder="Email" required="true">
    </div>
    <div class="field-container" align="center">
        <input class="password field-input" type="password" autofocus="true" placeholder="Password" required="true">
    </div>
    <div class="field-container" align="center">
        <input class="confirm-password field-input" type="password" autofocus="true" placeholder="Confirm Password" required="true">
    </div>
    <div class="register-button-container">
        <div class="register-button" align="center" onclick="clicked_register();">Register</div>
    </div>
    <div class="show-register" onclick="show_login();" align="center">Back to Login</div>
</div>

