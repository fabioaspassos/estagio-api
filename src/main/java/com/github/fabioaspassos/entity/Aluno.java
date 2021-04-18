package com.github.fabioaspassos.entity;

import javax.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id_aluno")
public class Aluno extends Pessoa{

//    @ManyToMany
//    private Set<Grupo> grupos = new HashSet<Grupo>();

}
