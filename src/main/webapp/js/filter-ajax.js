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
    $('.page-count').html(1+"");
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
                $('.filter-result').html("Filter Results : ");
                $('.filter-result').css("visibility", "visible");
                $('.filter-result-count').css("visibility", "visible");
                $('.filter-result-count').html($.trim(data));
                var total_data = 15;
                if (parseInt($.trim(data)) < 16)
                    total_data = parseInt($.trim(data)) - 1;
                $('.loader-mask .loading').html("Fetching Data... 0/" + (total_data + 1));
                $('.img-container').css("display", "none");
                $('.title').css("display", "none");
                $('.likedislike').css("display", "none");
                $('.price').css("display", "none");
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