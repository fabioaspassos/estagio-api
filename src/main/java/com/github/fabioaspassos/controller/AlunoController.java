package com.github.fabioaspassos.controller;

import com.github.fabioaspassos.entity.Aluno;
import com.github.fabioaspassos.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@CrossOrigin
@RestController
@RequestMapping("/api/aluno")
public class AlunoController {
    private final AlunoRepository alunoRepository;

    @GetMapping("/startingWith/{nome}")
    public List<Aluno> findByNome(@PathVariable String nome,
                                  Pageable pageable){

        return alunoRepository.findByNomeStartingWithIgnoreCase(nome, pageable).getContent();
    }

}
