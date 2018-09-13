
var updateFlag = false;
var ue = UE.getEditor("editor");
var contentsData;

$(function () {
    // initTextArea();
    $.ajax({
        type:"GET",
        url: "/mainPage/getContent",
        async: false,
        error:function (data) {
            alert("data is empty");
        },
        success:function (data) {
            if(null != data.cid && undefined != data.cid){
                contentsData = data;
                updateFlag = true;
                initContent(contentsData);
            }
        }
    });
});

function initTextArea() {
    ue = UE.getEditor("editor");
}

function initContent(data) {
    // ue.setContent("asdeeeeee");
    var title = $("#titleText");
    title.attr("value",data.title);
    var contentsText = data.contents;
    // var contentsText = "<p>dddddddd</p>";
    ue.addListener("ready",function () {
        ue.setContent(contentsText);
    });
}

//提交按钮点击事件
function commitContent() {
    var titleStr = $("#titleText").val();
    var contentText = ue.getContent();
    var localUser = initNavAdmin().uid;
    if(!updateFlag){
        if (null == titleStr || "" == titleStr) {
            alert("标题为空！！");
        }else if (null == contentText || "" == contentText) {
            alert("内容为空!!");
        }else{
            if (null == titleStr || "" == titleStr) {
                alert("标题为空！！");

            }else{
                $.ajax({
                    url: "/userUtil/addBlog?title="+titleStr+"&contents="+contentText+"&authorId="+localUser,
                    type: "post",
                    async:false,
                    error: function (data,msg) {
                        alert("添加失败！！！！"+msg.errorMsg);
                    },
                    success: function (data) {
                        alert("修改成功！！");
                        window.location.href = "/mainPage/index";
                    }
                });
            }
        }
    }else{
        var cID = contentsData.cid;
        $.ajax({
            url: "/userUtil/showJson?title="+titleStr+"&contents="+contentText+"&cId="+cID,
            type: "post",
            async: "false",
            error: function (data) {
                alert("添加失败！！！！");
            },
            success: function (data) {
                alert("修改成功！！");
                window.location.href = "/mainPage/blog/"+cID;
            }
        });
    }
}


function showErroeAlert(reason,detil) {
    var msg = "";
    if(reason == "unsupport-file-type"){
        msg = "UnsupportFileType" + detil;
    }else {
        console.log("error uploading file",reason,detil);
    }
    $("<div class='alert'><button type='button' class='close' data-dismiss='alert'>X</button>"+
        "<strong>File Upload Error</strong>"+msg+"</div>").prependTo("#alerts");
}