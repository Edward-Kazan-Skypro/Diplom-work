package pro.sky.finalprojectsky.service;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Component;
import pro.sky.finalprojectsky.dto.CommentDto;

import java.util.List;
/**
 * Интерфейс сервиса для работы с комментариями
 */
@Component
public interface CommentsService {
    /**
     * Добавление комментария к объявлению
     *
     * @param adKey         ID объявления
     * @param commentDto Объект комментария
     * @return AdsComment
     */
    CommentDto addComment(long adKey, CommentDto commentDto);

    /**
     * Получение всех комментариев определённого объявления
     *
     * @param adKey ID объявления
     * @return Collection<AdsComment>
     */
    List<CommentDto> getComments(long adKey);

    /**
     * Получение комментария по ID
     *
     * @param id    ID комментария
     * @param adKey ID объявления
     * @return AdsComment
     */
    CommentDto getComment(long adKey, long id);

    /**
     * Удаление комментария по ID
     *
     * @param id             ID комментария
     * @param adKey          ID объявления
     * @param authentication Аутентифицированный пользователь
     * @return Возвращает true если комментарий удалён, иначе false.
     */
    boolean deleteComment(long adKey, long id, Authentication authentication);

    /**
     * Изменение комментария по ID
     *
     * @param id               ID комментария
     * @param adKey            ID объявления
     * @param updateComment Изменённый комментарий
     * @param authentication   Аутентифицированный пользователь
     * @return AdsComment      Изменённый комментарий.
     */
    CommentDto updateComment(long adKey, long id, CommentDto updateComment, Authentication authentication);

    boolean deleteComment(long adKey, long id, org.springframework.security.core.Authentication authentication);

    CommentDto updateComment(long adKey, long id, CommentDto updateComment, org.springframework.security.core.Authentication authentication);
}