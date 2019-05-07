function popuplogin() {
    $('.popup-login').css("display", "block");
    $('.full-mask').css("display", "block");
    $(".popup-login").load("./register_login.jsp");
}
function close_popup() {
    $('.popup-login').css("display", "none");
    $('.full-mask').css("display", "none");
}
function show_register() {
    $('.login-container').css("display", "none");
    $('.login-container input').val("");
    $('.register-container').css("display", "block");
    $('.error-message').css("display", "none");
}
function show_login() {
    $('.register-container').css("display", "none");
    $('.register-container input').val("");
    $('.login-container').css("display", "block");
    $('.error-message').css("display", "none");
}
function clicked_logout() {
    post("Logout", {logout: "logout"}, "post");
    $('.fullname').css("width", "10%");
}
function clicked_login() {
    var validated = validate_login_fields();
    if (validated) {
        $.ajax({
            type: 'POST',
            url: "/Smartphone.search.review/Authenticate",
            data: {type: "login", email: $('.username').val(), password: $('.login-container .password').val()},
            success: function (response) {
                console.log($.trim(response));
                if ($.trim(response) === "Authentication Successful") {
                    $('.error-message').css("display", "none");
                    post("Authenticated", {username: $('.username').val()}, "post");
                } else {
                    $('.error-message').css("display", "block");
                    $('.error-message').html($.trim(response));
                }
            }
        });
    }
}
if (document.querySelector('body').getAttribute("loggedin") !== "null") {
    $('.login').css("display", "none");
    $('.fullname').html(document.querySelector('body').getAttribute("name"));
    $('.fullname').css("width", "20%");
    $('.logout').html("Logout");
    document.querySelector('.logout').setAttribute("onclick", "clicked_logout();");
} else {

}

function clicked_register() {
    var validated = validate_register_fields();
    if (validated) {
        $.ajax({
            type: 'POST',
            url: "/Smartphone.search.review/Authenticate",
            data: {type: "register", firstname: $('.first-name').val(), lastname: $('.last-name').val(), email: $('.email').val(), password: $('.register-container .password').val()},
            success: function (response) {
                console.log($.trim(response));
                if ($.trim(response) === "Sucessfully registered") {
                    $('.error-message').css("display", "none");
                    post("Authenticated", {username: $('.email').val()}, "post");
                } else {
                    $('.error-message').css("display", "block");
                    $('.error-message').html($.trim(response));
                }
            }
        });
    }
}
function validate_login_fields() {
    var emptyFields = $('.username').val() === "" || $('.login-container .password').val() === "";
    var correctEmailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test($('.username').val());
    if (emptyFields) {
        $('.error-message').css("display", "block");
        $('.error-message').html("Some fields are empty !");
    } else if (!correctEmailFormat) {
        $('.error-message').css("display", "block");
        $('.error-message').html("Email is not correct !");
    } else {
        $('.error-message').css("display", "none");
        return true;
    }
    return false;
}
function validate_register_fields() {
    var emptyFields = $('.first-name').val() === "" || $('.email').val() === "" || $('.register-container .password').val() === "" || $('.confirm-password').val() === "";
    var correctEmailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test($('.email').val());
    var bothpassword_match = $('.register-container .password').val() === $('.confirm-password').val();
    if (emptyFields) {
        $('.error-message').css("display", "block");
        $('.error-message').html("Some fields are empty !");
    } else if (!correctEmailFormat) {
        $('.error-message').css("display", "block");
        $('.error-message').html("Email is not correct !");
    } else if (!bothpassword_match) {
        $('.error-message').css("display", "block");
        $('.error-message').html("Passwords do not match !");
    } else {
        $('.error-message').css("display", "none");
        return true;
    }
    return false;
}
function post(path, params, method) {
    method = method || "post"; // Set method to post by default if not specified.

    // The rest of this code assumes you are not using a library.
    // It can be made less wordy if you use one.
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for (var key in params) {
        if (params.hasOwnProperty(key)) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", params[key]);

            form.appendChild(hiddenField);
        }
    }

    document.body.appendChild(form);
    form.submit();
}