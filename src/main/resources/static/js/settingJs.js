

$(function () {
    initUserValue();

});

//初始化用户数据
function initUserValue() {
    $.ajax({
        url: "/userPage/getSessionUser",
        async: false,
        success: function (data) {
            $("#user-name-label").val(data.username);
            $("#password-label").val(data.password);
            $("#password-label2").val(data.password);
            $("#email-label").val(data.email);
            var createTime = data.created;
            $("#user-create-label").html(changeDate(createTime));
        }
    })
}

function submitChangeUser() {
    var userVal = $("#user-name-label").val();
    var passwordVal = $("#password-label").val();
    var rePasswordVal = $("#password-label2").val();
    var emailVal = $("#email-label").val();
    if(checkUsername(userVal) && checkPasswordInput(passwordVal) && checkRePassword(rePasswordVal) && checkEmail(emailVal)){
        $.ajax({
            url:"/userPage/updateUser?username="+userVal+"&password="+passwordVal+"&email="+emailVal,
            async:false,
            success: function (data) {
                alert("修改成功！");
            }
        });
    }else{
        alert("信息错误");
    }
}


