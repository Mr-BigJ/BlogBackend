package cjj.common;

import cjj.entity.BlogUser;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWT {

     public String createToken(BlogUser user, Date date){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        JwtBuilder builder = Jwts.builder()
                //这两个是jwt的header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //这四个是jwt的playload
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + 1000*60*60*24*3))
                .claim("username",String.valueOf(user.getUsername()))
                .setIssuer("cjj")
                //这个是signature  由header和playload两部分base64后加上secret
                .signWith(signatureAlgorithm,"CJJBLOG");
        String jwt = builder.compact();
        return jwt;
    }
}
