package cjj.Controller;


import cjj.common.Result;
import cjj.dao.BlogUserMapper;
import cjj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/pro/user")
public class UserController {
    @Autowired
    UserService userService;

    @Resource
    BlogUserMapper userMapper;


    @PostMapping("/login")
    @ResponseBody
    public Result login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return userService.login(username,password);
    }

    @PostMapping("/register")
    @ResponseBody
    public Result register(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        return userService.register(username,password,email);
    }

    @PostMapping("/getToken")
    @ResponseBody
    public Result getToken(HttpServletRequest request){
        String token = request.getParameter("token");
        String re = userMapper.getToken(token);
        if(re == null ){
            return Result.fail(505,"验证失败","");
        }
        return Result.success(200,"",re);
    }

    @PostMapping("/uploadAvatar")
    @ResponseBody
    public Result uploadAvatar(MultipartFile file , String id){
        return userService.uploadAvatar(file,id);
    }

    @PostMapping("/sumLook")
    @ResponseBody
    public Result sumLook(String  username){
        return userService.sumLook(username);
    }

    @PostMapping("/getUserInfo")
    @ResponseBody
    public Result getUserInfo(String  username){
        return userService.getUserInfo(username);
    }

    @PostMapping("/allUser")
    @ResponseBody
    public Result allUser(){
        return userService.allUser();
    }

    @PostMapping("/search")
    public Result search(String searchInfo){
        return userService.search(searchInfo);
    }

    @PostMapping("/getRank")
    public Result getRank(String username){
        return userService.getRank(username);
    }
}
