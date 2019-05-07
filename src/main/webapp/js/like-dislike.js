/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * color: #007dff;
 * color: #a40000;
 */
function click_like(index, id) {
    if ($('body').attr("loggedin") !== "null") {
        if (document.querySelectorAll(".dislike i")[index].style.color === "rgb(164, 0, 0)") {
            _remove_("dislike", index, id, "like");
        } else if (document.querySelectorAll(".like i")[index].style.color !== "rgb(0, 125, 255)") {
            document.querySelectorAll(".like i")[index].style.color = "rgb(0, 125, 255)";
            $.ajax({
                type: 'POST',
                url: "/Smartphone.search.review/LikeDislike",
                data: {type: "like", username: $('body').attr('loggedin'), deviceID: id.toString()},
                success: function (response) {
                    console.log($.trim(response));
                    var count = $.trim(response).split(",")[1];
                    document.querySelectorAll(".like-count")[index].innerHTML = count;
                }
            });
        } else {
            _remove_("like", index, id, "nothing");
        }


    } else {
        popuplogin();
    }
}
function click_dislike(index, id) {
    if ($('body').attr("loggedin") !== "null") {
        if (document.querySelectorAll(".like i")[index].style.color === "rgb(0, 125, 255)") {
            _remove_("like", index, id, "dislike");
        } else if (document.querySelectorAll(".dislike i")[index].style.color !== "rgb(164, 0, 0)") {
            document.querySelectorAll(".dislike i")[index].style.color = "rgb(164, 0, 0)";
            $.ajax({
                type: 'POST',
                url: "/Smartphone.search.review/LikeDislike",
                data: {type: "dislike", username: $('body').attr('loggedin'), deviceID: id.toString()},
                success: function (response) {
                    console.log($.trim(response));
                    var count = $.trim(response).split(",")[1];
                    document.querySelectorAll(".dislike-count")[index].innerHTML = count;
                }
            });
        } else {
            _remove_("dislike", index, id, "nothing");
        }


    } else {
        popuplogin();
    }
}
function _remove_(what, index, id, also) {
    if (what === "like") {
        document.querySelectorAll(".like i")[index].style.color = "rgb(146, 146, 146)";
        $.ajax({
            type: 'POST',
            url: "/Smartphone.search.review/LikeDislike",
            data: {type: "remove-like", username: $('body').attr('loggedin'), deviceID: id.toString()},
            success: function (response) {
                console.log($.trim(response));
                var count = $.trim(response).split(",")[1];
                document.querySelectorAll(".like-count")[index].innerHTML = count;
                if (also === "dislike") {
                    document.querySelectorAll(".dislike i")[index].style.color = "rgb(164, 0, 0)";
                    $.ajax({
                        type: 'POST',
                        url: "/Smartphone.search.review/LikeDislike",
                        data: {type: "dislike", username: $('body').attr('loggedin'), deviceID: id.toString()},
                        success: function (response) {
                            console.log($.trim(response));
                            var count = $.trim(response).split(",")[1];
                            document.querySelectorAll(".dislike-count")[index].innerHTML = count;
                        }
                    });
                }
            }
        });
    } else if (what === "dislike") {
        document.querySelectorAll(".dislike i")[index].style.color = "rgb(146, 146, 146)";
        $.ajax({
            type: 'POST',
            url: "/Smartphone.search.review/LikeDislike",
            data: {type: "remove-dislike", username: $('body').attr('loggedin'), deviceID: id.toString()},
            success: function (response) {
                console.log($.trim(response));
                var count = $.trim(response).split(",")[1];
                document.querySelectorAll(".dislike-count")[index].innerHTML = count;
                if (also === "like") {
                    document.querySelectorAll(".like i")[index].style.color = "rgb(0, 125, 255)";
                    $.ajax({
                        type: 'POST',
                        url: "/Smartphone.search.review/LikeDislike",
                        data: {type: "like", username: $('body').attr('loggedin'), deviceID: id.toString()},
                        success: function (response) {
                            console.log($.trim(response));
                            var count = $.trim(response).split(",")[1];
                            document.querySelectorAll(".like-count")[index].innerHTML = count;
                        }
                    });
                }
            }
        });
    }
}

