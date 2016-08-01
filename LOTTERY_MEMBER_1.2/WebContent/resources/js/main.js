$(function() {
    $(".left-nav").find("dl").each(function() {
        $(this).click(function() {
            $(this).addClass("active").siblings().removeClass("active");
        });
    }); 

    $("#browseBtn").bind("click",function(event){
        $("#browseBox").fadeIn(300);
        event.stopPropagation();    //  阻止事件冒泡
        return false;
    });

    $(document).click(function() {
        $("#browseBox").fadeOut(300);
    });

    $(".typcn-phone-outline").parent().click(function() {
        alert("123");
    });

    $(".table-header-box1").find(".typcn").click(function() {
        $(this).siblings("p").fadeIn(300);
    });
})


function alerts(title, contant, type){
    if (type) {
        swal({
            title: title,
            text: contant,
            type : type
            });
    } else {
        swal({
              title: title,
              text: contant
        });
    }
    
}