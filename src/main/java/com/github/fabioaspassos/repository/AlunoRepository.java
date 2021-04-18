package com.github.fabioaspassos.repository;

import com.github.fabioaspassos.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    List<Aluno> findByNomeStartingWithIgnoreCase(String nome);
}
