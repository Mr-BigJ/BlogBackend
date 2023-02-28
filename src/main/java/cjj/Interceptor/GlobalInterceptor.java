package cjj.Interceptor;

import cjj.dao.BlogUserMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


@Component
@Aspect
public class GlobalInterceptor{

    @Resource
    BlogUserMapper userMapper;

    @Pointcut("execution(* cjj.Controller..*.*(..))")
    public void demo(){}

    @Before(value = "demo()")
    public void preHandle(JoinPoint joinPoint)  {
//        response.setHeader("Access-Control-Allow-Origin","*");
//        response.setHeader("Access-Control-Allow-Headers", "*");
//        response.setHeader("Access-Control-Allow-Methods", "*");
//        System.out.print(request.getRequestURI());
        System.out.println(joinPoint.getSignature().getName());
        if(!joinPoint.getSignature().getName().equals("register") && !joinPoint.getSignature().getName().equals("login")
           && !joinPoint.getSignature().getName().equals("uploadAvatar") && !joinPoint.getSignature().getName().equals("uploadContentImg")){
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            System.out.println("进入前置拦截");
            String token = null;
            try {
                token = request.getHeader("token");
            }catch (NullPointerException e){
                e.printStackTrace();
            }
            if(null == token || "".equals(token)){
                throw new JwtException("token为空");
            }
            //解析的时候会判断是否过期，如果过期则会抛出异常，因此下面的过期判断应该到不了
            Claims claims = null;
            try {
                claims =Jwts.parser().setSigningKey("CJJBLOG").parseClaimsJws(token).getBody();
            }catch (ExpiredJwtException e){
                //问题所在
                claims = e.getClaims();
            }

            String username =(String) claims.get("username");
            String dbToken = userMapper.getTokenByUsername(username);
            if("".equals(dbToken) || null == dbToken){
                throw new JwtException("token不存在");
            }
            if(!dbToken.equals(token)){
                throw new JwtException("token伪造");
            }
            //判断过期时间
            Date expiration = claims.getExpiration();
            //
            int chaoshi =(int)  ( (expiration.getTime()/1000) - new Date().getTime());
            if( chaoshi > 60 * 60 * 24 * 3 ){
                throw new JwtException("token过期");
            }
        }

    }

//    public void getWriter(HttpServletResponse response) throws IOException {
//        PrintWriter writer = null;
//        try {
//            //没有登录 就告诉前台 应该跳到登录页面 （前台用后置拦截器接受：在每次请求后响应之前拦截，就是有res以后执行成功函数之前）
//            //告诉前台我要传的数据格式 和字符集
//            response.setContentType("text/json;charset=utf-8");
//            //要一个流
//            writer = response.getWriter();
//            //要把什么以json格式传给前台
//            writer.write("{\"code\":401}");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (writer != null) {
//                writer.close();
//            }
//        }
//
//    }


}
