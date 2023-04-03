package pro.sky.finalprojectsky.mapper;

import org.mapstruct.Mapper;
import java.util.Collection;
import java.util.List;

//@Mapper
public interface WebMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<D> toDto(Collection<E> entity);

    List<E> toEntity(Collection<D> dto);

}