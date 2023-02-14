package cjj.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    private long tokenId;
    private long userId;
    private String token;
    private int buildTime;

}
