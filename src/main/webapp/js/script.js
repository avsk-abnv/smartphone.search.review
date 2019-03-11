var added = false;
var filterTop;
$(function () {
    $(document).scroll(function () {
        var $filter = $("#filters");
        filterTop=$(".filters").offset().top;
        if ($("#footer").offset().top - filterTop - $("#filters").height() <= 10)
        {
            if (!added) {
                $filter.css("position", "relative");
                $filter.css("margin", $("#footer").offset().top - $("#filters").height() - 2*$("#navbar").height() + "px 0 0 0");
                added = true;
            }
        } else {
            if(added){
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
});

