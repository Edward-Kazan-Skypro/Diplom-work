package pro.sky.finalprojectsky.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.finalprojectsky.dto.AdsDto;
import pro.sky.finalprojectsky.dto.UserDto;
import pro.sky.finalprojectsky.entity.Ads;
import pro.sky.finalprojectsky.entity.Image;
import java.io.IOException;

/**
 * Интерфейс сервиса для работы с картинками
 */
public interface ImageService {

    /**
     * Сохранение картинки в БД
     */
    Image uploadAdsImage(MultipartFile imageFile, Ads ads) throws IOException;

    /**
     * Обновление картинки объявления
     */

    AdsDto updateAdsImage(MultipartFile imageFile, Authentication authentication, long adsId) throws IOException;

    Image uploadUserImage(MultipartFile imageFile, Authentication authentication) throws IOException;

    UserDto updateUserImage(MultipartFile imageFile, Authentication authentication) throws IOException;

    Image simpleSaveImage (MultipartFile imageFile) throws IOException;

    /**
     * Получение картинки по ID
     */
    Image getImage(long id);

    /**
     * Получение массива байтов(для фронта)
     */
    byte[] getImageBytesArray(long id);

    /**
     * Удаление картинки по ID
     */
    void removeImage(long id) throws IOException;
}