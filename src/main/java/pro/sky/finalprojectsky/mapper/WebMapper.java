package pro.sky.finalprojectsky.mapper;

import org.mapstruct.Named;
import pro.sky.finalprojectsky.entity.User;

import java.util.Collection;
import java.util.List;

/**
 * Interface of WebMapper
 */
public interface WebMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<D> toDto(Collection<E> entity);

    List<E> toEntity(Collection<D> dto);

   /* @Named(value = "buildLink")
    default String buildLink(User user){
        return "/images/" + user.getImage().getId() + "/image";
    }*/

}