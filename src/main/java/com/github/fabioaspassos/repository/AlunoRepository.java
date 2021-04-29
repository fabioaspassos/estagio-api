package com.github.fabioaspassos.repository;

import com.github.fabioaspassos.entity.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlunoRepository extends PagingAndSortingRepository<Aluno, Integer> {

    Page<Aluno> findByNomeStartingWithIgnoreCase(String nome, Pageable pageable);
}
