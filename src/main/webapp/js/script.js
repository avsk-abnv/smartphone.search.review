var added = false;
var filterTop;
$(function () {
    $(document).scroll(function () {
        var $filter = $("#filters");
        filterTop = $(".filters").offset().top;
        if ($("#footer").offset().top - filterTop - $("#filters").height() <= 10)
        {
            if (!added) {
                $filter.css("position", "relative");
                $filter.css("margin", $("#footer").offset().top - $("#filters").height() - 2 * $("#navbar").height() + "px 0 0 0");
                added = true;
            }
        } else {
            if (added) {
                $filter.css("position", "fixed");
                $filter.css("margin", "0px");
                added = false;
            }
        }
    });
});

$(document).ready(function () {
    $("html, body").animate({scrollTop: 0}, "slow");
    $("#filters").css("max-height", $(window).height() - $("#filters").offset().top + "px");
    $(".filters").css("max-height", $(window).height() - $("#filters").offset().top + "px");
    $("#filters").load("./Filters.jsp");
    $("#navbar-container").load("./navbar.jsp");
});

$("#filters").ready(function () {
    var getHash;
    if (window.location.hash === "")
        getHash = "";
    else
        getHash = window.location.hash.toString().substring(1);
    if (getHash.length > 0) {
        tryApplying_hash(getHash);
    }

});
function tryApplying_hash(getHash) {
    setTimeout(function () {
        if ($('.filter-title-container span').text() !== "Filters Selected") {
            applyHash(getHash);
            tryApplying_hash(getHash);
        } else {
            console.log("hash applied");
            $('.loader-mask').css("display", "block");
            sendAjaxRequest("reset", getHash.split("-")[0].replace("&", "and")); //reset to reset instance variables of servlet
        }
    }, 500);
}

function applyHash(all_filterdata) {
    var currPage = all_filterdata.split("-")[1];
    all_filterdata = all_filterdata.split("-")[0];
    console.log(all_filterdata);
    var filterdata_arr = all_filterdata.split(",");
    for (let count = 0; count < filterdata_arr.length; count++) {
        var filter = filterdata_arr[count];
        var datasplit = filter.split(":");
        var filtertype = datasplit[0];
        var filterdata = datasplit[1];
        if (filtertype === "brand") {
            var brandnames_labels = $('.full-brand label');
            var brand = filterdata;
            for (let i = 0; i < brandnames_labels.length; i++) {
                if (brandnames_labels.eq(i).attr("data-brand") === brand) {
                    var input = brandnames_labels.eq(i).children().eq(0);
                    input.prop("checked", true);
                }
            }
            brandnames_labels = $('.short-brand label');
            for (let i = 0; i < brandnames_labels.length; i++) {
                if (brandnames_labels.eq(i).attr("data-brand") === brand) {
                    var input = brandnames_labels.eq(i).children().eq(0);
                    input.prop("checked", true);
                }
            }
        }
        if (filtertype === "os") {
            var os_labels = $(".os-ul label");
            var os = filterdata;
            for (let i = 0; i < os_labels.length; i++) {
                if (os_labels.eq(i).attr("data-os") === os) {
                    var input = os_labels.eq(i).children().eq(0);
                    input.prop("checked", true);
                }
            }
        }
        var checkFilter = ["external", "internal", "ram", "selfiecam", "maincam", "battery"];
        for (let i = 0; i < checkFilter.length; i++) {
            if (filtertype === checkFilter[i]) {
                var from = filterdata.split("to")[0];
                var to = filterdata.split("to")[1];
                if (to === "inf")
                    to = "";
                $('.filter-' + checkFilter[i]).children().eq(1).children().eq(0).children().eq(0).val(from);
                $('.filter-' + checkFilter[i]).children().eq(1).children().eq(0).children().eq(0).prop("disabled", true);
                $('.filter-' + checkFilter[i]).children().eq(1).children().eq(2).children().eq(0).val(to);
                $('.filter-' + checkFilter[i]).children().eq(1).children().eq(2).children().eq(0).prop("disabled", true);
                $('.filter-' + checkFilter[i]).children().eq(1).children().eq(3).css("display", "none");
                $('.filter-' + checkFilter[i]).children().eq(1).children().eq(4).css("display", "block");
            }
        }
        var insideDiv = filtertype;
        if (filtertype === "os" || filtertype === "brand")
            insideDiv = filterdata;
        $("<div onclick='filter_removeself(this);' class='filter-selected' id='" + insideDiv + "' data-filter='" + filtertype + ":" + filterdata + "'>" + insideDiv + "</div>").insertBefore($('.clearfloat'));
        filterapply("show");
        var currPage;
        if (window.location.hash === "")
            currPage = "page:1";
        else
            currPage = window.location.hash.toString().split("-")[1];
        var pageNo = parseInt(currPage.split(":")[1]);
        $('.page-count').html(pageNo + "");
    }
}

function nextPage() {
    var currPage;
    if (window.location.hash === "")
        currPage = "page:1";
    else
        currPage = window.location.hash.toString().split("-")[1];
    var pageNo = parseInt(currPage.split(":")[1]);

    var total_count = parseInt($(".filter-result-count").text().toString());
    if (total_count - pageNo * 16 > 0 && $('.filter-title span').text() === "Filters Selected") {
        window.location.hash = window.location.hash.toString().split("-")[0] + "-page:" + (pageNo + 1);
        var no_data = total_count - pageNo * 16;
        $('.loader-mask .loading').html("Fetching Data... 0/" + no_data);
        $('.img-container').css("display", "none");
        $('.title').css("display", "none");
        $('.loader-mask').css("display", "block");
        $('.page-count').html((pageNo + 1) + "");
        fetchFromDatabase("ping", no_data, pageNo * 16);
    }
}

function prevPage() {
    var currPage;
    if (window.location.hash === "")
        currPage = "page:1";
    else
        currPage = window.location.hash.toString().split("-")[1];
    var pageNo = parseInt(currPage.split(":")[1]);

    var total_count = parseInt($(".filter-result-count").text().toString());
    if (pageNo > 1 && $('.filter-title span').text() === "Filters Selected") {
        window.location.hash = window.location.hash.toString().split("-")[0] + "-page:" + (pageNo - 1);
        var no_data = total_count - (pageNo - 2) * 16;
        $('.loader-mask .loading').html("Fetching Data... 0/" + no_data);
        $('.img-container').css("display", "none");
        $('.title').css("display", "none");
        $('.loader-mask').css("display", "block");
        $('.page-count').html((pageNo - 1) + "");
        fetchFromDatabase("ping", no_data, (pageNo - 2) * 16);
    }
}