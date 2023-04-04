package pro.sky.finalprojectsky.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import pro.sky.finalprojectsky.dto.AdsCommentDto;
import pro.sky.finalprojectsky.entity.AdsComment;
import pro.sky.finalprojectsky.entity.User;
import pro.sky.finalprojectsky.mapper.AdsCommentMapper;
import pro.sky.finalprojectsky.repository.AdsCommentRepository;
import pro.sky.finalprojectsky.repository.AdsRepository;
import pro.sky.finalprojectsky.repository.UserRepository;
import pro.sky.finalprojectsky.service.CommentService;

import java.time.LocalDateTime;
import java.util.List;


@Transactional
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    Logger logger = LoggerFactory.getLogger(CommentService.class);

    private final AdsRepository adsRepository;

    private final AdsCommentRepository adsCommentRepository;

    private final UserRepository userRepository;

    private final AdsCommentMapper adsCommentMapper;


    @Override
    public AdsCommentDto addAdsComment(Integer adKey, AdsCommentDto adsCommentDto) {
        logger.info("Was invoked method for add ads comment");
        User user = userRepository.findByEmail(SecurityContextHolder.getContext()
                .getAuthentication().getName()).orElseThrow();
        AdsComment adsComment = adsCommentMapper.toEntity(adsCommentDto);
        adsComment.setAuthor(user);
        adsComment.setAds(adsRepository.findById(adKey).orElseThrow());
        adsComment.setCreatedAt(LocalDateTime.now());
        adsCommentRepository.save(adsComment);
        logger.info("comment added");
        return adsCommentMapper.toDto(adsComment);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AdsCommentDto> getAdsComments(Integer adKey) {
        logger.info("Was invoked method for get ads comment by adKey");
        List<AdsComment> commentList = adsCommentRepository.findAllByAdsId(adKey);
        return adsCommentMapper.toDto(commentList);
    }

    @Transactional(readOnly = true)
    @Override
    public AdsCommentDto getAdsComment(Integer adKey, Integer id) {
        logger.info("Was invoked method for get ads comment by adKey and id");
        AdsComment adsComment = adsCommentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Комментарий с id " + id + " не найден!"));
        logger.warn("Comment by id {} not found", id);
        if (adsComment.getAds().getId() != adKey) {
            logger.warn("Comment by id {} does not belong to ad by id {} ", id, adKey);
            throw new NotFoundException("Комментарий с id " + id + " не принадлежит объявлению с id " + adKey);
        }
        return adsCommentMapper.toDto(adsComment);
    }

    @Override
    public boolean deleteAdsComment(Integer adKey, Integer id, Authentication authentication) {
        logger.info("Was invoked method for delete ads comment by adKey and id");
        AdsComment adsComment = adsCommentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Комментарий с id " + id + " не найден!"));
        logger.warn("Comment by {} not found", id);
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        if (adsComment.getAuthor().getEmail().equals(user.getEmail()) || user.getRole().getAuthority().equals("ADMIN")) {
            if (adsComment.getAds().getId() != adKey) {
                logger.warn("Comment by id {} does not belong to ad by id {} ", id, adKey);
                throw new NotFoundException("Комментарий с id " + id + " не принадлежит объявлению с id " + adKey);
            }
            adsCommentRepository.delete(adsComment);
            logger.info("comment deleted");
            return true;
        }
        logger.warn("comment not deleted");
        return false;
    }

    @Override
    public AdsCommentDto updateAdsComment(Integer adKey, Integer id, AdsCommentDto updateAdsComment, Authentication authentication) {
        logger.info("Was invoked method for update ads comment by adKey and id");
        AdsComment updatedAdsComment = adsCommentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Комментарий с id " + id + " не найден!"));
        logger.warn("Comment by id {} not found", id);
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        if (updatedAdsComment.getAuthor().getEmail().equals(user.getEmail()) || user.getRole().getAuthority().equals("ADMIN")) {
            if (updatedAdsComment.getAds().getId() != adKey) {
                logger.warn("Comment by id {} does not belong to ad by id {} ", id, adKey);
                throw new NotFoundException("Комментарий с id " + id + " не принадлежит объявлению с id " + adKey);
            }
            updatedAdsComment.setText(updateAdsComment.getText());
            adsCommentRepository.save(updatedAdsComment);
            logger.warn("comment updated");
            return adsCommentMapper.toDto(updatedAdsComment);
        }
        return updateAdsComment;
    }
}