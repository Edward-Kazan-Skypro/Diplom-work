package pro.sky.finalprojectsky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.finalprojectsky.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
