$(function () {
    $(".navbar-nav").find(".active").removeClass("active");
    var pattern = 'a[href*="' + window.location.pathname + '"]';
    $(pattern).addClass("active");
    //$(this).addClass("active");
    //alert("AAAAAAAA!!!");

})