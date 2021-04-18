package com.github.fabioaspassos.controller;

import com.github.fabioaspassos.entity.Aluno;
import com.github.fabioaspassos.entity.CampoEstagio;
import com.github.fabioaspassos.repository.CampoEstagioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/campo")
public class CampoEstagioController {
    private final CampoEstagioRepository campoEstagioRepository;

    @GetMapping("/startingWith/{nome}")
    public List<CampoEstagio> findByStartingWithNome(@PathVariable String nome){
        return campoEstagioRepository.findByNomeStartingWithIgnoreCase(nome);
    }

    @GetMapping("/containing/{nome}")
    public List<CampoEstagio> findByContainingNome(@PathVariable String nome){
        return campoEstagioRepository.findByNomeContainingIgnoreCase(nome);
    }

}
