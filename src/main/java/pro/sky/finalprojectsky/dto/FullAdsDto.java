package pro.sky.finalprojectsky.dto;

import lombok.Data;
import pro.sky.finalprojectsky.model.Image;

@Data
public class FullAdsDto {

    private Integer id;
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private Integer price;
    private String title;
}
