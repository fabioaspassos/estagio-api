package com.github.fabioaspassos.dto;

import com.github.fabioaspassos.entity.CampoEstagio;
import com.github.fabioaspassos.entity.Disciplina;
import com.github.fabioaspassos.entity.Preceptor;
import lombok.Data;
import java.time.LocalDate;

@Data
public class GrupoDto {
    private int id;
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private CampoEstagio campoEstagio;
    private Disciplina disciplina;
    private Preceptor preceptor;
}
