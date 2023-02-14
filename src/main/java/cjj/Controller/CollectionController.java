package cjj.Controller;

import cjj.common.Result;
import cjj.dao.CollectionMapper;
import cjj.service.CollectionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin
@RequestMapping("/pro/collection")
public class CollectionController {
    @Resource
    CollectionMapper collectionMapper;

    @Resource
    CollectionService service;

    @PostMapping("/sumCollection")
    public Result sumCollection(String username){
        int i = collectionMapper.sumCollection(username);
        return Result.success(200,"",i);
    }

    @PostMapping("/addCol")
    public Result addCol(int userId,int contentId){
        int i = collectionMapper.insertCollect(userId,contentId);
        return Result.success(200,"添加成功",i);
    }

    @PostMapping("/delCol")
    public Result delCol(int userId,int contentId){
        Integer i = collectionMapper.delCollect(userId,contentId);
        return Result.success(200,"添加成功",i);
    }

    @PostMapping("/isCol")
    public Result isCol(int userId,int contentId){
        Object i = collectionMapper.isCol(userId,contentId);
        if(null == i){
            return Result.success(202,"没有收藏",null);
        }else {
            return Result.success(200,"已经收藏",null);
        }
    }

    @PostMapping("/getMyCol")
    public Result getMyCol(int userId){
        return service.getMyCol(userId);
    }
}
