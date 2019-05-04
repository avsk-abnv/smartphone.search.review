function openHomepage(){
    window.location.href = window.location.href.substring(0,window.location.href.indexOf("Homepage"))+"Homepage";
}

$(document).ready(function(e){
    if(!$('.grid-cols').children().length>0){
        trygettingcomments();
    }
});
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
function compareclick(){
    if($('.compare-click').html() === "Click to Compare :"){
        //compare: compare click
        $('.compare-click').html("Cancel");
        $('.grid-cols .title').attr("onclick","");
        $('.grid-cols .img-container').attr("onclick","");
        $('.container-body').css("display","block");
        for(let i=0; i<$('.img-container').length; i++){
            if($('.img-container').eq(i).css("display")==="none"){
                $('.container-body').eq(i).css("display","none");
            }
        }
        $('.title').css("cursor","auto");
        $('.img-container').css("cursor","auto");
        
    }else{
        //compare: cancel click
        $('.compare-click').html("Click to Compare :");
        $('.grid-cols .title').attr("onclick","showdetails(this.parentElement);");
        $('.grid-cols .img-container').attr("onclick","showdetails(this.parentElement);");
        $('.container-body').css("display","none");
        $('.grid-cols').css("border","1px solid transparent");
        $('.container-body input').prop("checked",false);
        $('.model-container').text("");
        $('.title').css("cursor","pointer");
        $('.img-container').css("cursor","pointer");
        $('#compare-body').css("display","none");
        $('#main-body').css("display","block");
    }
}
function select_me(event){
    var modelName = event.children[event.children.length-2].textContent;
    var $checkbox = event.children[2].children[0];
    var brand = event.getAttribute("data-brand");
    if($checkbox.checked){
        if($('.model-container').eq(0).text().length === 0){
           event.style.border = "3px solid #84b3ff";
           $('.model-container').eq(0).text(modelName); 
           $('.model-container').eq(0).attr("data-brand",brand);
        }else if($('.model-container').eq(1).text().length === 0){
           event.style.border = "3px solid #84b3ff";
           $('.model-container').eq(1).text(modelName);  
           $('.model-container').eq(1).attr("data-brand",brand);
        }else{
            $checkbox.checked = false;
        }
    }else{
        event.style.border = "1px solid transparent";
        if($('.model-container').eq(0).text() === modelName){
           $('.model-container').eq(0).text(""); 
           $('.model-container').eq(0).attr("data-brand","unknown");
        }else if($('.model-container').eq(1).text() === modelName){
           $('.model-container').eq(1).text("");
           $('.model-container').eq(1).attr("data-brand","unknown");
        }
    }
}
function showdetails(event){
    var brand = event.getAttribute("data-brand");
    var model = event.children[event.children.length-2].textContent;
    console.log(brand+":"+model);
    var url = window.location.href;
    model = model.toString().replace("&","_and_");
    url = url.slice(0,url.toString().indexOf("Homepage"))+"Details?brand="+brand+"&model="+model;
    console.log(url);
    window.location.href = url;
}

function filterapply(action) {
    if (action === "show") {
        $('.filter-title-container span').html("Filters Selected");
        $('.apply-filter').css("display", "block");
    }
    if (action === "hide") {
        $('.filter-title-container span').html("Filters");
        $('.apply-filter').css("display", "none");
    }
}
function comparison(){
    
    var $model_a = $('.model-container').eq(0).text();
    var $model_b = $('.model-container').eq(1).text();
    var $brand_a = $('.model-container').eq(0).attr('data-brand');
    var $brand_b = $('.model-container').eq(1).attr('data-brand');
    console.log($brand_a+"%"+$model_a);
    console.log($brand_b+"%"+$model_b);
    if($model_a.length>0 && $model_b.length>0){
        $("#compare-body").css("display","block");
        $("#main-body").css("display","none");
        $("#compare-body").load("./Comparison.jsp",{id_a:$brand_a+"%"+$model_a,id_b:$brand_b+"%"+$model_b});
        $("html, body").animate({scrollTop: 5}, "slow");
        $("html, body").animate({scrollTop: 0}, "slow");
    }
    
}
