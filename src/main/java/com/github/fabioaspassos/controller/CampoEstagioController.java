package com.github.fabioaspassos.controller;

import com.github.fabioaspassos.entity.CampoEstagio;
import com.github.fabioaspassos.repository.CampoEstagioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@CrossOrigin
@RestController
@RequestMapping("/api/campo")
public class CampoEstagioController {
    private final CampoEstagioRepository campoEstagioRepository;

    @GetMapping("/startingWith/{nome}")
    public List<CampoEstagio> findByStartingWithNome(@PathVariable String nome,Pageable pageable){
        return campoEstagioRepository.findByNomeStartingWithIgnoreCase(nome, pageable).getContent();
    }

    @GetMapping("/containing/{nome}")
    public List<CampoEstagio> findByContainingNome(@PathVariable String nome, Pageable pageable){
        return campoEstagioRepository.findByNomeContainingIgnoreCase(nome, pageable).getContent();
    }

}
