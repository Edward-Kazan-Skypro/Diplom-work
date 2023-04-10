package pro.sky.finalprojectsky.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import pro.sky.finalprojectsky.dto.AdsDto;
import pro.sky.finalprojectsky.dto.CreateAdsDto;
import pro.sky.finalprojectsky.dto.FullAdsDto;
import pro.sky.finalprojectsky.entity.Ads;
import pro.sky.finalprojectsky.entity.AdsComment;
import pro.sky.finalprojectsky.entity.Image;
import pro.sky.finalprojectsky.entity.User;
import pro.sky.finalprojectsky.mapper.AdsMapper;
import pro.sky.finalprojectsky.repository.AdsCommentRepository;
import pro.sky.finalprojectsky.repository.AdsRepository;
import pro.sky.finalprojectsky.repository.UserRepository;
import pro.sky.finalprojectsky.security.SecurityUtils;
import pro.sky.finalprojectsky.service.AdsService;
import pro.sky.finalprojectsky.service.ImageService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;

    private final AdsCommentRepository adsCommentRepository;

    private final UserRepository userRepository;

    private final ImageService imagesService;

    private final AdsMapper adsMapper;

    @Override
    public AdsDto createAds(CreateAdsDto createAdsDto, MultipartFile imageFile) throws IOException {
        //Сохраним картинку в БД
        Image adsImage = imagesService.saveImage(imageFile);
        //Получаем юзера
        User user = userRepository.findByEmail(SecurityContextHolder.getContext()
                .getAuthentication().getName()).orElseThrow();
        Ads ads = adsMapper.convertCreateAdsDtoToEntity(createAdsDto, user, adsImage);
        ads.setImage(adsImage);
        adsRepository.save(ads);
        return adsMapper.convertEntityToAdsDto(ads);

    }

    @Transactional(readOnly = true)
    @Override
    public Ads getAds(Integer id) {
        return adsRepository.findById(id).orElseThrow(() -> new NotFoundException("Объявление с id " + id + " не найдено!"));
    }

    @Transactional(readOnly = true)
    @Override
    public FullAdsDto getFullAdsDto(Integer id) {
        return adsMapper.convertEntityToFullAdsDto(adsRepository.findById(id).orElseThrow(() -> new NotFoundException("Объявление с id " + id + " не найдено!")));
    }

    @Override
    public List<AdsDto> getAllAds() {
        ArrayList<Ads> adsArrayList = new ArrayList<>(adsRepository.findAll());
        ArrayList<AdsDto> adsDtoArrayList = new ArrayList<>();
        for (Ads ads: adsArrayList){
            adsDtoArrayList.add(adsMapper.convertEntityToAdsDto(ads));
        }
        return adsDtoArrayList;
    }

    @Override
    public boolean removeAds(Integer id, Authentication authentication) throws IOException {
        Ads ads = adsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Объявление с id " + id + " не найдено!"));
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        //if (ads.getAuthor().getEmail().equals(user.getEmail()) || user.getRole().getAuthority().equals("ADMIN")) {
        if (SecurityUtils.checkPermissionToAds(ads)) {
            List<Integer> adsComments = adsCommentRepository.findAll().stream()
                    .filter(adsComment -> adsComment.getAds().getId() == ads.getId())
                    .map(AdsComment::getId)
                    .collect(Collectors.toList());
            adsCommentRepository.deleteAllById(adsComments);
            imagesService.removeAdsImage(ads.getImage().getId());
            adsRepository.delete(ads);
            return true;
        }
        return false;
    }

    @Override
    public AdsDto updateAds(Integer id, AdsDto updateAdsDto, Authentication authentication) {
        Ads updatedAds = adsRepository.findById(id).orElseThrow(() -> new NotFoundException("Объявление с id " + id + " не найдено!"));
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        if (SecurityUtils.checkPermissionToAds(updatedAds)){
            updatedAds.setTitle(updateAdsDto.getTitle());
            updatedAds.setPrice(updateAdsDto.getPrice());
            adsRepository.save(updatedAds);
            return adsMapper.convertEntityToAdsDto(updatedAds);
        }
        return updateAdsDto;
    }

    @Transactional(readOnly = true)
    @Override
    public List<AdsDto> getAdsMe() {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext()
                .getAuthentication().getName()).orElseThrow();
        List<Ads> adsList = adsRepository.findAllByAuthorId(user.getId());
        List<AdsDto> adsDtoList = new ArrayList<>();
        for (Ads ads: adsList) {
            adsDtoList.add(adsMapper.convertEntityToAdsDto(ads));
        }
        return adsDtoList;
    }
}