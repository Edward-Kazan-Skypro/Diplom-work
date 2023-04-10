package pro.sky.finalprojectsky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.finalprojectsky.entity.Image;

/**
 * Repository ImageRepository (advertisement image/изображение в объявлениях).
 */
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findByAdsId(long adsId);

}