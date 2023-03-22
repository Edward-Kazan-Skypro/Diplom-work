package pro.sky.finalprojectsky.service.impl;

import org.springframework.stereotype.Component;
import pro.sky.finalprojectsky.model.Comment;
import pro.sky.finalprojectsky.repository.CommentsRepository;

@Component
public class CommentsService {

    private final CommentsRepository commentsRepository;

    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    //Нужен метод для поиска комментария
    //Проблема в том, что комментарий не отдельная сущность, а часть сущности объявление
    //И в одном объявлении может быть несколько комментариев
    //Поэтому, чтобы найти комментарий, надо вначале найти объявление, в котором будем искать комментарий
    //И уже в найденном объявления искать этот комментарий

    public Comment findCommentById(Integer id){
            return commentsRepository.findById(id).orElse(new Comment());
    }
}
