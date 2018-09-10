/***
 * blog页面js
 */

var commentText = $("#comment");
var commentList = $("#commentList");


    $(function () {
        $.ajax({
            type:"GET",
            url: "/mainPage/getContent",
            error:function (data) {
                alert("Error!!");
            },
            success:function (data) {
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
            initCommentList();
        }
    }

    
    function initCommentList() {

    }
