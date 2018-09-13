


function registerUser() {
    var uname = $("#user-name-label").val();
    var password = $("#password-label").val();
    var repassword = $("#password-label2").val();
    var email = $("#email-label").val();

    if(checkUsername(uname) && checkPasswordInput(password) && checkRePassword(repassword) && checkEmail(email)){
        $.ajax({
            url:"/userPage/addUser?username="+uname+"&password="+password+"&email="+email,
            async:false,
            success: function (data) {
                alert("注册成功！");
                window.location.href = "/userPage/loginPage";
            }
        });
    }
}