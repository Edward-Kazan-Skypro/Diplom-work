package pro.sky.finalprojectsky.service;

import org.springframework.stereotype.Component;
import pro.sky.finalprojectsky.model.Comment;
import pro.sky.finalprojectsky.repository.CommentsRepository;

@Component
public class CommentsService {

    private final CommentsRepository commentsRepository;

    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public Comment findCommentById(Long id){
            return commentsRepository.findById(id).orElse(new Comment());
    }
}
