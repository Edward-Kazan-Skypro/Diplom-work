package pro.sky.finalprojectsky.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.finalprojectsky.dto.AdsDto;
import pro.sky.finalprojectsky.entity.Ads;
import pro.sky.finalprojectsky.entity.Image;
import java.io.IOException;


@Component
public interface ImageService {

    Image saveImage(MultipartFile imageFile) throws IOException;
    Image uploadAdsImage(MultipartFile imageFile, Ads ads) throws IOException;

    AdsDto updateAdsImage(MultipartFile imageFile, Authentication authentication, Integer adsId) throws IOException;

    Image getAdsImage(Integer id);

    byte[] getImageBytesArray(Integer id);

    void removeAdsImage(Integer id) throws IOException;

    boolean uploadUserImage(MultipartFile imageFile, Authentication authentication) throws IOException;

    boolean updateUserImage(MultipartFile imageFile, Authentication authentication) throws IOException;

}