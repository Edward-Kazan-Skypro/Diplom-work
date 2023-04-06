package pro.sky.finalprojectsky.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateAdsDto {

    @NotBlank
    @Size(min = 8)
    private String title;
    private int price;
    @NotBlank
    @Size(min = 8)
    private String description;

    private String image;
    //ads id
    private int pk;
}