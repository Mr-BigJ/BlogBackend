package cjj.dao;

import cjj.entity.BlogContent;
import cjj.entity.BlogUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogUserMapper {
    BlogUser login(String username,String password);

    int register(BlogUser user);

    int registerTest(BlogUser user);

    void insertToken(BlogUser user);

    void deleteToken(long tokenId);

    int updateToken(@Param("user") BlogUser user);

    String getToken(String token);

    BlogUser getByUserId(@Param("username")String username);

    String getUserName(@Param("id")int  id);

    void updateAvatar(@Param("url") String url, @Param("userId") String userId);

    BlogUser sumLook(@Param("username")String username);

    void addUser(@Param("bloguser") BlogUser blogUser);

    List<BlogUser> allUser();

    List<BlogUser> search(@Param("msg")String msg);

    String getTokenByUsername(@Param("username")String username);

    BlogUser hasSameUsername(@Param("username")String username);
}
