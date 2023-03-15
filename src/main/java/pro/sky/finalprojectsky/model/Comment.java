package pro.sky.finalprojectsky.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Getter
@Setter
@Entity(name = "comments")
public class Comment   {
    @JsonProperty("id")
    @Id
    private Long id = null;
    @JsonProperty("author")
    private Integer author = null;

    //Может использовать тип LocalDate?
    @JsonProperty("createdAt")
    private String createdAt = null;

    @JsonProperty("text")
    private String text = null;

    //Наверно нужно сделать связь с таблицей объявлений
    //т.е. у одного объявления может быть много комментариев
    @ManyToOne
    private FullAds fullAds;


    public Comment author(Integer author) {
        this.author = author;
        return this;
    }

    public Comment createdAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Comment id (Long id) {
        this.id = id;
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
                Objects.equals(this.id, comment.id) &&
                Objects.equals(this.text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, createdAt, id, text);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Comment {\n");

        sb.append("    author: ").append(toIndentedString(author)).append("\n");
        sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
        sb.append("    pk: ").append(toIndentedString(id)).append("\n");
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

