package cjj.service.serviceImpl;

import cjj.common.OSSCommon;
import cjj.common.RedisUtil;
import cjj.common.Result;
import cjj.dao.BlogContentMapper;
import cjj.entity.BlogContent;
import cjj.entity.BlogUser;
import cjj.service.BlogContentService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BlogContentServiceImpl implements BlogContentService {
    @Resource
    BlogContentMapper blogContentMapper;

    @Resource
    OSSCommon ossCommon;

    @Resource
    RedisTemplate<String,Object> redisTemplate;

    @Resource
    RedisUtil redisUtil;


    @Override
    public Result getContents(int pageNo) {
        int pageSize = 5;
        int filterSize = pageSize * (pageNo-1);
        List<BlogContent> contents = blogContentMapper.getContents(filterSize);
        return Result.success(200,"",contents);
    }



    @Transactional()
    @Override
    public Result addOne(BlogContent blogContent) {
        int i = blogContentMapper.addOne(blogContent);
        if(i == 0){
            return Result.fail(606,"添加失败","数据过大或格式错误");
        }else {
            redisUtil.del("contents");
            List<BlogContent> contents1 = blogContentMapper.getContents(1);
            for (BlogContent b: contents1) {
                redisTemplate.opsForZSet().add("contents",b,b.getPraise());
            }
            redisUtil.expire("contents",3600*24);
            return Result.success(200,"添加成功","");
        }

    }

    @Override
    public int sum() {
        return blogContentMapper.sum();
    }


    @Override
    public Result getMyBlogs(String username) {

        List<Map<String, String>> myBlogs = blogContentMapper.getMyBlogs(username);
        int i = blogContentMapper.MySum(username);
        HashMap<String,Object> map = new HashMap<>();
        map.put("contents",myBlogs);
        map.put("mysum",i);
        return Result.success(200,"",map);
    }

    @Override
    public Result updatePraise(int contentId) {
        int i = blogContentMapper.updatePraise(contentId);
        if(i!= 1){
            return Result.fail(808,"点赞失败","");
        }
        return Result.success(200,"点赞成功","");
    }

    @Override
    public Result updateUnPraise(int contentId) {
        int i = blogContentMapper.updateUnPraise(contentId);
        if(i!= 1){
            return Result.fail(808,"踩失败","");
        }
        return Result.success(200,"踩成功","");
    }

    @Override
    public Result updateLook(int contentId) {
        int i = blogContentMapper.updateLook(contentId);
        if(i!= 1){
            return Result.fail(808,"访问失败","");
        }
        return Result.success(200,"访问成功","");
    }

    @Override
    public Result sumPraise(String username) {
        Object i = blogContentMapper.sumPraise(username);
        if(null == i){
            i = 0;
        }
        return Result.success(200,"",i);
    }

    @Override
    public Result uploadContentImg(MultipartFile file) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String date = format.format(new Date());
        String url = "vueblog/contentImg/" +  date + UUID.randomUUID().toString().replace("-","") +  file.getOriginalFilename();
        try {
            url = ossCommon.ossUpload(file,url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(200,"上传成功",url);
    }

    @Override
    public Result getRecentBlogs(String username) {
        List<BlogContent> contentIdList =blogContentMapper.getRecentBlogs(username);

        return Result.success(200,"",contentIdList);
    }

    @Override
    public Result search(String msg,int page) {
        List<BlogContent> list = blogContentMapper.search(msg,(page-1)*5);
        if(list.size() == 0){
            return Result.fail(909,"没有与该关键字匹配的信息",null);
        }
        return Result.success(200,"",list);
    }

    @Override
    public Result decPraise(int contentId) {
        int i = blogContentMapper.decPraise(contentId);
        if(i!= 1){
            return Result.fail(808,"","");
        }
        return Result.success(200,"","");
    }

    @Override
    public Result decUnPraise(int contentId) {
        int i = blogContentMapper.decUnPraise(contentId);
        if(i!= 1){
            return Result.fail(808,"","");
        }
        return Result.success(200,"","");
    }

    @Override
    public Result decLook(int contentId) {
        int i = blogContentMapper.decLook(contentId);
        if(i!= 1){
            return Result.fail(808,"","");
        }
        return Result.success(200,"","");
    }


}
