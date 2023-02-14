package cjj.service.serviceImpl;

import cjj.common.Result;
import cjj.dao.DianZanMapper;
import cjj.service.DianZanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DianZanServiceImpl implements DianZanService {
    @Resource
    DianZanMapper dianZanMapper;

    @Override
    public Result sumStar(String username) {
        int i = dianZanMapper.sumStart(username);
        return Result.success(200,"",i);
    }

    @Override
    public Result isDianZan(int userId, int contentId) {
        Object o = dianZanMapper.isDianZan(userId,contentId);
        if(null == o || (int)o == 0){
            return Result.fail(505,"没有点赞","");
        }else if((int)o == 1){
            return Result.success(202,"已点赞","");
        }else {
            return Result.success(203,"已踩","");
        }
    }

    @Override
    public Result addDianZan(int userId, int contentId,int status) {
        Object dianZan = dianZanMapper.isDianZan(userId, contentId);
        if(null == dianZan){
            dianZanMapper.addDianZan(userId,contentId,status);
        }else {
            dianZanMapper.updateDianZan(userId,contentId,status);
        }

        return Result.success(200,"","");
    }


}
