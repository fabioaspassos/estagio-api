package com.github.fabioaspassos.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Disciplina {

    @Id
    private Integer id;

    @Column
    private String descricao;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean estagio;
}
