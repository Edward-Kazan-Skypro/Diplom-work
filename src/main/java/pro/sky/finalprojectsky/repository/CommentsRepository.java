package pro.sky.finalprojectsky.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sky.finalprojectsky.model.Comment;

@Repository
public interface CommentsRepository extends JpaRepository<Comment, Integer> {
}
