package pro.sky.finalprojectsky.service;


import org.mapstruct.factory.Mappers;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pro.sky.finalprojectsky.dto.*;
import pro.sky.finalprojectsky.mappers.AdsMapper;
import pro.sky.finalprojectsky.mappers.CommentMapper;
import pro.sky.finalprojectsky.exceptions.AdsNotFoundException;
import pro.sky.finalprojectsky.exceptions.UserNotFoundException;
import pro.sky.finalprojectsky.mappers.FullAdsMapper;
import pro.sky.finalprojectsky.model.Comment;
import pro.sky.finalprojectsky.model.Ads;
import pro.sky.finalprojectsky.model.User;
import pro.sky.finalprojectsky.repository.AdsRepository;
import pro.sky.finalprojectsky.repository.CommentsRepository;
import pro.sky.finalprojectsky.repository.UserRepository;

import java.util.ArrayList;

import java.util.List;

@Component
public class AdsService {

    private final AdsRepository adsRepository;
    private final CommentsRepository commentsRepository;

    private final UserRepository userRepository;
    private final AdsMapper adsMapper = Mappers.getMapper(AdsMapper.class);
    private final FullAdsMapper fullAdsMapper = Mappers.getMapper(FullAdsMapper.class);


    public AdsService(AdsRepository adsRepository,
                      CommentsRepository commentsRepository,
                      UserRepository userRepository) {
        this.adsRepository = adsRepository;
        this.commentsRepository = commentsRepository;
        this.userRepository = userRepository;
    }

    public boolean removeAds(Integer id) {
        if (adsRepository.existsById(id)) {
            adsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public CommentDto updateAdsComment(Integer adId, Integer commentId, Comment newComment) {
        if (adsRepository.existsById(adId)) {
            //найдем объявление
            Ads ads = adsRepository.getReferenceById(adId);
            //получаем из объявления список комментариев
            ArrayList<Comment> commentArrayList = (ArrayList<Comment>) ads.getComments();
            //в списке ищем комментарий, который будем обновлять
            Comment comment = new Comment();
            for (Comment c : commentArrayList) {
                if (c.getPk() == commentId) {
                    comment = newComment;
                    break;
                }
            }
            //сохраняем обновленный комментарий в БД
            commentsRepository.save(comment);
        }
        //вернем ДТО
        return CommentMapper.INSTANCE.commentToDto(commentsRepository.getReferenceById(commentId));
    }

    /**
     * Метод получения всех объявлений
     *
     * @return список объявлений
     */
    public List<AdsDto> getAllAds() {
        List<Ads> allAds = adsRepository.findAll();
        return AdsMapper.INSTANCE.adsListToAdsDto(allAds);
    }

    // Метод для добавления нового объявления
    public Ads addAds(Ads ads) {
        // сохраняем новое объявление в базе данных
        Ads newAds = adsRepository.save(ads);
        // возвращаем новое объявление
        return newAds;
    }

    /**
     * Метод получения объявлений авторизованного пользователя
     *
     * @return список объявлений пользователя
     */

    public List<AdsDto> getAdsMe() {
        User user = userRepository.findById(Integer.valueOf(SecurityContextHolder.getContext()
                .getAuthentication().getName())).orElseThrow((UserNotFoundException::new));
        List<Ads> adsList = adsRepository.findAllByUserId(user.getId());
        return adsMapper.adsListToAdsDto(adsList);
    }

    /**
     * Метод получения информации об объявлении
     *
     * @param id идентификатор объявления
     * @return полная информация об объявлении
     */

    public FullAdsDto getAds(Integer id) {
        return fullAdsMapper.adsToFullAdsDto(adsRepository.findById(id).orElseThrow((AdsNotFoundException::new)));
    }


    /**
     * Метод обновления информации об объявлении
     *
     * @param id   идентификатор объявления
     * @param body передаваемая информация
     * @return обновленная информация
     */
    public boolean updateAds(Integer id, AdsDto body) {
        if (adsRepository.existsById(body.getId())) {
            Ads ads = adsRepository.getReferenceById(body.getId());
            ads = AdsMapper.INSTANCE.adsDtoToAds(body);
            adsRepository.save(ads);
            return true;
        }
        return false;
    }

    /**
     * Метод удаления объявления
     *
     * @param id идентификатор объявления
     * @return удаленное объявление
     */

    public boolean removeAd(Integer id) {
        Ads ads = adsMapper.adsDtoToAds(AdsDto.builder().build());
        ads.setId(id);
        if (adsRepository.existsById(id)) {
            adsRepository.deleteById(id);
            return true;
        }
        return false;

    }

}
