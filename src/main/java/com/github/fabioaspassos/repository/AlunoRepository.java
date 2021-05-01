package com.github.fabioaspassos.repository;

import com.github.fabioaspassos.entity.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    Page<Aluno> findByNomeStartingWithIgnoreCase(String nome, Pageable pageable);
}
