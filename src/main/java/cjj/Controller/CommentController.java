package cjj.Controller;

import cjj.common.Result;
import cjj.dao.CommentMapper;
import cjj.entity.Comment;
import cjj.service.CommentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/pro/comment")
public class CommentController {
    @Resource
    CommentMapper commentMapper;

    @Resource
    CommentService commentService;


    @PostMapping("/sumComment")
    public Result sumComment(String username){
        int i = commentMapper.sumComment(username);
        return Result.success(200,"",i);
    }

    @PostMapping("/addComment")
    public Result addComment(int userId,String comment,int contentId){
        commentService.addComment(userId,comment,contentId);
        return Result.success(200,"添加成功",null);
    }

    @PostMapping("/getComments")
    public Result getComments(int contentId){
        return commentService.getComments(contentId);
    }

}
