package pro.sky.finalprojectsky.dto;

import lombok.Data;

@Data
public class AdsDto {
    private int adId;

    private int authorId;

    private String imageURL;

    private int price;

    private String title;

    private String description;
}
