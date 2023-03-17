package pro.sky.finalprojectsky.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
//Возможно и не надо из класса делать сущность, ведь все комментарии - часть объявления (комментарии в виде списка)
//@Entity(name = "comments")
public class Comment   {
    @JsonProperty("pk")
    @Id
    private Integer pk = null;

    //Почему здесь тип поля Integer?
    //Может быть это id пользователя, который создает комментарий?
    //Тогда получается нужна еще одна модель Author?
    //А не совпадает ли это с User, т.е. с зарегистрированным пользователем?
    //Комментарий может писать зарегистрированный пользователь? Или любой может?
    @JsonProperty("author")
    private Integer author = null;

    //Может использовать тип LocalDate?
    @JsonProperty("createdAt")
    private String createdAt = null;

    @JsonProperty("text")
    private String text = null;


    public Comment author(Integer author) {
        this.author = author;
        return this;
    }

    public Comment createdAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Comment pk (Integer pk) {
        this.pk = pk;
        return this;
    }
    public Comment text(String text) {
        this.text = text;
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
        Comment comment = (Comment) o;
        return Objects.equals(this.author, comment.author) &&
                Objects.equals(this.createdAt, comment.createdAt) &&
                Objects.equals(this.pk, comment.pk) &&
                Objects.equals(this.text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, createdAt, pk, text);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Comment {\n");

        sb.append("    author: ").append(toIndentedString(author)).append("\n");
        sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
        sb.append("    pk: ").append(toIndentedString(pk)).append("\n");
        sb.append("    text: ").append(toIndentedString(text)).append("\n");
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
}

