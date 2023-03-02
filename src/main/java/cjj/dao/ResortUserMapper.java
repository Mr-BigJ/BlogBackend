package cjj.dao;


import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResortUserMapper {
    int  getAllUserId();




    void updateSumPraise(@Param("userId")int userId);




}
