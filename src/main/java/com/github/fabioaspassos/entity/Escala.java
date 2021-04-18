package com.github.fabioaspassos.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "escala")
@Data
public class Escala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String nome;

    @OneToMany(mappedBy = "escala", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Grupo> grupos = new HashSet<Grupo>();

    public void setGrupos(Set<Grupo> grupos) {
        this.grupos = grupos;

        for(Grupo b : grupos) {
            b.setEscala(this);
        }
    }

    public void addGrupo(Grupo grupo) {
        grupo.setEscala(this);
        this.grupos.add(grupo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Escala escala = (Escala) o;
        return id == escala.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Escala{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}