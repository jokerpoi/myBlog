

$(function () {
    initUserValue();

});

//初始化用户数据
function initUserValue() {

}

function submitChangeUser() {
    var userVal = $("#user-name-label").val();
    var passwordVal = $("#password-label").val();
    var rePasswordVal = $("#password-label2").val();
    var emailVal = $("#email-label").val();
    if(checkUsername(userVal) && checkPasswordInput(passwordVal) && checkRePassword(rePasswordVal) && checkEmail(emailVal)){
        $.ajax({
            url:"/userPage/addUser?username="+userVal+"&password="+passwordVal+"&email="+emailVal,
            async:false
        });
    }else{
        alert("信息错误");
    }
}


