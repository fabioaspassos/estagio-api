package com.github.fabioaspassos.controller;

import com.github.fabioaspassos.entity.Preceptor;
import com.github.fabioaspassos.repository.PreceptorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/preceptor")
public class PreceptorController {
    public final PreceptorRepository preceptorRepository;

    @GetMapping("/startingWith/{nome}")
    public List<Preceptor> findByNome(@PathVariable String nome){
        return preceptorRepository.findByNomeStartingWithIgnoreCase(nome);
    }
}
