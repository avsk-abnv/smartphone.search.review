$("#main-body").ready(function () {
    if (window.location.hash === "")
        tryGettingHomepageData();
});
function tryGettingHomepageData() {
    setTimeout(function () {
        if ($('.grid-cols').length === 16) {
            getHomepageData(1);
        } else {
            tryGettingHomepageData();
        }
    }, 500);
}
function getHomepageData(page) {
    $('.loader-mask').css("display", "block");
    $('.loader-mask .loading').html("Loading ... ");
    $('.filter-result').css("visibility", "hidden");
    $('.filter-result-count').css("visibility", "hidden");
    window.location.hash = "page:" + page;
    $('.page-count').html(page + "");
    $.ajax({
        type: 'POST',
        url: "/Smartphone.search.review/HomepageData",
        data: {page: page + ""},
        success: function (response) {
            if ($.trim(response) === "Page loaded") {
                console.log($.trim(response));
                fetchFromDatabase("ping-homepage", 5245 - (page - 1) * 16, (page - 1) * 16);
            } else if ($.trim(response).startsWith("Finished")) {
                console.log($.trim(response));
                fetchFromDatabase("ping-homepage", 5245 - (page - 1) * 16, (page - 1) * 16);
            } else
                console.log($.trim(response));

        }
    });
}
function fetchFromDatabase(sData, no_data, timesCalled) {

    $.ajax({
        type: 'POST',
        url: "/Smartphone.search.review/Fetch",
        data: {request: sData, index: timesCalled},
        success: function (response) {
            var total_data = 15;
            if (no_data < 16)
                total_data = no_data - 1;
            $('.loader-mask .loading').html("Fetching Data... " + (timesCalled % 16 + 1) + "/" + (total_data + 1));
            var sub_response = $.trim(response).split("~");

            var brand = sub_response[0].split(",")[0];
            var title = sub_response[0].split(",")[1];
            var imageURL = sub_response[0].split(",")[2];
            var price = sub_response[0].split(",")[3];
            var likes = sub_response[0].split(",")[4];
            var dislikes = sub_response[0].split(",")[5];
            var interaction = "none";
            if (sub_response.length > 1) {
                interaction = sub_response[1];
            }
            if (interaction === "like") {
                $(".like i").eq(timesCalled % 16).css("color", "rgb(0, 125, 255)");
            } else if (interaction === "dislike") {
                $(".dislike i").eq(timesCalled % 16).css("color", "rgb(164, 0, 0)");
            } else {
                $(".like i").eq(timesCalled % 16).css("color", "rgb(146, 146, 146)");
                $(".dislike i").eq(timesCalled % 16).css("color", "rgb(146, 146, 146)");
            }
            console.log("title: " + title + ",imageURL: " + imageURL + ",price: " + price + ",likes: " + likes + ",dislikes: " + dislikes + ",interaction: " + interaction);
            $('.thumbnails').eq(timesCalled % 16).attr("src", imageURL);
            $('.title').eq(timesCalled % 16).html(title);
            $('.img-container').eq(timesCalled % 16).css("display", "block");
            $('.img-container').eq(timesCalled % 16).parent().attr("data-brand", brand);
            $('.title').eq(timesCalled % 16).css("display", "block");
            $('.price').eq(timesCalled % 16).css("display", "block");
            $('.likedislike').eq(timesCalled % 16).css("display", "block");
            $('.price span').eq(timesCalled % 16).html(price);
            $('.title').eq(timesCalled % 16).attr("data-brand", brand);
            $('span.like-count').eq(timesCalled % 16).html(likes);
            $('span.dislike-count').eq(timesCalled % 16).html(dislikes);
            if ($('.compare-click').text() === "Cancel") {
                $('.container-body').eq(timesCalled % 16).css("display", "block");
                $('.container-body input').eq(timesCalled % 16).prop("checked", false);
                $('.grid-cols').eq(timesCalled % 16).css("border", "1px solid transparent");
            } else {
                $('.container-body').eq(timesCalled % 16).css("display", "none");
                $('.container-body input').eq(timesCalled % 16).prop("checked", false);
                $('.grid-cols').eq(timesCalled % 16).css("border", "1px solid transparent");
            }
            console.log("no_data: " + no_data + ",total_data: " + total_data);
            if (timesCalled % 16 < total_data) {
                timesCalled++;
                fetchFromDatabase(sData, no_data, timesCalled);
            } else {
                $('.loader-mask').css("display", "none");
                if ($('.page-count').html() === "1")
                    $('.prev-page').css("visibility", "hidden");
                else
                    $('.prev-page').css("visibility", "visible");
                if (no_data < 16)
                    $('.next-page').css("visibility", "hidden");
                else
                    $('.next-page').css("visibility", "visible");
            }
        }
    });
}
