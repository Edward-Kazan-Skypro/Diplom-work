package pro.sky.finalprojectsky.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pro.sky.finalprojectsky.dto.AdsCommentDto;
import java.util.List;

@Component
public interface CommentService {

    AdsCommentDto addAdsComment(Integer adKey, AdsCommentDto adsCommentDto);

    /**
     * Получение всех комментариев определённого объявления
     *
     * @param adKey ID объявления
     * @return Collection<AdsComment>
     */
    List<AdsCommentDto> getAdsComments(Integer adKey);

    /**
     * Получение комментария по ID
     *
     * @param id    ID комментария
     * @param adKey ID объявления
     * @return AdsComment
     */
    AdsCommentDto getAdsComment(Integer adKey, Integer id);

    /**
     * Удаление комментария по ID
     *
     * @param id             ID комментария
     * @param adKey          ID объявления
     * @param authentication Аутентифицированный пользователь
     * @return Возвращает true если комментарий удалён, иначе false.
     */
    boolean deleteAdsComment(Integer adKey, Integer id, Authentication authentication);

    /**
     * Изменение комментария по ID
     *
     * @param id               ID комментария
     * @param adKey            ID объявления
     * @param updateAdsComment Изменённый комментарий
     * @param authentication   Аутентифицированный пользователь
     * @return AdsComment      Изменённый комментарий.
     */
    AdsCommentDto updateAdsComment(Integer adKey, Integer id, AdsCommentDto updateAdsComment, Authentication authentication);
}