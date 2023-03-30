package pro.sky.finalprojectsky.dto;

import lombok.Data;
import pro.sky.finalprojectsky.model.Image;

@Data
public class AdsDto {
    private int pk;

    private int author;

    private Image image;

    private int price;

    private String title;

    private String description;
}
