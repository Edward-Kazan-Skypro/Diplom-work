package pro.sky.finalprojectsky.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pro.sky.finalprojectsky.dto.AdsCommentDto;
import java.util.List;

@Component
public interface CommentService {

    AdsCommentDto addComment(Integer adId, AdsCommentDto adsCommentDto);

    List<AdsCommentDto> getComments(Integer adId);

    AdsCommentDto getComment(Integer adId, Integer commentId);

    boolean deleteComment(Integer adId, Integer commentId, Authentication authentication);

    AdsCommentDto updateComment(Integer adId, Integer commentId, AdsCommentDto updateComment, Authentication authentication);
}