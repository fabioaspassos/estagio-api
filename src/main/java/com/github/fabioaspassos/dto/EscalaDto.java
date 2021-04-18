package com.github.fabioaspassos.dto;

import lombok.Data;

import java.util.List;

@Data
public class EscalaDto {
    private int id;
    private String nome;
    private List<GrupoDto> grupos;
}
