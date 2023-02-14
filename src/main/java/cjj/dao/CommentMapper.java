package cjj.dao;

import cjj.entity.Comment;
import cjj.vo.CommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    int insertComment(@Param("userId") int userId, @Param("contentId")int contentId,
                      @Param("comment") String comment);

    int sumComment(@Param("username")String username);

    void addComment(@Param("comment")Comment comment);

    List<CommentVO> getComments(@Param("contentId")int contentId);
}
