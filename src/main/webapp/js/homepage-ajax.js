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

