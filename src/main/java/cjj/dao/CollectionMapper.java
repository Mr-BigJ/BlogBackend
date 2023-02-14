package cjj.dao;

import cjj.entity.BlogContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectionMapper {
    int insertCollect(@Param("userId") int userId, @Param("contentId") int contentId);

    int sumCollection(@Param("username") String username);

    int delCollect(@Param("userId") int userId, @Param("contentId") int contentId);

    Object isCol(@Param("userId") int userId, @Param("contentId") int contentId);

    List<BlogContent> getMyCol(@Param("userId")int userId);
}
