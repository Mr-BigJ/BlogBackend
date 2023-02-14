package cjj.service.serviceImpl;

import cjj.common.Result;
import cjj.dao.CommentMapper;
import cjj.entity.Comment;
import cjj.service.CommentService;
import cjj.vo.CommentVO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    CommentMapper commentMapper;

    @Override
    public Result sumComment(String userId) {
        int i = commentMapper.sumComment(userId);
        return Result.success(200,"",i);
    }

    @Override
    public Result addComment(int userId, String comment, int contentId) {
        Comment comment1 = new Comment();
        comment1.setComment(comment);
        comment1.setContentId(contentId);
        comment1.setUserId(userId);
        String DateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormat);
        Date date = new Date();
        comment1.setCommentDate(simpleDateFormat.format(date));
        commentMapper.addComment(comment1);
        return Result.success(200,"",null);
    }

    @Override
    public Result getComments(int contentId) {
        List<CommentVO> list = commentMapper.getComments(contentId);
        if(list.size() == 0){
            return Result.fail(1001,"没有评论",null);
        }
        return Result.success(200,"",list);
    }
}
