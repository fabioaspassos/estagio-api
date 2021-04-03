package com.github.fabioaspassos.controller;

import com.github.fabioaspassos.model.Escala;
import com.github.fabioaspassos.repository.EscalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/escala")
public class EscalaController {

    @Autowired
    private EscalaRepository escalaRepository;

    @GetMapping
    public List<Escala> list(){
        return escalaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Escala> create(@RequestBody Escala escala){
        Escala escalaSaved = escalaRepository.save(escala);
        return ResponseEntity.ok(escalaSaved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Escala> findById(@PathVariable Integer id){
        Optional<Escala> optionalEscala = escalaRepository.findById(id);
        if (!optionalEscala.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(optionalEscala.get());
    }

}
