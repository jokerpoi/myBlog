/***
 * blog页面js
 */

var commentText = $("#comment");
var commentList = $("#commentList");
var blogId = 0;
var commentFlag = 1;

var offset = 0;
var limit = 3;


$(function () {
    $.ajax({
        type:"GET",
        url: "/mainPage/getContent",
        error:function (data) {
            alert("Error!!");
            },
        success:function (data) {
            blogId = data.cid;
            commentFlag = data.allowComment;
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

        if(commentFlag == "1"){
            $("#comment").removeAttr("disabled");
        }else{
            $("#comment").attr("disabled","disabled");
        }

        var grid = initCommentList(offset,limit);
        $("#commentPageLabel").html("显示第"+(offset+1)+"到第"+(grid.rows.length+offset)+"条记录，总共"+grid.total+"条记录");
    }

    //上一页方法
    function onclickPrv() {
        if(offset != 0){
            offset = offset - limit;
            $("#commentList").html("");
            var grid = initCommentList(offset,limit);
            $("#commentPageLabel").html("显示第"+(offset+1)+"到第"+(grid.rows.length+offset)+"条记录，总共"+grid.total+"条记录");
        }
    }
    //下一页方法
    function onclickNext() {
        var grid = initCommentList(offset,limit);
        if(offset+limit <= grid.total){
            offset = offset+limit;
            $("#commentList").html("");
            grid = initCommentList(offset,limit);
            $("#commentPageLabel").html("显示第"+(offset+1)+"到第"+(grid.rows.length+offset)+"条记录，总共"+grid.total+"条记录");
        }
    }
    //首页方法
    function onclickFirst() {
        offset = 0;
        $("#commentList").html("");
        var grid = initCommentList(offset,limit);
        $("#commentPageLabel").html("显示第"+(offset+1)+"到第"+(grid.rows.length+offset)+"条记录，总共"+grid.total+"条记录");
    }
    //尾页方法
function onclickLast() {
    var grid = initCommentList(offset,limit);
    page = grid.total/limit;
    offset = offset + page*limit;
    $("#commentList").html("");
    grid = initCommentList(offset,limit);
    $("#commentPageLabel").html("显示第"+(offset+1)+"到第"+(grid.rows.length+offset)+"条记录，总共"+grid.total+"条记录");
}
    
    function initCommentList(offset,limit) {

        var commentNode = document.getElementById("commentList");
        var dataGrid;

        if(blogId != 0 && blogId != null){
            $.ajax({
                url: "/userUtil/showCommentsPageByBlogId?blogId="+blogId+"&offset="+offset+"&limit="+limit,
                async: false,
                success: function (data) {
                    var row = data.rows;
                    dataGrid = data;
                    for(var i=0;i<row.length;i++){
                        var div = initCommentNode(commentNode,row[i]);
                        initChildReplay(div,row[i].cmId);
                    }
                }
            });
        }
        return dataGrid;
    }

    function initChildReplay(node,fatherId) {

        $.ajax({
            url: "/userUtil/findListByFatherId/"+fatherId,
            async: false,
            success: function (data) {
                if(data != "" && data != null){
                    for(var i=0;i<data.length;i++){
                        var div = initCommentNode(node,data[i]);
                        initChildReplay(div,data[i].cmId);
                    }
                }
            }
        })
    }

    function initCommentNode(dom,data) {

        //外框div
        var divBorder = document.createElement("div");

        //评论标题
        var labelLine = document.createElement("div");
        labelLine.setAttribute("class","col-md-12");

        var depthL = document.createElement("label");
        depthL.appendChild(document.createTextNode(data.total+"楼"));
        depthL.setAttribute("class","col-md-1");
        var user = document.createElement("label");
        user.appendChild(document.createTextNode(data.author));
        user.setAttribute("class","col-md-1");
        var timeCm = document.createElement("label");
        timeCm.setAttribute("class","col-md-4");
        timeCm.appendChild(document.createTextNode(changeDate(data.cm_create)));

        labelLine.appendChild(depthL);
        labelLine.appendChild(user);
        labelLine.appendChild(timeCm);
        //内容
        var commentsVal = document.createElement("div");
        commentsVal.setAttribute("class","col-md-12");
        commentsVal.setAttribute("style","height:100px;text-overflow:ellipsis;overflow:hidden;");
        commentsVal.innerHTML = data.comment;
        //回复点击后文字编辑框
        var replayTextDiv = document.createElement("div");
        //回复点击按钮
        var replayBtn = document.createElement("a");
        replayBtn.setAttribute("id","replayBtn"+data.cmId);
        replayBtn.setAttribute("class","btn col-md-1 col-md-offset-8");
        replayBtn.onclick = function (ev) {
            clickReplay(replayTextDiv,data.cmId);
            replayBtn.setAttribute("style","display:none;");
        };
        replayBtn.appendChild(document.createTextNode("回复"));

        divBorder.appendChild(labelLine);

        divBorder.appendChild(commentsVal);
        divBorder.appendChild(replayTextDiv);
        if(commentFlag == 1){
            divBorder.appendChild(replayBtn);
        }

        divBorder.setAttribute("style","border: 1px solid #000;margin:3px;");
        divBorder.setAttribute("class","col-md-12");

        dom.appendChild(divBorder);

        return replayTextDiv;
    }
    
    function clickReplay(dom,father) {

        var labelTitle = document.createElement("label");
        labelTitle.setAttribute("class","col-md-3");
        labelTitle.appendChild(document.createTextNode("发表回复"));
        var textArea = document.createElement("textarea");
        textArea.setAttribute("class","col-md-8 col-md-offset-2");
        textArea.setAttribute("style","height: 100px; border-collapse: separate; border: 1px solid rgb(204, 204, 204);");
        textArea.setAttribute("minlength","8");

        var replayBtn = document.createElement("btn");
        replayBtn.setAttribute("class","col-md-2 col-md-offset-8 btn btn-primary");
        replayBtn.setAttribute("type","submit");
        replayBtn.appendChild(document.createTextNode("提交"));
        replayBtn.onclick = function (ev) {
            var text = textArea.value;
            if(text == ""){
                dom.innerHTML = "";
                $("#replayBtn"+father).attr("style","display:display");
            }else{
                createReplayArea(father,text);
            }
        };

        dom.appendChild(labelTitle);
        dom.appendChild(textArea);
        dom.appendChild(replayBtn);

    }

    function onclickSubmitReplay() {
        var replatText = $("#comment").val();
        if(replatText != ""){
            createReplayArea(0,replatText);
        }else{
            alert("未输入任何文字!");
        }

    }

    function createReplayArea(father,text) {
        $.ajax({
            url: "/userUtil/addComments?comment=" + text+"&fatherId="+father+"&blogId="+blogId,
            async: false,
            success: function (data) {
                alert("回复成功");
                window.location.replace(location.href);
            }
        });
    }

