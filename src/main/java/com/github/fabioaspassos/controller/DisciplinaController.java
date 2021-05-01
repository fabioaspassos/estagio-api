package com.github.fabioaspassos.controller;

import com.github.fabioaspassos.entity.Disciplina;
import com.github.fabioaspassos.repository.DisciplinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@CrossOrigin
@RestController
@RequestMapping("/api/disciplina")
public class DisciplinaController {
    private final DisciplinaRepository disciplinaRepository;

    @GetMapping("/startingWith/{descricao}")
    public List<Disciplina> findBystartingWithDescricao(@PathVariable String descricao,
                                                        Pageable pageable){
        return disciplinaRepository.findByDescricaoStartingWithIgnoreCase(descricao, pageable).getContent();
    }

    @GetMapping("/containing/{descricao}")
    public List<Disciplina> findByContainingDescricao(@PathVariable String descricao,
                                                      Pageable pageable){
        return disciplinaRepository.findByDescricaoContainingIgnoreCase(descricao, pageable).getContent();
    }
}
