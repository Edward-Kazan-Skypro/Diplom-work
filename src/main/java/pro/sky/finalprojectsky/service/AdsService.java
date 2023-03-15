package pro.sky.finalprojectsky.service;


import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;
import pro.sky.finalprojectsky.model.FullAds;
import pro.sky.finalprojectsky.repository.AdsRepository;

@Component
public class AdsService {

    private final AdsRepository adsRepository;


    public AdsService(AdsRepository adsRepository) {
        this.adsRepository = adsRepository;
    }

    public FullAds getAdsById (Long id){
        try {
            return adsRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
