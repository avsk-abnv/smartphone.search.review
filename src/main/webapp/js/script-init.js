function showbrands(){
    var $brand_div = $("#all-brand-names");
    $brand_div.css("display","block");
    $("#filters").css("overflow-y","hidden");
    $(".mask").css("display","block");
    $("body").css("overflow-y","hidden");
}
function hidebrands(){
    var $brand_div = $("#all-brand-names");
    $brand_div.css("display","none");
    $("#filters").css("overflow-y","scroll");
    $(".mask").css("display","none");
    $("body").css("overflow-y","scroll");
}


