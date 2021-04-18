package com.github.fabioaspassos.controller;

import com.github.fabioaspassos.entity.Aluno;
import com.github.fabioaspassos.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {
    private final AlunoRepository alunoRepository;

    @GetMapping("/startingWith/{nome}")
    public List<Aluno> findByNome(@PathVariable String nome){
        return alunoRepository.findByNomeStartingWithIgnoreCase(nome);
    }
}
