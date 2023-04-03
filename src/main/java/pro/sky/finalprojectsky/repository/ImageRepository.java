package pro.sky.finalprojectsky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.finalprojectsky.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Image findByAdsId(long adsId);

}