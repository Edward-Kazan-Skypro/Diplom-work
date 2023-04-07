package pro.sky.finalprojectsky.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import pro.sky.finalprojectsky.dto.CommentDto;
import pro.sky.finalprojectsky.dto.ResponseWrapper;
import pro.sky.finalprojectsky.entity.AdsComment;
import pro.sky.finalprojectsky.mapper.CommentMapper;
import pro.sky.finalprojectsky.entity.User;
import pro.sky.finalprojectsky.repository.AdsRepository;
import pro.sky.finalprojectsky.repository.CommentRepository;
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

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private final CommentMapper commentMapper;


    @Override
    public CommentDto addComment(Integer adId, CommentDto commentDto) {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext()
                .getAuthentication().getName()).orElseThrow();
        AdsComment adsComment = commentMapper.toEntity(commentDto);
        adsComment.setAuthor(user);
        adsComment.setAds(adsRepository.findById(adId).orElseThrow());
        //adsComment.setCreatedAt(LocalDateTime.now());
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        long date = zdt.toInstant().toEpochMilli();
        adsComment.setCreatedAt(date);
        commentRepository.save(adsComment);
        return commentMapper.toDto(adsComment);
    }


    @Transactional(readOnly = true)
    @Override
    public List<CommentDto> getComments(Integer adId) {
        List<AdsComment> commentList = commentRepository.findAllByAdsId(adId);
        return commentMapper.toDto(commentList);
        //return (List<CommentDto>) commentMapper.toDto((AdsComment) commentList);
    }

    @Transactional(readOnly = true)
    @Override
    public CommentDto getComment(Integer adId, Integer commentId) {
        AdsComment adsComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий с id " + commentId + " не найден!"));
        if (adsComment.getAds().getId() != adId) {
            throw new NotFoundException("Комментарий с id " + commentId + " не принадлежит объявлению с id " + adId);
        }
        return commentMapper.toDto(adsComment);
    }

    @Override
    public boolean deleteComment(Integer adId, Integer commentId, Authentication authentication) {
        AdsComment adsComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий с id " + commentId + " не найден!"));
        //User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        //if (adsComment.getAuthor().getEmail().equals(user.getEmail()) || user.getRole().getAuthority().equals("ADMIN")) {
        if (SecurityUtils.checkPermissionToAdsComment(adsComment)) {
            if (adsComment.getAds().getId() != adId) {
                throw new NotFoundException("Комментарий с id " + commentId + " не принадлежит объявлению с id " + adId);
            }
            commentRepository.delete(adsComment);
            return true;
        }
        return false;
    }

    @Override
    public CommentDto updateComment(Integer adId, Integer commentId, CommentDto updateComment, Authentication authentication) {

        AdsComment updatedComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Комментарий с id " + commentId + " не найден!"));
        //User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        //if (updatedComment.getAuthor().getEmail().equals(user.getEmail()) || user.getRole().getAuthority().equals("ADMIN")) {
        if (SecurityUtils.checkPermissionToAdsComment(updatedComment)) {
            if (updatedComment.getAds().getId() != adId) {
                throw new NotFoundException("Комментарий с id " + commentId + " не принадлежит объявлению с id " + adId);
            }
            updatedComment.setText(updateComment.getText());
            commentRepository.save(updatedComment);
            return commentMapper.toDto(updatedComment);
        }
        return updateComment;
    }
}