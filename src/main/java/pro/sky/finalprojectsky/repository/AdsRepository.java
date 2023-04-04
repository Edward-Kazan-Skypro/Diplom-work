package pro.sky.finalprojectsky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.finalprojectsky.entity.Ads;
import java.util.List;

public interface AdsRepository extends JpaRepository<Ads, Integer> {

    List<Ads> findAllByAuthorId(Integer id);

}