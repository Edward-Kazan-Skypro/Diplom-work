package pro.sky.finalprojectsky.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class AdsDto {
    //ads id
    private int pk;
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

    public AdsDto(int pk, int author, String image, int price, String title, String description) {
        this.pk = pk;
        this.author = author;
        this.image = image;
        this.price = price;
        this.title = title;
        this.description = description;
    }
}