package com.github.fabioaspassos.repository;

import com.github.fabioaspassos.entity.Preceptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreceptorRepository extends JpaRepository<Preceptor, Integer> {

    Page<Preceptor> findByNomeStartingWithIgnoreCase(String nome, Pageable pageable);
}
