package pro.sky.finalprojectsky.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pro.sky.finalprojectsky.dto.CommentDto;
import java.util.List;

@Component
public interface CommentService {

    CommentDto addComment(Integer adId, CommentDto commentDto);

    List<CommentDto> getComments(Integer adId);

    CommentDto getComment(Integer adId, Integer commentId);

    boolean deleteComment(Integer adId, Integer commentId, Authentication authentication);

    CommentDto updateComment(Integer adId, Integer commentId, CommentDto updateComment, Authentication authentication);
}