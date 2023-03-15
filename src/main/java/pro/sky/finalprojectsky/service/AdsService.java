package pro.sky.finalprojectsky.service;


import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import pro.sky.finalprojectsky.model.Comment;
import pro.sky.finalprojectsky.model.FullAds;
import pro.sky.finalprojectsky.repository.AdsRepository;
import pro.sky.finalprojectsky.repository.CommentsRepository;

import java.util.List;

@Component
public class AdsService {

    private final AdsRepository adsRepository;
    private final CommentsRepository commentsRepository;


    public AdsService(AdsRepository adsRepository, CommentsRepository commentsRepository) {
        this.adsRepository = adsRepository;
        this.commentsRepository = commentsRepository;
    }


    public FullAds getAdsById (Long id){
        try {
            return adsRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteAdsById (Long id){
        adsRepository.deleteById(id);
        return adsRepository.findById(id).isEmpty();
    }

    public boolean deleteCommentInAds (Long adsId, Long commentsId){
        Comment comment = commentsRepository.findById(commentsId).get();

        //Надо дописать метод с учетом того, как в объявление добавляется комментарий !!!!!!!!!!!!!!!
    return false;
    }

    // Метод для получения всех объявлений
    public List<FullAds> getAllAds(){
        // получаем все объявления с базы данных и помещаем их в коллекцию
        List<FullAds> allAds = adsRepository.findAll();
        // возвращаем коллекцию
        return allAds;
    }

    // Метод для добавления нового объявления
    public FullAds addAds(FullAds ads){
        // сохраняем новое объявление в базе данных
        FullAds newAds = adsRepository.save(ads);
        // возвращаем новое объявление
        return newAds;
    }

    // Метод для получения моих объявлений
    public List<FullAds> getAdsMe(Long userId){
        // получаем все объявления, созданные пользователем с заданным id и помещаем их в коллекцию
        List<FullAds> adsMe = adsRepository.findAllByUserId(userId);
        // возвращаем коллекцию
        return adsMe;
    }

}
