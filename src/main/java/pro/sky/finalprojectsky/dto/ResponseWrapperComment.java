package pro.sky.finalprojectsky.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseWrapperComment {

    private int count;
    private List<CommentDto> result = new ArrayList<>();
}
