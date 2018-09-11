
var eyeFlag = false;

$(function () {
    initUserValue();

    $("#password-label").focus(onInputCheck(this));

});

//初始化用户数据
function initUserValue() {

}

function passwordEyeClick(index) {
    // event.stopPropagation();
    if(eyeFlag==false){
        $("#password-label").attr("type","text");
        $("#password-span").attr("class","glyphicon glyphicon-eye-open input-group-addon");
        $("#password-label2").attr("type","text");
        $("#password-span2").attr("class","glyphicon glyphicon-eye-open input-group-addon");
        eyeFlag = true;
    }else {
        $("#password-label").attr("type","password");
        $("#password-span").removeClass("glyphicon glyphicon-eye-open");
        $("#password-span").addClass("glyphicon glyphicon-eye-close");
        $("#password-label2").attr("type","password");
        $("#password-span2").removeClass("glyphicon glyphicon-eye-open");
        $("#password-span2").addClass("glyphicon glyphicon-eye-close");
        eyeFlag = false;
    }
}

function onInputCheck(onEvent) {
       onEvent.css({
           "border-color":"#b92c28"
       });
}

//验证密码输入
function checkPasswordInput() {
    var flag = false;

    return flag;
}