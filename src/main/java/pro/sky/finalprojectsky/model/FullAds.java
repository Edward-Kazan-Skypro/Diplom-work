package pro.sky.finalprojectsky.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.*;


@Getter
@Setter
@Entity(name = "fullAds")
public class FullAds   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pk;

    private String authorFirstName;

    private String authorLastName;

    private String description;

    private String email;

   //private List<String> image;

    private String phone;

    private Integer price ;
    private String title ;

    //private List<Comment> commentList;


}
