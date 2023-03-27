package pro.sky.finalprojectsky.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentDto {

    private int authorId;
    private String authorFirstName;
    private String authorImageURL;
    private Timestamp createdAt;
    private int commentId;
    private String text;
}
