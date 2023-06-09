package pro.sky.finalprojectsky.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import pro.sky.finalprojectsky.dto.Role;
import pro.sky.finalprojectsky.entity.Ads;
import pro.sky.finalprojectsky.entity.AdsComment;

public class SecurityUtils {

    private SecurityUtils() {
    }

    public static MyUserDetails getUserDetailsFromContext() {
        return (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static long getUserIdFromContext() {
        return getUserDetailsFromContext().getId();
    }

    public static boolean checkPermissionToAds(Ads ads) {
        MyUserDetails userDetails = getUserDetailsFromContext();

        if (!userDetails.getAuthorities().contains(Role.ADMIN) && userDetails.getId() != ads.getAuthor().getId()) {
            throw new AccessDeniedException("Чтобы изменить/удалить объявление, нужно иметь роль ADMIN или быть владельцем этого объявления");
        }
        return true;
    }

    public static boolean checkPermissionToAdsComment(AdsComment adsComment) {
        MyUserDetails userDetails = getUserDetailsFromContext();

        if (!userDetails.getAuthorities().contains(Role.ADMIN) && userDetails.getId() != adsComment.getAuthor().getId()) {
            throw new AccessDeniedException("Чтобы изменить/удалить комментарий, нужно иметь роль ADMIN или быть владельцем этого комментария");
        }
        return true;
    }
}