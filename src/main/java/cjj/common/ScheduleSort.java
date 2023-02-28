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
    public static int listSize;
    public static List<Integer> idList;

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

//        redisTemplate.opsForZSet().unionAndStore("users","dksgkdjsn","userTemp");
        /*redisUtil.del("users");
        List<Integer> list = resortUserMapper.getAllUserId();
        listSize = list.size();
        idList = list;
        for (int i = 0; i < 20; i++) {
            new Thread(new SortThread(listSize/20*i,listSize/20*(i+1)-1,resortUserMapper)).start();
        }

        List<BlogUser> blogUsers = blogUserMapper.allUser();
        for (BlogUser b:blogUsers) {
            double rule = b.getSumpraise()-b.getSumunpraise() * 0.8 + b.getSumlook() * 0.5;
            redisTemplate.opsForZSet().add("users",b.getUsername()+"-"+b.getSumlook()+"-"+b.getSumunpraise()+"-"+b.getSumpraise(),rule);
        }
        redisTemplate.delete("userTemp");*/
    }


}
