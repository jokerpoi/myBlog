
var eyeFlag = false;

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
    var eventVal = onEvent.value;
    var eventId = onEvent.id;
    switch (eventId){
        case "password-label"://输入密码框
            checkPasswordInput(eventVal);
            break;
        case "password-label2":
            checkRePassword(eventVal);
            break;
        case "email-label":
            checkEmail(eventVal);
            break;
        case "user-name-label":
            checkUsername(eventVal);
            break;
    }
}

//验证用户名
function checkUsername(val) {
    var flag = true;
    var req = /^[a-zA-Z][0-9a-zA-Z]{4,10}/;
    if(req.test(val)){
        $("#user-name-info").css("color","#3e8f3e");
    }else{
        $("#user-name-info").css("display","block");
        $("#user-name-info").css("color","#b92c28");
        flag = false;
    }
    return flag;
}

//验证密码输入
function checkPasswordInput(passwordVal) {
    var flag = true;
    $("#password-ul").css("display","block");
    if(8 <= passwordVal.length && passwordVal.length <=16 ){
        $("#password-size-info").css("color","#3e8f3e");
    }else{
        $("#password-size-info").css("color","#b92c28");
        flag = false;
    }
    var reg = /(?!^(\d+|[a-zA-Z]+|[~!@#$%^&*?]+)$)^[\w~!@#$%^&*?]{8,16}$/;
    var regFlag = reg.test(passwordVal);
    if(regFlag){
        $("#password-type-info").css("color","#3e8f3e");
    }else{
        $("#password-type-info").css("color","#b92c28");
        flag = false;
    }

    return flag;
}

function checkEmail(val) {
    var flag = true;
    var req = /[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?/;
    if(req.test(val)){
        $("#email-info").css("display","none");
    }else{
        flag = false;
        $("#email-info").css("display","block");
        $("#email-info").css("color","#b92c28");
    }
    return flag;
}

function checkRePassword(val) {
    var flag = true;
    var passwordVal = $("#password-label").val();
    if(passwordVal != val){
        flag = false;
        $("#re-password-info").css("display","block");
        $("#re-password-info").css("color","#b92c28");
    }else{
        $("#re-password-info").css("display","none");
    }
    return flag;
}