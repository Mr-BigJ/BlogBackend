package cjj.common;

import cjj.dao.BlogUserMapper;
import cjj.dao.ResortUserMapper;
import cjj.entity.BlogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

@Component
@EnableScheduling
@Async
public class ScheduleSort  {
    public static int usersSize;
    public static List<BlogUser> users;

    @Autowired
    RedisUtil redisUtil;

    @Resource
    ResortUserMapper resortUserMapper;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Autowired
    BlogUserMapper blogUserMapper;

    @Scheduled(fixedRate = 60000*60*3)
    public void reSortUser() throws ExecutionException, InterruptedException {
        //获取两个变量的合集放到第三个中
        redisTemplate.opsForZSet().unionAndStore("users","","userTemp");
        redisUtil.del("users");
        List<BlogUser> blogUsers = blogUserMapper.allUser();
        usersSize = blogUsers.size();
        users = blogUsers;
        for (int i = 0; i < 200; i++) {
            new Thread(new SortThread(usersSize/200*i,usersSize/200*(i+1)-1,users,redisTemplate)).start();
        }
        //Thread.sleep(30000);
        redisTemplate.delete("userTemp");
    }


}
