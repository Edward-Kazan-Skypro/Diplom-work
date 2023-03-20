package pro.sky.finalprojectsky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.finalprojectsky.model.Image;
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
