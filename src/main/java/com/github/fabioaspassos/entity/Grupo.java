package com.github.fabioaspassos.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "escala_grupo")
@Data
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private LocalDate dataInicio;

    @Column
    private LocalDate dataFim;

    @Column
    private String turno;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "escala_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Escala escala;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Preceptor preceptor;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    @ManyToMany
    @JoinTable(
            name = "escala_grupo_aluno",
            joinColumns = @JoinColumn( name = "grupo_id"),
            inverseJoinColumns = @JoinColumn( name = "aluno_id")
    )
    private Set<Aluno> alunos = new HashSet<Aluno>();

    @ManyToOne
    @JoinColumn(name = "campo_estagio_id")
    private CampoEstagio campoEstagio;

    public void addAluno(Aluno aluno){
        this.alunos.add(aluno);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grupo grupo = (Grupo) o;
        return id == grupo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", escala=" + escala +
                '}';
    }

    public Grupo makeCopy(){
        Grupo newGrupo = new Grupo();
        newGrupo.setNome("Copia de "+ this.getNome());
        newGrupo.setEscala(this.getEscala());
        newGrupo.setPreceptor(this.getPreceptor());
        newGrupo.setDisciplina(this.getDisciplina());
        newGrupo.setCampoEstagio(this.getCampoEstagio());
        this.getAlunos().forEach(aluno -> newGrupo.addAluno(aluno));
        return newGrupo;
    }
}