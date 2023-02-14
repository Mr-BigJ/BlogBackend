package cjj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
@MapperScan(basePackages = "cjj.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);

    }
}



