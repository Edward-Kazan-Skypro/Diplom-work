package pro.sky.finalprojectsky.dto;

import lombok.Data;
import pro.sky.finalprojectsky.model.Comment;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseWrapperComment {

    private int count;
    private List<Comment> result = new ArrayList<>();
}
