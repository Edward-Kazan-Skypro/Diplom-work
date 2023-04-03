package pro.sky.finalprojectsky.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.finalprojectsky.dto.AdsDto;
import pro.sky.finalprojectsky.dto.CreateAdsDto;
import pro.sky.finalprojectsky.dto.FullAdsDto;
import pro.sky.finalprojectsky.entity.Ads;
import java.io.IOException;
import java.util.List;

@Component
public interface AdsService {

    AdsDto createAds(CreateAdsDto createAdsDto, MultipartFile imageFile) throws IOException;
    Ads getAds(Integer id);
    FullAdsDto getFullAdsDto(Integer id);
    List<AdsDto> getAllAds();
    boolean removeAds(Integer id, Authentication authentication) throws IOException;
    AdsDto updateAds(Integer id, AdsDto updatedAdsDto, Authentication authentication);
    List<AdsDto> getAdsMe();
}