package pro.sky.finalprojectsky.dto;

import lombok.Data;
import pro.sky.finalprojectsky.model.Image;

@Data
public class AdsDto {
    private int id;

    private int author;

    private Image image;

    private int price;

    private String title;
}
