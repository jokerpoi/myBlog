package com.poi.controller;

import com.poi.entity.*;
import com.poi.service.CommentService;
import com.poi.service.ContentService;
import com.poi.service.UserService;
import com.poi.service.UserUtilPageTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping("/userUtil")
public class UserUtilPageController {
    private static final Logger logger = LoggerFactory.getLogger(UserUtilPageController.class);
    @Autowired
    private ContentService contentService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserUtilPageTableService userUtilPageTableService;
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/userUtil")
    public String userUtilPage(){
        logger.info("进入userUtilPage页面");
        return "userUtilpage";
    }

    @RequestMapping(value = "/addBlogPage")
    public String addBlogPage(){
        logger.info("进入addBlogPage");
        return "addBlog";
    }

    @RequestMapping(value = "/updateById/{blog}")
    public void updateBlog(@PathVariable(value = "blog") Contents contents){
        logger.info("updateBlog ID=" + contents.getCId());
        contentService.updateContents(contents);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteById/{id}")
    public void deleteBlogById(@PathVariable(value = "id")int id){
        logger.info("删除blogID= "+id);
        contentService.deleteBlog(id);
        commentService.removeCommentsByBlogId(id);
    }

    @ResponseBody
    @RequestMapping(value = "/findUserUtilPage")
    public DataGrid findUserUtilPage(int limit, int offset, HttpServletRequest request){
        logger.info("进入findUserUtilPage");
        User user = (User) request.getSession().getAttribute("user");
        if(null != user){
            return userUtilPageTableService.fintListByUserIdInPage(limit,offset,user.getUid());
        }else {
            return new DataGrid();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/showJson",method = RequestMethod.POST)
    public String showJson( String title,String contents,String cId){
        Contents content = new Contents();
        int id = Integer.parseInt(cId);
        content.setCId(id);
        content.setTitle(title);
        content.setContents(contents);
        logger.info("Object: "+content.toString());
        updateBlog(content);
        return "main";
    }

    @ResponseBody
    @RequestMapping(value = "/addBlog")
    public Contents addBlog(String title,String contents,String authorId){
        Contents blog = new Contents();
        int author = Integer.parseInt(authorId);
        blog.setAuthorId(author);

        logger.info("添加新Blog："+blog.toString());
        if(null!=title && !title.isEmpty() && null != contents && !contents.isEmpty()){
            blog.setTitle(title);
            blog.setContents(contents);
            Contents info = contentService.addContents(blog);
            logger.info("添加完成，结果为："+info.toString());
            return info;
        }else{
            return blog;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/changeAllowComment/{id}")
    public void changeAllowComment(@PathVariable(value = "id") int id){
        logger.info("changeAllowComment+ID: "+id);
        Contents blog = contentService.findContentById(id);
        int allowFlag = blog.getAllowComment();
        allowFlag = (allowFlag == 0)?1:0;
        blog.setAllowComment(allowFlag);
        logger.info("allowCommentFlag="+allowFlag);
        contentService.updateContents(blog);
    }

    @RequestMapping(value = "/settingPage")
    public String seettingPage(){
        logger.info("settingPage");
        return "settingPage";
    }

    @ResponseBody
    @RequestMapping(value = "/addComments")
    public Comments addNewComments(Comments comments,HttpServletRequest request){
        logger.info("tian jia ping lun,Comments: "+ comments.toString());
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return new Comments();
        }
        logger.info("User: " + user.toString());
        comments.setAuthorId(user.getUid());
        int total = commentService.findListCommentsByBlogId(comments.getBlogId()).size()+1;
        comments.setTotal(total);
        Comments result = commentService.addNewComments(comments);
        logger.info("result: " + result.getCmId());
        Contents blog = contentService.findContentById(comments.getBlogId());
        blog.setCommentsNum(total);
        contentService.updateContents(blog);
        return result;
//        return new Comments();
    }

    @ResponseBody
    @RequestMapping(value = "/showCommentsPageByBlogId")
    public DataGrid<CommentsPage> showPageComments(Comments comments,int limit,int offset){
        logger.info("显示评论列表: "+comments.toString()+"\n limit: "+limit + " offset : "+offset);
        DataGrid<CommentsPage> dataGrid = new DataGrid<>();
        dataGrid = commentService.findPageByBlogId(comments,offset,limit);
        return dataGrid;
    }

    @ResponseBody
    @RequestMapping(value = "/findListByBlogId/{blogId}")
    public List<CommentsPage> findListByBlogId(@PathVariable(value = "blogId") int blogId){
        logger.info("findListByBlogId: blogId : " + blogId);
        return commentService.findListCommentsByBlogId(blogId);
    }

    @ResponseBody
    @RequestMapping(value = "/findListByFatherId/{fatherId}")
    public List<CommentsPage> findListByFatherId(@PathVariable(value = "fatherId") int blogId){
        logger.info("findListByFatherId: fatherId : " + blogId);
        return commentService.findListCommentsByFatherId(blogId);
    }
}
