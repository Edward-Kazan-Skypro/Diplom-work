package pro.sky.finalprojectsky.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import pro.sky.finalprojectsky.dto.CommentDto;
import pro.sky.finalprojectsky.entity.AdsComment;
import pro.sky.finalprojectsky.mappers.CommentMapper;
import pro.sky.finalprojectsky.model.User;
import pro.sky.finalprojectsky.repository.AdsRepository;
import pro.sky.finalprojectsky.repository.CommentsRepository;
import pro.sky.finalprojectsky.repository.UserRepository;
import pro.sky.finalprojectsky.service.CommentsService;

import java.time.LocalDateTime;
import java.util.List;
/**
 * Имплементация сервиса для работы с комментариями
 */
@Transactional
@RequiredArgsConstructor
@Service
public class CommentsServiceImpl implements CommentsService {
    Logger logger = LoggerFactory.getLogger(CommentsService.class);

    private final AdsRepository adsRepository;

    private final CommentsRepository commentRepository;

    private final UserRepository userRepository;

    private final CommentMapper commentMapper;


    @Override
    public CommentDto addComment(long adKey, CommentDto commentDto) {
        logger.info("Was invoked method for add ads comment");
        User user = userRepository.findByEmail(SecurityContextHolder.getContext()
                .getAuthentication().getName()).orElseThrow();
        AdsComment adsComment = commentMapper.toEntity(commentDto);
        adsComment.setAuthor(user);
        adsComment.setAds(adsRepository.findById(adKey).orElseThrow());
        adsComment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(adsComment);
        logger.info("comment added");
        return commentMapper.toDto(adsComment);
    }


    @Transactional(readOnly = true)
    @Override
    public List<CommentDto> getComments(long adKey) {
        logger.info("Was invoked method for get ads comment by adKey");
        List<AdsComment> commentList = commentRepository.findAllByAdsId(adKey);
        return (List<CommentDto>) commentMapper.toDto((AdsComment) commentList);
    }

    @Transactional(readOnly = true)
    @Override
    public CommentDto getComment(long adKey, long id) {
        logger.info("Was invoked method for get ads comment by adKey and id");
        AdsComment adsComment = (AdsComment) commentRepository.findById( id)
                .orElseThrow(() -> new NotFoundException("Комментарий с id " + id + " не найден!"));
        logger.warn("Comment by id {} not found", id);
        if (adsComment.getAds().getId() != adKey) {
            logger.warn("Comment by id {} does not belong to ad by id {} ", id, adKey);
            throw new NotFoundException("Комментарий с id " + id + " не принадлежит объявлению с id " + adKey);
        }
        return commentMapper.toDto(adsComment);
    }

    @Override
    public boolean deleteComment(long adKey, long id, org.apache.tomcat.util.net.openssl.ciphers.Authentication authentication) {
        return false;
    }

    @Override
    public CommentDto updateComment(long adKey, long id, CommentDto updateComment, org.apache.tomcat.util.net.openssl.ciphers.Authentication authentication) {
        return null;
    }

    @Override
    public boolean deleteComment(long adKey, long id, Authentication authentication) {
        logger.info("Was invoked method for delete ads comment by adKey and id");
        AdsComment adsComment = (AdsComment) commentRepository.findById( id)
                .orElseThrow(() -> new NotFoundException("Комментарий с id " + id + " не найден!"));
        logger.warn("Comment by {} not found", id);
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        if (adsComment.getAuthor().getEmail().equals(user.getEmail()) || user.getRole().getAuthority().equals("ADMIN")) {
            if (adsComment.getAds().getId() != adKey) {
                logger.warn("Comment by id {} does not belong to ad by id {} ", id, adKey);
                throw new NotFoundException("Комментарий с id " + id + " не принадлежит объявлению с id " + adKey);
            }
            commentRepository.delete(adsComment);
            logger.info("comment deleted");
            return true;
        }
        logger.warn("comment not deleted");
        return false;
    }

    @Override
    public CommentDto updateComment(long adKey, long id, CommentDto updateComment, Authentication authentication) {
        logger.info("Was invoked method for update ads comment by adKey and id");
        AdsComment updatedComment = (AdsComment) commentRepository.findById( id)
                .orElseThrow(() -> new NotFoundException("Комментарий с id " + id + " не найден!"));
        logger.warn("Comment by id {} not found", id);
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        if (updatedComment.getAuthor().getEmail().equals(user.getEmail()) || user.getRole().getAuthority().equals("ADMIN")) {
            if (updatedComment.getAds().getId() != adKey) {
                logger.warn("Comment by id {} does not belong to ad by id {} ", id, adKey);
                throw new NotFoundException("Комментарий с id " + id + " не принадлежит объявлению с id " + adKey);
            }
            updatedComment.setText(updateComment.getText());
            commentRepository.save(updatedComment);
            logger.warn("comment updated");
            return commentMapper.toDto(updatedComment);
        }
        return updateComment;
    }
}