package pro.sky.finalprojectsky.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.finalprojectsky.dto.AdsDto;
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
    Image uploadImage(MultipartFile imageFile, Ads ads) throws IOException;

    /**
     * Обновление картинки объявления
     */

    AdsDto updateImage(MultipartFile imageFile, Authentication authentication, Integer adsId) throws IOException;

    /**
     * Получение картинки по ID
     */
    Image getImage(Integer id);

    /**
     * Получение массива байтов(для фронта)
     */
    byte[] getImageBytesArray(Integer id);

    /**
     * Удаление картинки по ID
     */
    void removeImage(Integer id) throws IOException;
}