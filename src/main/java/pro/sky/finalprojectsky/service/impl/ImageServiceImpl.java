package pro.sky.finalprojectsky.service.impl;

import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    @Value("${path.to.images.folder}")

    private String imagesDir;

    private final ImageRepository imagesRepository;

    private final AdsRepository adsRepository;

    private final UserRepository userRepository;

    private final AdsMapper adsMapper;

    private final UserMapper userMapper;

    @Override
    public Image uploadAdsImage(MultipartFile imageFile, Ads ads) throws IOException {
        Image image = this.saveImage(imageFile);
        ads.setImage(image);
        adsRepository.save(ads);
        return image;
    }

    @Override
    public Image saveImage(MultipartFile imageFile) throws IOException{
        String fileName = imageFile.getOriginalFilename();
        Path filePath = Path.of(imagesDir, fileName);
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
        image.setAds(null);
        image.setUser(null);
        return imagesRepository.save(image);
    }

    @Override
    public AdsDto updateAdsImage(MultipartFile imageFile, Authentication authentication, Integer adsId) throws IOException {
        Ads ads = adsRepository.findById(adsId).orElseThrow(() -> new NotFoundException("Объявление с id " + adsId + " не найдено!"));
        if (SecurityUtils.checkPermissionToAds(ads)) {
            Image updatedImage = this.saveImage(imageFile);
            updatedImage.setAds(ads);
            ads.setImage(updatedImage);
            adsRepository.save(ads);
        }
        return adsMapper.convertEntityToAdsDto(ads);
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    @Transactional(readOnly = true)
    @Override
    public Image getAdsImage(Integer id) {
        return imagesRepository.findById(id).orElseThrow(() -> new NotFoundException("Картинка с id " + id + " не найдена!"));
    }

    @Transactional(readOnly = true)
    @Override
    public byte[] getImageBytesArray(Integer id) {
        Image images = imagesRepository.findById(id).orElseThrow(() -> new NotFoundException("Картинка с id " + id + " не найдена!"));
        return images.getImage();
    }

    @Override
    public void removeAdsImage(Integer id) throws IOException {
        Image images = imagesRepository.findById(id).orElseThrow(() -> new NotFoundException("Картинка с id " + id + " не найдена!"));
        Path filePath = Path.of(images.getFilePath());
        imagesRepository.deleteById(id);
        Files.deleteIfExists(filePath);
    }

    @Override
    public  Image uploadUserImage(MultipartFile imageFile, Authentication authentication) throws IOException {
        Image image = this.saveImage(imageFile);
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new NotFoundException("Пользователь с именем " + authentication.getName() + " не найден!"));
        user.setImage(image);
        userRepository.save(user);
        return image;
    }
    @Override
    public UserDto updateUserImage(MultipartFile imageFile, Authentication authentication) throws IOException{
        Image image = this.saveImage(imageFile);
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new NotFoundException("Пользователь с именем " + authentication.getName() + " не найден!"));
        user.setImage(image);
        userRepository.save(user);
        return userMapper.convertEntityToUserDto(user);
    }
}