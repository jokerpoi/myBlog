
$(function () {
    initTable().init();
});

function initTable() {
    var oTableInit = new Object();

    oTableInit.init = function () {
        $("#blogTable").bootstrapTable({
            url: "/userUtil/findUserUtilPage",
            method: "get",
            dataType: "json",
            cache: false,
            pagination: true,
            queryParams:oTableInit.queryParams,
            sidePagination: "server",
            search: true,
            pageNumber: 1,
            pageSize: 5,
            uniqueId: "cId",
            columns: [{
                field: "title",
                title: "标题"
            },{
                field: "author",
                title: "作者"
            },{
                field: "url",
                title: "文章路径",
                formatter:function (value,row,index) {
                    return "<a href='/mainPage/blog/"+row.cId+"'>/mainPage/blog/"+row.cId+"</a>";
                }
            },{
                field: "status",
                title: "文章状态"
            },{
                field: "type",
                title: "文章类型"
            },{
                field: "createTime",
                title: "创建时间",
                width: "100px"
            },{
                field: "modifiedTime",
                title: "更新时间",
                width: "100px"
            },{
                field: "hits",
                title: "点击量"
            },{
                field: "commentNum",
                title: "评论数量"
            },{
                field: "allowComment",
                title: "是否允许评论",
                width: "50px",
                formatter:function (value,row,index) {
                    var resultS = "";
                    if(row.allowComment){
                        resultS = "<input type='checkbox' id='radioComment' name='allowRadio' onclick='allowCommentBtn("+row.cId+")' checked>";
                    }else{
                        resultS = "<input type='checkbox' id='radioComment' name='allowRadio' onclick='allowCommentBtn("+row.cId+")'>";
                    }
                    return resultS;
                }
            },{
                field: "option",
                title: "编辑",
                width: "200px",
                formatter:function (value,row,index) {
                    return [
                        "<a class='btn btn-primary' onclick='clickSubAdmin("+row.cId+")'>审核</a>",
                        "<a class='btn btn-default' onclick='clickUpdate("+row.cId+")'>修改</a>",
                        "<a class='btn btn-danger' onclick='removeBlog("+row.cId+")'>删除</a>"
                    ].join(" ")
                }
            }]

        });
    };
    oTableInit.queryParams = function (params) {
        var temp = {
            limit : params.limit,
            offset : params.offset
        };
        return temp;
    };

    return oTableInit;
}

function clickUpdate(index) {
    $.ajax({
        url:"/mainPage/setAttributeCon/"+index,
        type:"get",
        success:function (data) {
            window.location.href = "/userUtil/addBlogPage";
        }
    })
}

//删除按钮点击事件
function removeBlog(index) {
    $.ajax({
        url: "/userUtil/deleteById/"+index,
        type: "post",
        async: false,
        success: function (data) {
            alert("删除成功");
            $("#blogTable").bootstrapTable("refresh");
        },
        error:function (data) {
            alert("删除失败!!");
        }
    })
}

//审核按钮点击事件
function clickSubAdmin(index) {

}

//允许评论点击事件
function allowCommentBtn(index) {
    alert(index);
}