package pro.sky.finalprojectsky.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AdsCommentDto {
    private int id;
    private int author;
    private long createdAt;
    @NotBlank
    @Size(min = 8)
    private String text;
}