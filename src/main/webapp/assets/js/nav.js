/**
    * Created by mingfei.net@gmail.com
    * 6/27/17 15:38
    * https://github.com/thu/jianshu/
    */
$(function () {
//            $.fn.bootstrapSwitch.defaults.size = 'mini';
    $("[name='night-mode']").bootstrapSwitch();
    $("[name='font-mode']").bootstrapSwitch();
    $('#nav-sign-up').click(function () {
        window.location.href = "sign_up.jsp";
    });
    $('#write').click(function () {
        window.location.href = 'write.jsp';
    });
});