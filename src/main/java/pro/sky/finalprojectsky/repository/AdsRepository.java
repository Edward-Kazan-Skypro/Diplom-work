package pro.sky.finalprojectsky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.finalprojectsky.model.FullAds;

import java.util.List;

@Repository
public interface AdsRepository extends JpaRepository<FullAds, Integer> {

    // Метод для получения всех объявлений пользователя
    List<FullAds> findAllByUserId (Integer userId);
}
