package cjj.dao;

import cjj.entity.BlogContent;
import cjj.entity.BlogUser;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface BlogContentMapper {

    List<BlogContent> getContents(int filterSize);


    int addOne(@Param("blogcontent")BlogContent blogContent);
    int addContent(@Param("blogcontent")BlogContent blogContent);
    int sum();

    //“我的”相关
    List<Map<String,String>> getMyBlogs(@Param("username")String username);
    int MySum(@Param("username") String username);

    //更新点赞踩和访问
    int updateLook(@Param("id")int id);
    int updatePraise(@Param("id")int id);
    int updateUnPraise(@Param("id")int id);

    int sumPraise(@Param("username")String username);


    List<BlogContent> getRecentBlogs(@Param("username")String username);

    BlogContent getById(@Param("contentId")int contentId);

    List<BlogContent> search(@Param("msg")String msg,@Param("page")int page);


    int decPraise(@Param("contentId")int contentId);

    int decUnPraise(@Param("contentId")int contentId);

    int decLook(@Param("contentId")int contentId);
}
