package pro.sky.finalprojectsky.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.finalprojectsky.dto.AdsCommentDto;
import pro.sky.finalprojectsky.dto.AdsDto;
import pro.sky.finalprojectsky.dto.CreateAdsDto;
import pro.sky.finalprojectsky.dto.FullAdsDto;
import pro.sky.finalprojectsky.entity.Ads;

import java.io.IOException;
import java.util.List;

/**
 * Интерфейс сервиса для работы с объявлениями
 */
public interface AdsService {

    /**
     * Добавление объявления
     *
     * @param createAdsDto Объект объявления
     * @param imageFile    Картинка объявления
     * @return Ads
     */

    AdsDto createAds(CreateAdsDto createAdsDto, MultipartFile imageFile) throws IOException;

    /**
     * Получение объявления по ID
     *
     * @param id ID объявления
     * @return Ads
     */
    Ads getAds(Integer id);

    /**
     * Получение DTO с полной информацией об объекте
     */
    FullAdsDto getFullAdsDto(Integer id);

    /**
     * Получение всех объявлений
     *
     * @return Collection<Ads>
     */
    List<AdsDto> getAllAds();

    /**
     * Удаление объявления по ID
     *
     * @param id             ID объявления
     * @param authentication Аутентифицированный пользователь
     * @return Возвращает true если объявление удалено, иначе false.
     */
    boolean removeAds(Integer id, Authentication authentication) throws IOException;

    /**
     * Изменение объявления по ID
     *
     * @param id             ID объявления
     * @param updatedAdsDto  Изменённое объявление
     * @param authentication Аутентифицированный пользователь
     * @return Ads Изменённое объявление.
     */
    AdsDto updateAds(Integer id, AdsDto updatedAdsDto, Authentication authentication);

    /**
     * Получение всех объявлений аутентифицированного пользователя
     *
     * @return Collection<Ads>
     */
    List<AdsDto> getAdsMe();

    /**
     * Добавление комментария к объявлению
     *
     * @param adKey         ID объявления
     * @param adsCommentDto Объект комментария
     * @return AdsComment
     */
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