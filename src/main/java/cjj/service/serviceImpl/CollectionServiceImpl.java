package cjj.service.serviceImpl;

import cjj.common.Result;
import cjj.dao.CollectionMapper;
import cjj.dao.CommentMapper;
import cjj.entity.BlogContent;
import cjj.service.CollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Resource
    CollectionMapper collectionMapper;

    @Override
    public Result sumCollection(String username) {
        int i = collectionMapper.sumCollection(username);
        return Result.success(200,"",i);
    }

    @Override
    public Result getMyCol(int userId) {
        List<BlogContent> list=collectionMapper.getMyCol(userId);
        if(list.size() == 0){
            return Result.fail(303,"没有收藏的文章",null);
        }
        return Result.success(200,"",list);
    }
}
