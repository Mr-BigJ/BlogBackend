package cjj.config;

//import cjj.Interceptor.GlobalInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CORSConfig implements WebMvcConfigurer {
//    @Autowired
//    private GlobalInterceptor globalInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .maxAge(3600)
                .allowedOrigins("*");
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
////        List<String> list = new ArrayList<>();
////        list.add("/*.css");
////        list.add("/*.html");
////        list.add("/*.png");
////        list.add("/*.jpg");
////        list.add("/*.woff");
////        list.add("/*.ttf");
////        list.add("/*.ico");
////        list.add("/*.js");
////        list.add("/");
//        registry.addInterceptor(globalInterceptor).addPathPatterns("/**")
//                .excludePathPatterns("/pro/user/login")
//                .excludePathPatterns("/error/**")
//                .excludePathPatterns("/")
//                .excludePathPatterns("/favicon.ico")
//                .excludePathPatterns("/index.html")
//                .excludePathPatterns("/css/**")
//                .excludePathPatterns("/fonts/**")
//                .excludePathPatterns("/img/**")
//                .excludePathPatterns("/js/**")
//                .excludePathPatterns("/pro/user/allUser")
//                .excludePathPatterns("/pro/blog/uploadContentImg")
//                .excludePathPatterns("/pro/user/register");
//    }
}
