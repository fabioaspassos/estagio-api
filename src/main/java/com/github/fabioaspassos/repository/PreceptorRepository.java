package com.github.fabioaspassos.repository;

import com.github.fabioaspassos.entity.Preceptor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreceptorRepository extends JpaRepository<Preceptor, Integer> {

    List<Preceptor> findByNomeStartingWithIgnoreCase(String nome);
}
