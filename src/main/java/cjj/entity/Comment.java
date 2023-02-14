package cjj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {
    private int id;
    private int userId;
    private int contentId;
    private String comment;
    private String commentDate;
}
