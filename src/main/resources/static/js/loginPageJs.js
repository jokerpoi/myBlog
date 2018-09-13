



function clickLoginBtn() {

    var username = $("#user-val").val();
    var password = $("#password-val").val();
    $.ajax({
        url: "/userPage/login?username="+username+"&password="+password,
        async: false,
        error: function (data) {

        },
        success: function (data) {
            if(data == "nameNull"){
                $("#error-info").html("用户名不存在！");
                $("#error-info").css("display","block");
            }else if(data == "passwordError"){
                $("#error-info").css("display","block");
                $("#error-info").html("密码错误！");
            }else{
                alert(data);
                window.location.href = "/mainPage/index";
            }
        }
    });

}