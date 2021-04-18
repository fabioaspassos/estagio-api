package com.github.fabioaspassos.dto;

import com.github.fabioaspassos.entity.Escala;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstagioMapper {
    EscalaDto toEscalaDto(Escala escala);
    Escala toEscala(EscalaDto escalaDto);
    List<EscalaDto> toEscalaDTOs(List<Escala> escalas);

}
