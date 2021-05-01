package com.github.fabioaspassos.repository;

import com.github.fabioaspassos.entity.Disciplina;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

    Page<Disciplina> findByDescricaoStartingWithIgnoreCase(String descricao, Pageable pageable);
    Page<Disciplina> findByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);
}
