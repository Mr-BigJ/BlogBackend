package cjj.common;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*@ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler
    public Result handler(ShiroException e){
        log.error("异常是：----{}",e.getMessage());
        return Result.fail(401,"异常是"+e.getMessage(),null);
    }*/

    @ExceptionHandler
    public Result handler(RuntimeException e){
        if(e instanceof JwtException){
            return Result.fail(401,"异常是"+e.getMessage(),null);
        }
        log.error("异常是：----{}",e.getMessage());
        return Result.fail(0,"异常是"+e.getMessage(),null);
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler
//    public Result handler(MethodArgumentNotValidException e){
//        log.error("异常是：----{}",e.getMessage());
//        return Result.fail(0,"异常是"+e.getMessage(),null);
//    }
}
