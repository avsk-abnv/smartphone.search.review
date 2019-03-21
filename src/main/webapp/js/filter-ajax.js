function filterAjax(event) {
    var filterstring = "";
    for (let i = 0; i < event.children.length; i++) {
        if (event.children[i].getAttribute("class") === "filter-selected") {
            filterstring += event.children[i].getAttribute("data-filter") + ",";
        }
    }
    filterstring = filterstring.substring(0, filterstring.length - 1);
    console.log(filterstring);
    $('.loader-mask').css("display", "block");
    window.location.hash = filterstring + "-page:1";
    filterstring = filterstring.replace("&", "and");
    sendAjaxRequest("reset", filterstring); //reset to reset instance variables of servlet
}

function sendAjaxRequest(sData, filterstring) {

    $.ajax({
        type: 'POST',
        url: "/Smartphone.search.review/Filter",
        data: {filterstring: filterstring, request: sData},
        success: function (data) {
            if (sData !== "get result")
                $('.loader-mask .loading').html($.trim(data));
            else {
                $('.filter-result').css("display", "block");
                $('.filter-result-count').css("display", "block");
                $('.filter-result-count').html($.trim(data));
                var total_data = 15;
                if (parseInt($.trim(data)) < 16)
                    total_data = parseInt($.trim(data)) - 1;
                $('.loader-mask .loading').html("Fetching Data... 0/" + (total_data + 1));
                $('.img-container').css("display", "none");
                $('.title').css("display", "none");
                if (parseInt($.trim(data)) > 0) {
                    var currPage;
                    if (window.location.hash === "")
                        currPage = "page:1";
                    else
                        currPage = window.location.hash.toString().split("-")[1];
                    var pageNo = parseInt(currPage.split(":")[1]);
                    fetchFromDatabase("ping", parseInt($.trim(data)) - (pageNo-1)*16, (pageNo-1)*16);
                } else {
                    $('.loader-mask').css("display", "none");
                }
            }
            if ($.trim(data).substring(0, 7) === "Loading" || $.trim(data).substring(0, 7) === "Loading.") {
                if ($.trim(data) === "Loading... 100%") {
                    sendAjaxRequest("get result", filterstring);
                } else {
                    sendAjaxRequest(filterstring, filterstring);
                }
            } else
                console.log($.trim(data));

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
            var brand = $.trim(response).split(",")[0];
            var title = $.trim(response).split(",")[1];
            var imageURL = $.trim(response).split(",")[2];
            console.log("title: " + title + ",imageURL: " + imageURL);
            $('.thumbnails').eq(timesCalled % 16).attr("src", imageURL);
            $('.title').eq(timesCalled % 16).html(title);
            $('.img-container').eq(timesCalled % 16).css("display", "block");
            $('.img-container').eq(timesCalled % 16).parent().attr("data-brand", brand);
            $('.title').eq(timesCalled % 16).css("display", "block");

            console.log("no_data: " + no_data + ",total_data: " + total_data);
            if (timesCalled % 16 < total_data) {
                timesCalled++;
                fetchFromDatabase("ping", no_data, timesCalled);
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