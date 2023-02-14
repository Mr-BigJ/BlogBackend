package cjj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogContent implements Serializable {
    private int id;
    private String content;
    private int  userId;
    private String title;
    private String description;
    private int look;
    private int praise;
    private int unpraise;
    private String username;
    private int status;
    private String created;

}
