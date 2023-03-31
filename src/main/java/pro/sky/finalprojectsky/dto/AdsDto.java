package pro.sky.finalprojectsky.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdsDto {
    private int id;

    private int author;

    private String image;

    private int price;

    private String title;
}
