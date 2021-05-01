package com.github.fabioaspassos.controller;

import com.github.fabioaspassos.entity.Preceptor;
import com.github.fabioaspassos.repository.PreceptorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@CrossOrigin
@RestController
@RequestMapping("/api/preceptor")
public class PreceptorController {
    public final PreceptorRepository preceptorRepository;

    @GetMapping("/startingWith/{nome}")
    public List<Preceptor> findByNome(@PathVariable String nome,
                                      Pageable pageable){
        return preceptorRepository.findByNomeStartingWithIgnoreCase(nome, pageable).getContent();
    }
}
