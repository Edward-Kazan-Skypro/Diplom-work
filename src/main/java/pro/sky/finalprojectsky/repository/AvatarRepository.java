package pro.sky.finalprojectsky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.finalprojectsky.model.Avatar;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
