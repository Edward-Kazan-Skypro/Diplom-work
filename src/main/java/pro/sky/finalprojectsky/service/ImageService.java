package pro.sky.finalprojectsky.service;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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
    pro.sky.finalprojectsky.entity.Image uploadImage(MultipartFile imageFile, Ads ads) throws IOException;

    /**
     * Обновление картинки объявления
     */

    AdsDto updateImage(MultipartFile imageFile, Authentication authentication, long adsId) throws IOException;

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
