package com.github.fabioaspassos.repository;

import com.github.fabioaspassos.entity.CampoEstagio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampoEstagioRepository extends JpaRepository<CampoEstagio, Integer> {

    List<CampoEstagio> findByNomeStartingWithIgnoreCase(String nome);
    List<CampoEstagio> findByNomeContainingIgnoreCase(String nome);
}
