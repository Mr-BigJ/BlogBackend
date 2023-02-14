package cjj.Controller;

import cjj.common.Result;
import cjj.entity.BlogContent;
import cjj.service.BlogContentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/pro/blog")
public class BlogContentController {
    @Resource
    BlogContentService service;


    @PostMapping("/content")
    public Result getContents(){
        return service.getContents();
    }



    @GetMapping("/sum")
    public Result sum(){
        int sum = service.sum();
        return Result.success(200,"",sum);
    }

    @PostMapping("/addOne")
    public Result addOne(String content,int userId,String title,String description,String username){
        BlogContent blogContent = new BlogContent();
        blogContent.setUsername(username);
        blogContent.setUserId(userId);
        blogContent.setUnpraise(0);
        blogContent.setTitle(title);
        blogContent.setStatus(1);
        blogContent.setPraise(0);
        blogContent.setLook(0);
        blogContent.setDescription(description);
        String DateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormat);
        Date date = new Date();
        blogContent.setCreated(simpleDateFormat.format(date));
        blogContent.setContent(content);
        return service.addOne(blogContent);
    }

    @PostMapping("/getMyBlogs")
    public Result getMyBlogs(String username){
        return service.getMyBlogs(username);
    }

    @PostMapping("/updatePraise")
    public Result updatePraise(int contentId){
        return service.updatePraise(contentId);
    }

    @PostMapping("/decPraise")
    public Result decPraise(int contentId){
        return service.decPraise(contentId);
    }

    @PostMapping("/decUnPraise")
    public Result decUnPraise(int contentId){
        return service.decUnPraise(contentId);
    }

    @PostMapping("/decLook")
    public Result decLook(int contentId){
        return service.decLook(contentId);
    }

    @PostMapping("/updateUnPraise")
    public Result updateUnPraise(int contentId){
        return service.updateUnPraise(contentId);
    }

    @PostMapping("/updateLook")
    public Result updateLook(int contentId){
        return service.updateLook(contentId);
    }

    @PostMapping("/sumPraise")
    public Result sumPraise(String username){
        return service.sumPraise(username);
    }

    @PostMapping("/uploadContentImg")
    public Result uploadContentImg(MultipartFile file){
        return service.uploadContentImg(file);
    }

    @PostMapping("/getRecentBlogs")
    public Result getRecentBlogs(String username){
        return service.getRecentBlogs(username);
    }

    @PostMapping("/search")
    public Result search(String searchInfo){
        return service.search(searchInfo);
    }


}
