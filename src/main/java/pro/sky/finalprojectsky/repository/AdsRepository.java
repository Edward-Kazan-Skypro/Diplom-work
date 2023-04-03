package pro.sky.finalprojectsky.repository;

import liquibase.repackaged.net.sf.jsqlparser.util.validation.metadata.NamedObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.finalprojectsky.model.Ads;

import java.util.List;
import java.util.Set;

@Repository
public interface AdsRepository extends JpaRepository<Ads, Integer> {
    void deleteById(Integer id);


    /**
     * Метод для получения всех объявлений пользователя
     * @param id
     * @return
     */
    List<Ads> findAllByUserId(Integer id);


}
