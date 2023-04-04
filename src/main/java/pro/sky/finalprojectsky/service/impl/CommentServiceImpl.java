package pro.sky.finalprojectsky.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import pro.sky.finalprojectsky.dto.CommentDto;
import pro.sky.finalprojectsky.entity.AdsComment;
import pro.sky.finalprojectsky.mapper.CommentMapper;
import pro.sky.finalprojectsky.entity.User;
import pro.sky.finalprojectsky.repository.AdsRepository;
import pro.sky.finalprojectsky.repository.CommentRepository;
import pro.sky.finalprojectsky.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import pro.sky.finalprojectsky.service.CommentService;

@Transactional
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    //Logger logger = LoggerFactory.getLogger(CommentsService.class);

    private final AdsRepository adsRepository;

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final CommentMapper commentMapper;


    @Override
    public CommentDto addComment(Integer adId, CommentDto commentDto) {
        //logger.info("Was invoked method for add ads comment");
        User user = userRepository.findByEmail(SecurityContextHolder.getContext()
                .getAuthentication().getName()).orElseThrow();
        AdsComment adsComment = commentMapper.toEntity(commentDto);
        adsComment.setAuthor(user);
        adsComment.setAds(adsRepository.findById(adId).orElseThrow());
        adsComment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(adsComment);
        //logger.info("comment added");
        return commentMapper.toDto(adsComment);
    }


    @Transactional(readOnly = true)
    @Override
    public List<CommentDto> getComments(Integer adId) {
        //logger.info("Was invoked method for get ads comment by adId");
        List<AdsComment> commentList = commentRepository.findAllByAdsId(adId);
        return (List<CommentDto>) commentMapper.toDto((AdsComment) commentList);
    }

    @Transactional(readOnly = true)
    @Override
    public CommentDto getComment(Integer adId, Integer commentId) {
        //logger.info("Was invoked method for get ads comment by adKey and id");
        AdsComment adsComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий с id " + commentId + " не найден!"));
        //logger.warn("Comment by id {} not found", id);
        if (adsComment.getAds().getId() != adId) {
            //logger.warn("Comment by id {} does not belong to ad by id {} ", id, adKey);
            throw new NotFoundException("Комментарий с id " + commentId + " не принадлежит объявлению с id " + adId);
        }
        return commentMapper.toDto(adsComment);
    }

    @Override
    public boolean deleteComment(Integer adId, Integer commentId, Authentication authentication) {
        //logger.info("Was invoked method for delete ads comment by adKey and id");
        AdsComment adsComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий с id " + commentId + " не найден!"));
        //logger.warn("Comment by {} not found", id);
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        if (adsComment.getAuthor().getEmail().equals(user.getEmail()) || user.getRole().getAuthority().equals("ADMIN")) {
            if (adsComment.getAds().getId() != adId) {
                //logger.warn("Comment by id {} does not belong to ad by id {} ", id, adKey);
                throw new NotFoundException("Комментарий с id " + commentId + " не принадлежит объявлению с id " + adId);
            }
            commentRepository.delete(adsComment);
            //logger.info("comment deleted");
            return true;
        }
        //logger.warn("comment not deleted");
        return false;
    }

    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CommentDto updateComment, Authentication authentication) {
        //logger.info("Was invoked method for update ads comment by adKey and id");
        AdsComment updatedComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий с id " + commentId + " не найден!"));
        //logger.warn("Comment by id {} not found", id);
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        if (updatedComment.getAuthor().getEmail().equals(user.getEmail()) || user.getRole().getAuthority().equals("ADMIN")) {
            if (updatedComment.getAds().getId() != adId) {
                //logger.warn("Comment by id {} does not belong to ad by id {} ", id, adKey);
                throw new NotFoundException("Комментарий с id " + commentId + " не принадлежит объявлению с id " + adId);
            }
            updatedComment.setText(updateComment.getText());
            commentRepository.save(updatedComment);
            //logger.warn("comment updated");
            return commentMapper.toDto(updatedComment);
        }
        return updateComment;
    }
}