package pro.sky.finalprojectsky.dto;

import lombok.Data;

@Data
public class CommentDto {
    //id автора комментария
    private int author;
    //ссылка на аватар автора комментария
    private String authorImage;
    //имя создателя комментария
    private String authorFirstName;
    //дата и время создания комментария
    private Long createdAt;
    //id комментария
    private int pk;
    //текст комментария
    private String text;
}
