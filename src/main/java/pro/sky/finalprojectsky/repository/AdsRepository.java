package pro.sky.finalprojectsky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.finalprojectsky.model.Ads;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Integer> {
    void deleteById(Integer id);

    // Метод для получения всех объявлений пользователя
    //List<FullAds> findAllByUserId (Integer id);
}
