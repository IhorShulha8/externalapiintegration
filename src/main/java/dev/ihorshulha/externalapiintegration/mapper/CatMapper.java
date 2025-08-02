package dev.ihorshulha.externalapiintegration.mapper;

import dev.ihorshulha.externalapiintegration.dto.CatDto;
import dev.ihorshulha.externalapiintegration.model.Cat;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatMapper {

    CatDto toDto(Cat cat);

    List<CatDto> toDtos(List<Cat> cats);
}
