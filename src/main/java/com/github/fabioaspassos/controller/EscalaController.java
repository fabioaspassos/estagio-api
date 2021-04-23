package com.github.fabioaspassos.controller;

import com.github.fabioaspassos.entity.Escala;
import com.github.fabioaspassos.dto.EscalaDto;
import com.github.fabioaspassos.dto.EstagioMapper;
import com.github.fabioaspassos.repository.EscalaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@CrossOrigin
@RestController
@RequestMapping("/api/escala")
public class EscalaController {
    private final EscalaRepository escalaRepository;
    private final EstagioMapper estagioMapper;

    @GetMapping
    public List<EscalaDto> list(){
        return  estagioMapper.toEscalaDTOs(escalaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Escala> create(@RequestBody Escala escala){
        Escala escalaSaved = escalaRepository.save(escala);
        return ResponseEntity.ok(escalaSaved);
    }

    @PutMapping
    public ResponseEntity<EscalaDto> update(@RequestBody EscalaDto escalaDto) {
        Optional<Escala> optionalEscala = escalaRepository.findById(escalaDto.getId());
        if (!optionalEscala.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        optionalEscala.get().setNome(escalaDto.getNome());
        Escala escala =  escalaRepository.save(optionalEscala.get());

        return ResponseEntity.ok(estagioMapper.toEscalaDto(escala));
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
