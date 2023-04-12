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
import static java.nio.file.StandardOpenOption.CREATE_NEW;

/**
 * Реализация сервиса для работы с картинками
 */
@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {
    private final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Value("${path.to.images.folder}")
    private String imagesDir;

    private final ImageRepository imageRepository;

    private final AdsRepository adsRepository;

    private final UserRepository userRepository;

    private final AdsMapper adsMapper;

    private final UserMapper userMapper;

    /**
     * Сохранение картинки в БД
     *
     * @param imageFile Объект картинка
     * @return Images сохраненное изображение
     * @throws IOException exception     *
     */
    @Override
    public Image uploadAdsImage(MultipartFile imageFile, Ads ads) throws IOException {
        logger.info("Was invoked method for upload ads image");
        Image image = simpleSaveImage(imageFile);
        image.setAds(ads);
        return imageRepository.save(image);
       /* Path filePath = Path.of(imagesDir, "ads_image_" + ads.getId() + "." + getExtensions(Objects.requireNonNull(imageFile.getOriginalFilename())));
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
        return imagesRepository.save(images);*/
    }
    @Override
    public Image simpleSaveImage(MultipartFile imageFile) throws IOException{
        logger.info("Was invoked method for simple save image");
        Path filePath = Path.of(imagesDir, "image_" + (imageFile.getOriginalFilename()));
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
        Image image = new Image();
        image.setFilePath(filePath.toString());
        image.setFileSize(imageFile.getSize());
        image.setMediaType(imageFile.getContentType());
        image.setImage(imageFile.getBytes());
        return imageRepository.save(image);
    }

    @Override
    public Image uploadUserImage(MultipartFile imageFile, Authentication authentication) throws IOException {
        logger.info("Was invoked method for upload user image");
        Image image = simpleSaveImage(imageFile);
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        image.setUser(user);
        return imageRepository.save(image);
       /* Path filePath = Path.of(imagesDir, "user_image_" + user.getId() + "." + getExtensions(Objects.requireNonNull(imageFile.getOriginalFilename())));
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
        Image image = new Image();
        image.setFilePath(filePath.toString());
        image.setFileSize(imageFile.getSize());
        image.setMediaType(imageFile.getContentType());
        image.setImage(imageFile.getBytes());
        image.setUser(user);
        return imagesRepository.save(image);*/
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
        if (SecurityUtils.checkPermissionToAds(ads)) {
            Image newAdsImage = simpleSaveImage(imageFile);
            ads.setImage(newAdsImage);
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
        Image newUserImage = simpleSaveImage(imageFile);
        user.setImage(newUserImage);
        userRepository.save(user);
        return userMapper.toDto(user);
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
        return imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Картинка с id " + id + " не найдена!"));
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
        Image image = imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Картинка с id " + id + " не найдена!"));
        return image.getImage();
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
        Image images = imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Картинка с id " + id + " не найдена!"));
        logger.warn("image by id {} not found", id);
        Path filePath = Path.of(images.getFilePath());
        images.getAds().setImage(null);
        imageRepository.deleteById(id);
        Files.deleteIfExists(filePath);
    }
}