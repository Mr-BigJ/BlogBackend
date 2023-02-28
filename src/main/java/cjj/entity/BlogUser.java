package cjj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogUser implements Serializable {
    private int id;
    private String username;
    private String avatar;
    private String email;
    private String password;
    private String created;
    private String lastedLogin;
    private int status;
    private long tokenId;
    private String token;
    private String buildTime;
    private int sumlook;
    private int sumpraise;
    private int sumunpraise;
    private int sumcomment;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCreated() {
        return created;
    }

    public String getLastedLogin() {
        return lastedLogin;
    }

    public int getStatus() {
        return status;
    }

    public long getTokenId() {
        return tokenId;
    }

    public String getToken() {
        return token;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public int getSumlook() {
        return sumlook;
    }

    public int getSumpraise() {
        return sumpraise;
    }

    public int getSumunpraise() {
        return sumunpraise;
    }

    public int getSumcomment() {
        return sumcomment;
    }
}
