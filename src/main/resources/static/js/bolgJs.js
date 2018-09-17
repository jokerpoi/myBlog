/***
 * blog页面js
 */

var commentText = $("#comment");
var commentList = $("#commentList");
var blogId = 0;


$(function () {
    $.ajax({
        type:"GET",
        url: "/mainPage/getContent",
        error:function (data) {
            alert("Error!!");
            },
        success:function (data) {
            blogId = data.cid;
            initBlogPage(data);
            }
        });
});

    function initBlogPage(contents) {
        var createTime = document.getElementById("createTime");
        var windowTitle = $("#window-title");
        var title = $("#title");
        var modifiedTime = document.getElementById("modifiedTime");
        var pragh = $("#pragh");
        var author = document.getElementById("author");
        var hits = document.getElementById("hits");

        windowTitle.html(contents.title);
        title.html(contents.title);
        modifiedTime.appendChild(document.createTextNode(changeDate(contents.modified)));
        createTime.appendChild(document.createTextNode(changeDate(contents.create)));
        author.appendChild(document.createTextNode(getAuthorById(contents.authorId)));
        hits.appendChild(document.createTextNode(contents.hits));
        pragh.html(contents.contents);
        // pragh.html("<h1>ssddiidofdfdf</h1>");
        var commentFlag = contents.allowComment;
        if(commentFlag == "1"){
            $("#comment").removeAttr("disabled");
        }else{
            $("#comment").attr("disabled","disabled");
        }

        initCommentList();

    }

    
    function initCommentList() {

        var commentNode = document.getElementById("commentList");

        if(blogId != 0 && blogId != null){
            $.ajax({
                url: "/userUtil/findListByBlogId/"+blogId,
                async: false,
                success: function (data) {
                    for(var i=0;i<data.length;i++){
                        var div = initCommentNode(commentNode,data[i]);
                        initChildReplay(div,data[i].cmId);
                    }
                }
            })
        }
    }

    function initChildReplay(node,fatherId) {

        $.ajax({
            url: "/userUtil/findListByFatherId/"+fatherId,
            async: false,
            success: function (data) {
                if(data != ""){
                    for(var i=0;i<data.length;i++){
                        var div = initCommentNode(node,data[i]);
                        initChildReplay(div,data[i].cmId);
                    }
                }
            }
        })
    }

    function initCommentNode(dom,data) {
        //评论标题
        var depthL = document.createElement("label");
        depthL.appendChild(document.createTextNode(data.total+"楼"));
        depthL.setAttribute("class","col-md-1");
        var user = document.createElement("label");
        user.appendChild(document.createTextNode(data.author));
        user.setAttribute("class","col-md-1");
        var timeCm = document.createElement("label");
        timeCm.setAttribute("class","col-md-4");
        timeCm.appendChild(document.createTextNode(changeDate(data.cm_create)));
        //内容
        var commentsVal = document.createElement("div");
        commentsVal.setAttribute("class","col-md-12");
        commentsVal.setAttribute("style","height:150px;text-overflow:ellipsis;overflow:hidden;");
        commentsVal.innerHTML = data.comment;
        //回复点击后文字编辑框
        var replayTextDiv = document.createElement("div");
        //回复点击按钮
        var replayBtn = document.createElement("a");
        replayBtn.setAttribute("class","col-md-1 col-md-offset-8");
        replayBtn.setAttribute("onclick",clickReplay(replayTextDiv,data.cmId));
        replayBtn.appendChild(document.createTextNode("回复"));
        //下划线
        var line = document.createElement("div");
        line.setAttribute("style","background-color: #5e5e5e;height: 1px;");
        line.setAttribute("class","col-md-12");

        dom.setAttribute("style","border: 1px solid rgb(204, 204, 204);");
        dom.appendChild(depthL);
        dom.appendChild(user);
        dom.appendChild(timeCm);
        dom.appendChild(commentsVal);
        dom.appendChild(replayTextDiv);
        dom.appendChild(replayBtn);
        dom.appendChild(line);

        return replayTextDiv;
    }
    
    function clickReplay(dom,father) {

        alert(dom);
        var textArea = document.createElement("textarea");
        textArea.setAttribute("class","col-md-8 col-md-offset-2");
        textArea.setAttribute("style","height: 100px; border-collapse: separate; border: 1px solid rgb(204, 204, 204);");
        textArea.setAttribute("minlength","8");

        var replayBtn = document.createElement("btn");
        replayBtn.setAttribute("class","col-md-2 col-md-offset-8 btn btn-primary");
        replayBtn.setAttribute("type","submit");
        replayBtn.setAttribute("onclick","");

    }

    function onclickSubmitReplay() {
        var replatText = $("#comment").val();

        createReplayArea(blogId,replatText);
    }

    function createReplayArea(father,text) {
        $.ajax({
            url: "/userUtil/addComments?comment=" + text+"&fatherId="+father+"&blogId="+blogId,
            async: false,
            success: function (data) {

            }
        });
    }

