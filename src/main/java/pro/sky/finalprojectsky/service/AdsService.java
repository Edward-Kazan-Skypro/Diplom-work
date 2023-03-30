package pro.sky.finalprojectsky.service;

import pro.sky.finalprojectsky.dto.CommentDto;
import pro.sky.finalprojectsky.model.Comment;

public interface AdsService {

    boolean removeAds (Integer id);

    CommentDto updateAdsComment (Integer adId, Integer commentId, Comment body);
}
