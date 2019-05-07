function addcomment(comment) {
    var username = $('body').attr('loggedin');
    var name = $('body').attr('name');
    var devicemodel = $('.title').html();
    var devicebrand = $('.title').attr('data-brand');
    if (username !== 'null') {
        $.ajax({
            type: 'POST',
            url: "/Smartphone.search.review/Comment",
            data: {comment: comment, email: username, deviceID: devicebrand + '%' + devicemodel, type: "send"},
            success: function (data) {
                console.log($.trim(data));
                $("<span class='commented-by'>" + name + "</span><div class='talk-bubble tri-right left-top comment-text-container'><div class='talktext'><p class='comment-text'>" + comment + "</p></div></div>").insertAfter($(".add-comment"));
                $('.comment').val("");
            }
        });
    } else{
        popuplogin();
    }
}

function trygettingcomments() {
    console.log("trying");
    setTimeout(function () {
        if (document.querySelectorAll(".model-details-1").length > 0) {
            getcomments();
        } else {
            trygettingcomments();
        }
    }, 500);
}
function getcomments() {
    var devicemodel = $('.title').html();
    var devicebrand = $('.title').attr('data-brand');
    //console.log("inside");
    $.ajax({
        type: 'POST',
        url: "/Smartphone.search.review/Comment",
        data: {comment: "null", email: "null", deviceID: devicebrand + '%' + devicemodel, type: "get"},
        success: function (data) {
            console.log($.trim(data));
            var comments = $.trim(data).split(";");
            for (let i = 0; i < comments.length; i++) {
                $('.comment-container').append("<span class='commented-by'>" + comments[i].split("==")[1] + "</span><div class='talk-bubble tri-right left-top comment-text-container'><div class='talktext'><p class='comment-text'>" + comments[i].split("==")[0] + "</p></div></div>");
            }

        }
    });
}