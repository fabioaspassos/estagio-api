package com.github.fabioaspassos.repository;

import com.github.fabioaspassos.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

    List<Disciplina> findByDescricaoStartingWithIgnoreCase(String descricao);
    List<Disciplina> findByDescricaoContainingIgnoreCase(String descricao);
}
