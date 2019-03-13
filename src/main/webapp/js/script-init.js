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
        
    }
}
function select_me(event){
    var modelName = event.lastElementChild.textContent;
    var $checkbox = event.children[1].children[0];
    if($checkbox.checked){
        if($('.model-container').eq(0).text().length === 0){
           event.style.border = "3px solid #84b3ff";
           $('.model-container').eq(0).text(modelName); 
        }else if($('.model-container').eq(1).text().length === 0){
           event.style.border = "3px solid #84b3ff";
           $('.model-container').eq(1).text(modelName);  
        }else{
            $checkbox.checked = false;
        }
    }else{
        event.style.border = "1px solid transparent";
        if($('.model-container').eq(0).text() === modelName){
           $('.model-container').eq(0).text(""); 
        }else if($('.model-container').eq(1).text() === modelName){
           $('.model-container').eq(1).text("");
        }
    }
}
function showdetails(event){
    console.log(event);
    console.log(event.lastElementChild.textContent);
}

