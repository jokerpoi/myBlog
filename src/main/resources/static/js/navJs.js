/***
 * 导航栏js
 */

$(function () {
    initNavAdmin();
});

function getAuthorById(id) {
    var author;
    $.ajax({
        type:"get",
        url:"/mainPage/getUserById/"+id,
        async: false,
        success:function (data) {
            author = data.username;
        }
    });
    return author;
}

function changeDate(date) {
    var dateResult = new Date(date);
    var modified = dateResult.getFullYear()+"-"+(dateResult.getMonth()+1)+"-"+dateResult.getDate()+
        " "+dateResult.getHours()+":"+dateResult.getMinutes()+":"+dateResult.getSeconds();
    return modified;
}

function initNavAdmin() {
    var user;
    $.ajax({
        url: "/userPage/getSessionUser",
        async: false,
        success: function (data) {
            $("#admin").html(data.username);
            $("#rightAdmin").val(data.username);
            user = data;
        }
    })
    return user;
}