package pro.sky.finalprojectsky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.finalprojectsky.model.FullAds;

@Repository
public interface AdsRepository extends JpaRepository<FullAds, Long> {
}
