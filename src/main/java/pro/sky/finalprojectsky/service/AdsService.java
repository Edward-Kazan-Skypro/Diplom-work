package pro.sky.finalprojectsky.service;


import org.springframework.stereotype.Component;
import pro.sky.finalprojectsky.dto.*;
import pro.sky.finalprojectsky.mappers.CommentMapper;
import pro.sky.finalprojectsky.model.Comment;
import pro.sky.finalprojectsky.model.FullAds;
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


    public AdsService(AdsRepository adsRepository,
                      CommentsRepository commentsRepository,
                      UserRepository userRepository) {
        this.adsRepository = adsRepository;
        this.commentsRepository = commentsRepository;
        this.userRepository = userRepository;
    }

    public boolean removeAds (Integer id){
        if (adsRepository.existsById(id)){
            adsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public CommentDto updateAdsComment(Integer adId, Integer commentId, Comment newComment) {
        if (adsRepository.existsById(adId)){
            //найдем объявление
            FullAds fullAds = adsRepository.getReferenceById(adId);
            //получаем из объявления список комментариев
            ArrayList<Comment> commentArrayList = (ArrayList<Comment>) fullAds.getComments();
            //в списке ищем комментарий, который будем обновлять
            Comment comment = new Comment();
            for (Comment c: commentArrayList) {
                if (c.getPk() == commentId){
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


    // Метод для получения всех объявлений
    public List<FullAds> getAllAds() {
        // получаем все объявления с базы данных и помещаем их в коллекцию
        List<FullAds> allAds = adsRepository.findAll();
        // возвращаем коллекцию
        return allAds;
    }

    // Метод для добавления нового объявления
    public FullAds addAds(FullAds ads) {
        // сохраняем новое объявление в базе данных
        FullAds newAds = adsRepository.save(ads);
        // возвращаем новое объявление
        return newAds;
    }

    // Метод для получения моих объявлений
    public List<FullAds> getAdsMe(Integer userId) {
        // получаем все объявления, созданные пользователем с заданным id и помещаем их в коллекцию
        //List<FullAds> adsMe = adsRepository.findAllByUserId(userId);
        // возвращаем коллекцию
        return null;
    }
}
