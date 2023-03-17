package pro.sky.finalprojectsky.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import java.util.*;


@Getter
@Setter
@Entity(name = "fullAds")
public class FullAds   {

    @JsonProperty("pk")
    @Id
    private Integer pk = null;
    @JsonProperty("authorFirstName")
    private String authorFirstName = null;
    @JsonProperty("authorLastName")
    private String authorLastName = null;
    @JsonProperty("description")
    private String description = null;
    @JsonProperty("email")
    private String email = null;


    //Здесь не совсем понятно, почему список стринговый
    //Возможно это список с адресами для загрузки изображений?
    //@JsonProperty("image")
    //private List<String> image = null;

    @JsonProperty("phone")
    private String phone = null;

    @JsonProperty("price")
    private Integer price = null;

    @JsonProperty("title")
    private String title = null;

    //Создаем поле для хранения комментариев к объявлению
    @JsonProperty("comments")
    //Причину ошибки я не понял...
    //Но нужен контейнер для хранения списка комментариев (от одного до бесконечности)
    //Отключил проверку - надо это иметь ввиду!
    private List<Comment> commentList = null;


    //Не понятно, для чего эти конструкторы?
    //Может быть для создания объявления по одному из имеющихся полей?
    //Но зачем?
    public FullAds authorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
        return this;
    }
    public FullAds authorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
        return this;
    }
    public FullAds description(String description) {
        this.description = description;
        return this;
    }

    public FullAds email(String email) {
        this.email = email;
        return this;
    }
    //public FullAds image(List<String> image) {
    //    this.image = image;
    //    return this;
    //}

    //public FullAds addImageItem(String imageItem) {
    //    if (this.image == null) {
    //        this.image = new ArrayList<String>();
    //    }
    //    this.image.add(imageItem);
    //    return this;
    //}

    public FullAds phone(String phone) {
        this.phone = phone;
        return this;
    }

    public FullAds pk(Integer pk) {
        this.pk = pk;
        return this;
    }
    public FullAds price(Integer price) {
        this.price = price;
        return this;
    }

    public FullAds title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FullAds fullAds = (FullAds) o;
        return Objects.equals(this.authorFirstName, fullAds.authorFirstName) &&
                Objects.equals(this.authorLastName, fullAds.authorLastName) &&
                Objects.equals(this.description, fullAds.description) &&
                Objects.equals(this.email, fullAds.email) &&
                //Objects.equals(this.image, fullAds.image) &&
                Objects.equals(this.phone, fullAds.phone) &&
                Objects.equals(this.pk, fullAds.pk) &&
                Objects.equals(this.price, fullAds.price) &&
                Objects.equals(this.title, fullAds.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorFirstName, authorLastName, description, email, phone, pk, price, title);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class FullAds {\n");

        sb.append("    authorFirstName: ").append(toIndentedString(authorFirstName)).append("\n");
        sb.append("    authorLastName: ").append(toIndentedString(authorLastName)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        //sb.append("    image: ").append(toIndentedString(image)).append("\n");
        sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
        sb.append("    pk: ").append(toIndentedString(pk)).append("\n");
        sb.append("    price: ").append(toIndentedString(price)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

    @ManyToOne(optional = false)
    private Comment comments;

    public Comment getComments() {
        return comments;
    }

    public void setComments(Comment comments) {
        this.comments = comments;
    }
}
