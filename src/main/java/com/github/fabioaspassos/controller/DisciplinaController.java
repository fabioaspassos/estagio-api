package com.github.fabioaspassos.controller;

import com.github.fabioaspassos.entity.Disciplina;
import com.github.fabioaspassos.repository.DisciplinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/disciplina")
public class DisciplinaController {
    private final DisciplinaRepository disciplinaRepository;

    @GetMapping("/startingWith/{descricao}")
    public List<Disciplina> findBystartingWithDescricao(@PathVariable String descricao){
        return disciplinaRepository.findByDescricaoStartingWithIgnoreCase(descricao);
    }

    @GetMapping("/containing/{descricao}")
    public List<Disciplina> findByContainingDescricao(@PathVariable String descricao){
        return disciplinaRepository.findByDescricaoContainingIgnoreCase(descricao);
    }
}
