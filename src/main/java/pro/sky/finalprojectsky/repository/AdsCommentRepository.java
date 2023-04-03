package pro.sky.finalprojectsky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.finalprojectsky.entity.AdsComment;
import java.util.List;
import java.util.Optional;

public interface AdsCommentRepository extends JpaRepository<AdsComment, Integer> {

    List<AdsComment> findAllByAdsId(long adsId);

    Optional<AdsComment> findByIdAndAdsId(long id, long adsId);

}