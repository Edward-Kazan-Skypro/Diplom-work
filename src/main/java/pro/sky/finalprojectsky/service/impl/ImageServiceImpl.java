package pro.sky.finalprojectsky.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;
import pro.sky.finalprojectsky.dto.AdsDto;
import pro.sky.finalprojectsky.dto.UserDto;
import pro.sky.finalprojectsky.entity.Ads;
import pro.sky.finalprojectsky.entity.Image;
import pro.sky.finalprojectsky.entity.User;
import pro.sky.finalprojectsky.mapper.AdsMapper;
import pro.sky.finalprojectsky.mapper.UserMapper;
import pro.sky.finalprojectsky.repository.AdsRepository;
import pro.sky.finalprojectsky.repository.ImageRepository;
import pro.sky.finalprojectsky.repository.UserRepository;
import pro.sky.finalprojectsky.security.SecurityUtils;
import pro.sky.finalprojectsky.service.ImageService;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import static java.nio.file.StandardOpenOption.CREATE_NEW;

/**
 * Реализация сервиса для работы с картинками
 */
@RequiredArgsConstructor
//@Transactional
@Service
public class ImageServiceImpl implements ImageService {
    Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Value("${path.to.images.folder}")
    private String imagesDir;

    private final ImageRepository imagesRepository;

    private final AdsRepository adsRepository;

    private final UserRepository userRepository;

    private final AdsMapper adsMapper;

    private final UserMapper userMapper;

    /**
     * Сохранение картинки в БД
     *
     * @param imageFile Объект картинка
     * @return Images сохраненное изображение
     * @throws IOException exception
     *                     Вызывает метод:
     *                     * {@link #getExtensions(String fileName)}
     */
    @Override
    public Image uploadAdsImage(MultipartFile imageFile, Ads ads) throws IOException {
        logger.info("Was invoked method for upload ads image");
        Path filePath = Path.of(imagesDir, "ads_image_" + ads.getId() + "." + getExtensions(Objects.requireNonNull(imageFile.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = imageFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
        Image images = new Image();
        images.setFilePath(filePath.toString());
        images.setFileSize(imageFile.getSize());
        images.setMediaType(imageFile.getContentType());
        images.setImage(imageFile.getBytes());
        images.setAds(ads);
        return imagesRepository.save(images);
    }

    @Override
    public Image uploadUserImage(MultipartFile imageFile, Authentication authentication) throws IOException {
        logger.info("Was invoked method for upload user image");
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        Path filePath = Path.of(imagesDir, "user_image_" + user.getId() + "." + getExtensions(Objects.requireNonNull(imageFile.getOriginalFilename())));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = imageFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
        Image images = new Image();
        images.setFilePath(filePath.toString());
        images.setFileSize(imageFile.getSize());
        images.setMediaType(imageFile.getContentType());
        images.setImage(imageFile.getBytes());
        images.setUser(user);
        return imagesRepository.save(images);
    }

    /**
     * Обновление картинки объявления
     *
     * @param imageFile      Файл картинки
     * @param authentication Файл аутентификации
     * @param adsId          ID объявления
     * @return AdsDto         объявление
     * @throws IOException       exception
     * @throws NotFoundException Объявление не найдено
     */
    @Override
    public AdsDto updateAdsImage(MultipartFile imageFile, Authentication authentication, long adsId) throws IOException {
        logger.info("Was invoked method for update ads image");

        Ads ads = adsRepository.findById(adsId).orElseThrow(() -> new NotFoundException("Объявление с id " + adsId + " не найдено!"));
        logger.warn("ad by id {} not found", adsId);
        //User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        if (SecurityUtils.checkPermissionToAds(ads)) {
            Image updatedImage = imagesRepository.findByAdsId(adsId);
            Path filePath = Path.of(updatedImage.getFilePath());
            Files.deleteIfExists(filePath);
            try (
                    InputStream is = imageFile.getInputStream();
                    OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                    BufferedInputStream bis = new BufferedInputStream(is, 1024);
                    BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
            ) {
                bis.transferTo(bos);
            }
            updatedImage.setFileSize(imageFile.getSize());
            updatedImage.setMediaType(imageFile.getContentType());
            updatedImage.setImage(imageFile.getBytes());
            ads.setImage(imagesRepository.save(updatedImage));
            adsRepository.save(ads);
        }
        return adsMapper.toDto(ads);
    }

    @Override
    public UserDto updateUserImage(MultipartFile imageFile, Authentication authentication) throws IOException {
        logger.info("Was invoked method for update user image");
        String userName = authentication.getName();
        User user = userRepository.findByEmail(userName).orElseThrow(() -> new NotFoundException("Пользователь с именем " + authentication.getName() + " не найден!"));
        logger.warn("user by name {} not found", userName);
        Image updatedUserImage = imagesRepository.findByUserId(user.getId());
        Path filePath = Path.of(updatedUserImage.getFilePath());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = imageFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }
        updatedUserImage.setFileSize(imageFile.getSize());
        updatedUserImage.setMediaType(imageFile.getContentType());
        updatedUserImage.setImage(imageFile.getBytes());
        user.setImage(imagesRepository.save(updatedUserImage));
        userRepository.save(user);

        return userMapper.toDto(user);
    }


    private String getExtensions(String fileName) {
        logger.info("Was invoked method for get extensions");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * Получение картинки по ID
     *
     * @param id Id картинки
     * @return image изображение
     * @throws NotFoundException Картинка не найдена
     */
    @Transactional(readOnly = true)
    @Override
    public Image getImage(long id) {
        logger.info("Was invoked method for get image by id");
        return imagesRepository.findById(id).orElseThrow(() -> new NotFoundException("Картинка с id " + id + " не найдена!"));
    }

    /**
     * Получение массива байтов(для фронта)
     *
     * @param id изображения
     * @return image изображение
     * @throws NotFoundException Картинка не найдена
     */
    @Transactional(readOnly = true)
    @Override
    public byte[] getImageBytesArray(long id) {
        logger.info("Was invoked method for get image bates array");
        Image images = imagesRepository.findById(id).orElseThrow(() -> new NotFoundException("Картинка с id " + id + " не найдена!"));
        return images.getImage();
    }

    /**
     * Удаление картинки по ID
     *
     * @param id Id картинки
     * @throws IOException exception
     */
    @Override
    public void removeImage(long id) throws IOException {
        logger.info("Was invoked method for delete image by id");
        Image images = imagesRepository.findById(id).orElseThrow(() -> new NotFoundException("Картинка с id " + id + " не найдена!"));
        logger.warn("image by id {} not found", id);
        Path filePath = Path.of(images.getFilePath());
        images.getAds().setImage(null);
        imagesRepository.deleteById(id);
        Files.deleteIfExists(filePath);
    }
}