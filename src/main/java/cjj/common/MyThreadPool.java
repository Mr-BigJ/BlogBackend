package cjj.common;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

//@Component
public class MyThreadPool {



    //@Bean("myPool")
    public ExecutorService getMyPool(){
        return new ForkJoinPool();
    }
}
