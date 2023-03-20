package pro.sky.finalprojectsky.service;


import org.springframework.stereotype.Component;
import pro.sky.finalprojectsky.model.Comment;
import pro.sky.finalprojectsky.model.FullAds;
import pro.sky.finalprojectsky.repository.AdsRepository;
import pro.sky.finalprojectsky.repository.CommentsRepository;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdsService {

    private final AdsRepository adsRepository;
    private final CommentsRepository commentsRepository;


    public AdsService(AdsRepository adsRepository, CommentsRepository commentsRepository) {
        this.adsRepository = adsRepository;
        this.commentsRepository = commentsRepository;
    }


    /**
     * Метод получения объявления по его id.
     *
     * @param id
     * @return
     * @author Мухаметзянов Эдуард
     */
    public FullAds getAdsById(Integer id) {
        if (adsRepository.findById(id).isPresent()) {
            return adsRepository.findById(id).get();
        } else {
            return null;
        }
    }

    /**
     * Метод удаления объявления по его id.
     *
     * @param id
     * @return
     * @author Мухаметзянов Эдуард
     */
    public boolean deleteAdsById(Integer id) {
        adsRepository.deleteById(id);
        return adsRepository.findById(id).isEmpty();
    }

    /*public boolean updateCommentInAds(Integer adsId, Integer commentsId, Comment comment) {
        if (adsRepository.findById(adsId).isPresent()) {
            //Находим объявление по его id
            FullAds ads = getAdsById(adsId);
            Comment oldComment = getCommentById(adsId, commentsId);
            if (oldComment != null) {
                //Получим список всех комментариев в этом объявлении
               List<Comment> commentsList = new ArrayList<>(ads.getCommentList());
                //Переберем список комментариев и удалим из него нужный нам комментарий
                //Точнее, учитывая особенность forEach, добавим в новый список комментариев все, кроме удаляемого
                ArrayList<Comment> refinedCommentsList = new ArrayList<>();
                for (Comment c : commentsList) {
                    if (c.getPk() != commentsId) {
                        refinedCommentsList.add(c);
                    }
                }
                //Теперь у нас есть очищенный список комментариев.
                //Добавим к нему комментарий
                refinedCommentsList.add(comment);
                //Добавим список комментариев с новым комментарием к объявлению.
                ads.setCommentList(refinedCommentsList);
                //Сохраним объявление с обновленным списком комментариев
                adsRepository.save(ads);
                return true;
            }
        }
        return false;
    }*/


    //Метод для получения списка комментариев из объявления
   /* public List<Comment> getCommentsListFromAds(Integer adsId) {
        //Находим объявление по его id
        FullAds ads = getAdsById(adsId);
        //Получим все комментарии из этого объявления
        List<Comment> allCommentsFromAds = new ArrayList<>(ads.getCommentList());
        return allCommentsFromAds;
    }*/

    /*public Comment getCommentById(Integer adsId, Integer commentsId) {
        ArrayList<Comment> allCommentsFromAds = new ArrayList<>(getCommentsListFromAds(adsId));
        Comment selectedComment = null;
        //Ищем нужный нам комментарий, ищем по id комментария
        for (Comment comment : allCommentsFromAds) {
            if (comment.getPk() == commentsId) {
                selectedComment = comment;
            }
        }
        //А вдруг комментария с таким id нет?
        //При создании нового комментария значения все его значения полей null
        //Тогда пусть в вызывающем методе будет проверка на null найденного комментария
        return selectedComment;
    }*/


    // Метод для получения всех объявлений
    public List<FullAds> getAllAds() {
        // получаем все объявления с базы данных и помещаем их в коллекцию
        List<FullAds> allAds = adsRepository.findAll();
        // возвращаем коллекцию
        return allAds;
    }

    // Метод для добавления нового объявления
    public FullAds addAds(FullAds ads) {
        // сохраняем новое объявление в базе данных
        FullAds newAds = adsRepository.save(ads);
        // возвращаем новое объявление
        return newAds;
    }

    // Метод для получения моих объявлений
    public List<FullAds> getAdsMe(Integer userId) {
        // получаем все объявления, созданные пользователем с заданным id и помещаем их в коллекцию
        //List<FullAds> adsMe = adsRepository.findAllByUserId(userId);
        // возвращаем коллекцию
        return null;
    }




    }










