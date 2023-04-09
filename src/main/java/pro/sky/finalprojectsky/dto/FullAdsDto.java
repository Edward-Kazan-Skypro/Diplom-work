package pro.sky.finalprojectsky.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FullAdsDto {
    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private int id;
    private int price;
    private String title;
}