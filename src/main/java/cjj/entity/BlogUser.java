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
}
