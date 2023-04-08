package pro.sky.finalprojectsky.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import pro.sky.finalprojectsky.dto.AdsCommentDto;
import pro.sky.finalprojectsky.entity.AdsComment;
import pro.sky.finalprojectsky.mapper.AdsCommentMapper;
import pro.sky.finalprojectsky.entity.User;
import pro.sky.finalprojectsky.repository.AdsCommentRepository;
import pro.sky.finalprojectsky.repository.AdsRepository;
import pro.sky.finalprojectsky.repository.UserRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import pro.sky.finalprojectsky.security.SecurityUtils;
import pro.sky.finalprojectsky.service.CommentService;

@Transactional
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final AdsRepository adsRepository;

    private final UserRepository userRepository;

    private final AdsCommentRepository adsCommentRepository;

    private final AdsCommentMapper adsCommentMapper;


    @Override
    public AdsCommentDto addComment(Integer adId, AdsCommentDto adsCommentDto) {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext()
                .getAuthentication().getName()).orElseThrow();
        AdsComment adsComment = adsCommentMapper.toEntity(adsCommentDto);
        adsComment.setAuthor(user);
        adsComment.setAds(adsRepository.findById(adId).orElseThrow());
        //adsComment.setCreatedAt(LocalDateTime.now());
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        long date = zdt.toInstant().toEpochMilli();
        adsComment.setCreatedAt(date);
        adsCommentRepository.save(adsComment);
        return adsCommentMapper.toDto(adsComment);
    }


    @Transactional(readOnly = true)
    @Override
    public List<AdsCommentDto> getComments(Integer adId) {
        List<AdsComment> commentList = adsCommentRepository.findAllByAdsId(adId);
        return adsCommentMapper.toDto(commentList);
    }

    @Transactional(readOnly = true)
    @Override
    public AdsCommentDto getComment(Integer adId, Integer commentId) {
        AdsComment adsComment = adsCommentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий с id " + commentId + " не найден!"));
        if (adsComment.getAds().getId() != adId) {
            throw new NotFoundException("Комментарий с id " + commentId + " не принадлежит объявлению с id " + adId);
        }
        return adsCommentMapper.toDto(adsComment);
    }

    @Override
    public boolean deleteComment(Integer adId, Integer commentId, Authentication authentication) {
        AdsComment adsComment = adsCommentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий с id " + commentId + " не найден!"));
        //User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        //if (adsComment.getAuthor().getEmail().equals(user.getEmail()) || user.getRole().getAuthority().equals("ADMIN")) {
        if (SecurityUtils.checkPermissionToAdsComment(adsComment)) {
            if (adsComment.getAds().getId() != adId) {
                throw new NotFoundException("Комментарий с id " + commentId + " не принадлежит объявлению с id " + adId);
            }
            adsCommentRepository.delete(adsComment);
            return true;
        }
        return false;
    }

    @Override
    public AdsCommentDto updateComment(Integer adId, Integer commentId, AdsCommentDto updateComment, Authentication authentication) {

        AdsComment updatedComment = adsCommentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий с id " + commentId + " не найден!"));
        //User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        //if (updatedComment.getAuthor().getEmail().equals(user.getEmail()) || user.getRole().getAuthority().equals("ADMIN")) {
        if (SecurityUtils.checkPermissionToAdsComment(updatedComment)) {
            if (updatedComment.getAds().getId() != adId) {
                throw new NotFoundException("Комментарий с id " + commentId + " не принадлежит объявлению с id " + adId);
            }
            updatedComment.setText(updateComment.getText());
            adsCommentRepository.save(updatedComment);
            return adsCommentMapper.toDto(updatedComment);
        }
        return updateComment;
    }
}