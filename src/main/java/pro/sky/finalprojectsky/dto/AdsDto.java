package pro.sky.finalprojectsky.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdsDto {
    private Integer id;

    private Integer author;

    private String image;

    private Integer price;

    private String title;
}
