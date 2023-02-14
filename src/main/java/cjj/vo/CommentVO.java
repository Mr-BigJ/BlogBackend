package cjj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO implements Serializable {
    private String username;
    private String comment;
    private int id;
    private int contentId;
    private String commentDate;
}
