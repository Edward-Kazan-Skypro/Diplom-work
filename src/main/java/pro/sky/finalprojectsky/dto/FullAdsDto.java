package pro.sky.finalprojectsky.dto;

import lombok.Data;
import pro.sky.finalprojectsky.model.Image;

@Data
public class FullAdsDto {

    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private Image image;
    private String phone;
    private Integer pk;
    private Integer price;
    private String title;
}
