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
    window.location.hash = filterstring;
    filterstring = filterstring.replace("&","and");
    sendAjaxRequest("reset", filterstring); //reset to reset instance variables of servlet
}

function sendAjaxRequest(sData, filterstring) {
    $.ajax({
        type: 'POST',
        url: "/Smartphone.search.review/Filter",
        data: {filterstring: sData},
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
                $('.loader-mask .loading').html("Fetching Data... 0/"+(total_data+1));
                $('.img-container').css("display", "none");
                $('.title').css("display", "none");
                if (parseInt($.trim(data)) > 0) {
                    fetchFromDatabase("ping", parseInt($.trim(data)), 0);
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
            $('.loader-mask .loading').html("Fetching Data... " + (timesCalled + 1) + "/" + (total_data + 1));
            var brand = $.trim(response).split(",")[0];
            var title = $.trim(response).split(",")[1];
            var imageURL = $.trim(response).split(",")[2];
            console.log("title: " + title + ",imageURL: " + imageURL);
            $('.thumbnails').eq(timesCalled).attr("src", imageURL);
            $('.title').eq(timesCalled).html(title);
            $('.img-container').eq(timesCalled).css("display", "block");
            $('.img-container').eq(timesCalled).parent().attr("data-brand",brand);
            $('.title').eq(timesCalled).css("display", "block");

            console.log("no_data: " + no_data + ",total_data: " + total_data);
            if (timesCalled < total_data) {
                timesCalled++;
                fetchFromDatabase("ping", no_data, timesCalled);
            } else {
                $('.loader-mask').css("display", "none");
            }
        }
    });
}