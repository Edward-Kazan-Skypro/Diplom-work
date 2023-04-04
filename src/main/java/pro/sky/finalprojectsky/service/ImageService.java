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
    Image uploadImage(MultipartFile imageFile, Ads ads) throws IOException;

    AdsDto updateImage(MultipartFile imageFile, Authentication authentication, Integer adsId) throws IOException;

    Image getImage(Integer id);

    byte[] getImageBytesArray(Integer id);

    void removeImage(Integer id) throws IOException;
}