package com.github.fabioaspassos.repository;

import com.github.fabioaspassos.entity.CampoEstagio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampoEstagioRepository extends JpaRepository<CampoEstagio, Integer> {

    Page<CampoEstagio> findByNomeStartingWithIgnoreCase(String nome, Pageable pageable);
    Page<CampoEstagio> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
