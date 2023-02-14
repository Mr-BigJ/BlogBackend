package cjj.service.serviceImpl;
import cjj.common.JWT;
import cjj.common.OSSCommon;
import cjj.common.RedisUtil;
import cjj.common.Result;
import cjj.dao.BlogUserMapper;
import cjj.entity.BlogContent;
import cjj.entity.BlogUser;
import cjj.service.UserService;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserImpl implements UserService {

    @Resource
    BlogUserMapper userMapper;

    @Resource
    OSSCommon ossCommon;

    @Resource
    RedisUtil redisUtil;

    @Resource
    RedisTemplate<String,Object> redisTemplate;


    @Override
    public Result register(String username, String password,String email) {
        BlogUser user = userMapper.hasSameUsername(username);
        if(user != null){
            return Result.fail(909,"已存在的用户名",null);
        }
        BlogUser loginUser = new BlogUser();
        loginUser.setUsername(username);
        loginUser.setPassword(password);
        String DateFormat = "yyyyMMdd-HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormat);
        Date date = new Date();
        loginUser.setCreated(simpleDateFormat.format(date));
        loginUser.setBuildTime(simpleDateFormat.format(date));
        //loginUser.setLastedLogin(simpleDateFormat.format(date));
        int nowTime = (int ) (date.getTime()/1000);
        loginUser.setTokenId(nowTime);
        loginUser.setAvatar("");
        loginUser.setEmail(email);
        loginUser.setStatus(0);
        String tokenString = "";
        JWT jwt = new JWT();
        tokenString = jwt.createToken(loginUser,date);
        loginUser.setToken(tokenString);
        if(userMapper.register(loginUser) == 1){
            return Result.success(200,"注册成功",tokenString);
        }
        return Result.fail(909,"注册失败","");
    }


    public Result login(String username, String password){

        BlogUser loginUser = userMapper.login(username,password);
        if(loginUser == null){
            return Result.fail(101,"账号密码错误或账号不存在","");
        }
        String DateFormat = "yyyyMMdd-HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateFormat);
        Date date = new Date();

        loginUser.setLastedLogin(simpleDateFormat.format(date));
        loginUser.setBuildTime(simpleDateFormat.format(date));

        String tokenString = "";
        JWT jwt = new JWT();
        tokenString = jwt.createToken(loginUser,date);
        loginUser.setToken(tokenString);
        int i = userMapper.updateToken(loginUser);
        if(i != 1){
            return Result.fail(303,"token更新失败","");
        }
        loginUser.setPassword("");
        loginUser.setEmail("");
        loginUser.setTokenId(0);
        return  Result.success(200,"登录成功",loginUser);
    }



    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public Result uploadAvatar(MultipartFile file,String userId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String date = format.format(new Date());
        String url = "vueblog/avatar/" +  date + UUID.randomUUID().toString().replace("-","") + "avatar" + file.getOriginalFilename();
        try {
            url = ossCommon.ossUpload(file,url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        userMapper.updateAvatar(url,userId);
        return Result.success(200,"上传成功",url);
    }

    @Override
    public Result sumLook(String username) {
        BlogUser i = userMapper.sumLook(username);
        return Result.success(200,"",i);
    }

    @Override
    public Result getUserInfo(String username) {
        BlogUser byUserId = userMapper.getByUserId(username);
        if(byUserId == null)return Result.fail(303,"无此账户","");
        return Result.success(200,"",byUserId);
    }

    @Override
    public Result allUser() {
        if(redisUtil.hasKey("users")){

            Object users = redisTemplate.opsForZSet().reverseRange("users",0,100);
            return Result.success(200,"",users);
        }else {
            Object users = redisTemplate.opsForZSet().reverseRange("userTemp",0,100);
            return Result.success(200,"",users);
        }
    }

    @Override
    public Result search(String msg) {
        if(msg.equals("")){
            Set<Object> contents1 = redisTemplate.opsForZSet().reverseRange("users", 0, -1);
            return Result.success(200,"",contents1);
        }
        List<BlogUser> list = userMapper.search(msg);
        if(list == null){
            return Result.fail(909,"没有与该关键字匹配的用户",null);
        }
        return Result.success(200,"",list);
    }

    @Override
    public Result getRank(String username) {
        Long i = redisTemplate.opsForZSet().reverseRank("users", username);
        return Result.success(200,"",i+1);
    }


}
