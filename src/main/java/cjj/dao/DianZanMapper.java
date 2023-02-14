package cjj.dao;

import org.apache.ibatis.annotations.Param;

public interface DianZanMapper {
    int sumStart(@Param("username") String username);

    Object isDianZan(@Param("userId")int userId, @Param("contentId")int contentId);


    void addDianZan(@Param("userId")int userId, @Param("contentId")int contentId,
                    @Param("status")int status);


    void updateDianZan(@Param("userId")int userId, @Param("contentId")int contentId,
                       @Param("status")int status);
}
