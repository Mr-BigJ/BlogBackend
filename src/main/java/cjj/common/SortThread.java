package cjj.common;

import cjj.entity.BlogUser;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;


public class SortThread implements Runnable {
    private int start,end;
    private List<BlogUser> users;
    RedisTemplate<String,Object> redisTemplate;
    public SortThread(int start, int end,  List<BlogUser> users, RedisTemplate<String,Object> redisTemplate){
        this.start=start;
        this.end=end;
        this.users = users;
        this.redisTemplate = redisTemplate;
    }



    @Override
    public void run() {
        for (int i = start ; i<=end;i++) {
            BlogUser b = users.get(i);
            double rule = b.getSumpraise()-b.getSumunpraise() * 0.8 + b.getSumlook() * 0.5;
            redisTemplate.opsForZSet().add("users",b.getUsername()+"-"+b.getSumlook()+"-"+b.getSumunpraise()+"-"+b.getSumpraise(),rule);
        }
    }
}
