package pro.sky.finalprojectsky.dto;

import lombok.Data;

@Data
public class CommentDto {

    private int author;
    private String createdAt;
    private int pk;
    private String text;
}
