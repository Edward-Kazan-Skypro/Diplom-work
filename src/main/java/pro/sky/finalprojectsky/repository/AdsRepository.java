package pro.sky.finalprojectsky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.finalprojectsky.entity.Ads;
import java.util.List;

/**
 * Repository AdsRepository (advertisement/объявление).
 */
public interface AdsRepository extends JpaRepository<Ads, Integer> {
    List<Ads> findAllByAuthorId(Integer id);
}