package pro.sky.finalprojectsky.service;


import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import pro.sky.finalprojectsky.model.Comment;
import pro.sky.finalprojectsky.model.FullAds;
import pro.sky.finalprojectsky.repository.AdsRepository;
import pro.sky.finalprojectsky.repository.CommentsRepository;

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
}
