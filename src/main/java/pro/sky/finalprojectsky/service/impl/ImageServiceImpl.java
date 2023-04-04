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
import pro.sky.finalprojectsky.entity.Ads;
import pro.sky.finalprojectsky.entity.Image;
import pro.sky.finalprojectsky.entity.User;
import pro.sky.finalprojectsky.mapper.AdsMapper;
import pro.sky.finalprojectsky.repository.AdsRepository;
import pro.sky.finalprojectsky.repository.ImageRepository;
import pro.sky.finalprojectsky.repository.UserRepository;
import pro.sky.finalprojectsky.service.ImageService;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import static java.nio.file.StandardOpenOption.CREATE_NEW;

@RequiredArgsConstructor
@Transactional
@Service
public class ImageServiceImpl implements ImageService {

    @Value("${path.to.images.folder}")

    private String imagesDir;

    private final ImageRepository imagesRepository;

    private final AdsRepository adsRepository;

    private final UserRepository userRepository;

    private final AdsMapper adsMapper;

    @Override
    public Image uploadAdsImage(MultipartFile imageFile, Ads ads) throws IOException {
        Path filePath = Path.of(imagesDir, "ads_" + ads.getId() + "." + getExtensions(Objects.requireNonNull(imageFile.getOriginalFilename())));
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
        images.setUser(null);
        return imagesRepository.save(images);
    }

    @Override
    public AdsDto updateAdsImage(MultipartFile imageFile, Authentication authentication, Integer adsId) throws IOException {
        Ads ads = adsRepository.findById(adsId).orElseThrow(() -> new NotFoundException("Объявление с id " + adsId + " не найдено!"));
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow();
        if (ads.getAuthor().getEmail().equals(user.getEmail()) || user.getRole().getAuthority().equals("ADMIN")) {
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
        images.getAds().setImage(null);
        imagesRepository.deleteById(id);
        Files.deleteIfExists(filePath);
    }

    @Override
    public  boolean uploadUserImage(MultipartFile imageFile, Authentication authentication) throws IOException {
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new NotFoundException("Пользователь с именем " + authentication.getName() + " не найден!"));
        Path filePath = Path.of(imagesDir, "ads_" + user.getId() + "." + getExtensions(Objects.requireNonNull(imageFile.getOriginalFilename())));
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
        images.setAds(null);
        images.setUser(user);
        imagesRepository.save(images);
        return true;
    }
    @Override
    public  boolean updateUserImage(MultipartFile imageFile, Authentication authentication) throws IOException{
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new NotFoundException("Пользователь с именем " + authentication.getName() + " не найден!"));
        if (user.getRole().getAuthority().equals("USER")) {
            Image updatedImage = imagesRepository.findByAdsId(user.getId());
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
            user.setImage(imagesRepository.save(updatedImage));
            userRepository.save(user);
            return true;
        }
        return false;
    }
}