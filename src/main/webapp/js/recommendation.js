function recommendation(ctrl) {
    if ($('body').attr("loggedin") !== "null") {
        page = 1;
        $('.recommendation').css("font-weight", "bold");
        $('.recommendation').css("color", "#2874f0");
        $('.recommendation').css("background", "#f1f3f6");
        $('.loader-mask').css("display", "block");
        $('.loader-mask .loading').html("Loading ... ");
        $('.filter-result').css("visibility", "hidden");
        $('.filter-result-count').css("visibility", "hidden");
        $('.page-count').html(page + "");
        $('.relevance').css("font-weight", "normal");
        $('.relevance').css("box-shadow", "none");
        $('.img-container').css("display", "none");
        $('.title').css("display", "none");
        $('.likedislike').css("display", "none");
        $('.price').css("display", "none");
        window.location.hash = "recommendation_page:" + page;
        $.ajax({
            type: 'POST',
            url: "/Smartphone.search.review/Recommendation",
            data: {page: page + "", username: $('body').attr('loggedin')},
            success: function (response) {
                console.log($.trim(response));

                if ($.trim(response) !== "failed") {
                    var total = parseInt($.trim(response).split(",")[1]);
                    $('.full-mask').attr("total", total + "");
                    fetchFromDatabase("ping-recommendation", total - (page - 1) * 16, (page - 1) * 16);
                }
            }
        });
    }
}
function relevance() {
    $('.relevance').css("font-weight", "bold");
    $('.relevance').css("box-shadow", "inset 0 -10px 0px -6px #2874f0");
    $('.recommendation').css("font-weight", "normal");
    $('.recommendation').css("color", "black");
    $('.recommendation').css("background", "transparent");

    getHomepageData(1);
}