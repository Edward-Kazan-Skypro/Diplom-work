package pro.sky.finalprojectsky.dto;

import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class AdsDto {
    //ads id
    private int id;
    //user id
    private int author;
    private String image;
    private int price;
    @NotBlank
    @Size(min = 8)
    private String title;
    @NotBlank
    @Size(min = 8)
    private String description;

    public AdsDto() {
    }

    public AdsDto(int id, int author, String image, int price, String title, String description) {
        this.id = id;
        this.author = author;
        this.image = image;
        this.price = price;
        this.title = title;
        this.description = description;
    }
}