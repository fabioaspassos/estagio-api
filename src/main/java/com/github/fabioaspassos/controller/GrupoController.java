package com.github.fabioaspassos.controller;

import com.github.fabioaspassos.dto.EstagioMapper;
import com.github.fabioaspassos.dto.GrupoDto;
import com.github.fabioaspassos.entity.*;
import com.github.fabioaspassos.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor

@CrossOrigin
@RestController
@RequestMapping("/api/grupo")
public class GrupoController {
    private final GrupoRepository grupoRepository;
    private final EscalaRepository escalaRepository;
    private final CampoEstagioRepository campoEstagioRepository;
    private final AlunoRepository alunoRepository;
    private final PreceptorRepository preceptorRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final EstagioMapper estagioMapper;

    @GetMapping("/byEscalaId/{id}")
    public ResponseEntity<Set<Grupo>> findByEscala(@PathVariable Integer id){
        Optional<Escala> optionalEscala = escalaRepository.findById(id);
        if (!optionalEscala.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(optionalEscala.get().getGrupos());
    }

    @PostMapping("{id}/campo")
    public ResponseEntity<Grupo> addCampoEstagio(@PathVariable Integer id, @RequestBody CampoEstagio campoEstagio){
        Optional<Grupo> optionalGrupo = grupoRepository.findById(id);
        Optional<CampoEstagio> optionalCampoEstagio = campoEstagioRepository.findById(campoEstagio.getId());
        if (!optionalGrupo.isPresent() || !optionalCampoEstagio.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Grupo grupo = optionalGrupo.get();
        grupo.setCampoEstagio(optionalCampoEstagio.get());
        Grupo grupoSaved = grupoRepository.save(grupo);

        return ResponseEntity.ok(grupoSaved);
    }

    @PostMapping("{id}/aluno")
    public ResponseEntity<Grupo> addAluno(@PathVariable Integer id, @RequestBody Aluno aluno){
        Optional<Aluno> optionalAluno = alunoRepository.findById(aluno.getId());
        Optional<Grupo> optionalGrupo = grupoRepository.findById(id);
        if (!optionalGrupo.isPresent() || !optionalAluno.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Grupo grupo = optionalGrupo.get();
        grupo.addAluno(optionalAluno.get());
        Grupo grupoSaved = grupoRepository.save(grupo);

        return ResponseEntity.ok(grupoSaved);
    }

    @DeleteMapping("{idGrupo}/aluno/{idAluno}")
    public ResponseEntity<Grupo> removeAluno(@PathVariable Integer idGrupo, @PathVariable Integer idAluno){
        Optional<Aluno> optionalAluno = alunoRepository.findById(idAluno);
        Optional<Grupo> optionalGrupo = grupoRepository.findById(idGrupo);
        if (!optionalGrupo.isPresent() || !optionalAluno.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Grupo grupo = optionalGrupo.get();
        grupo.getAlunos().removeIf( a -> a.equals(optionalAluno.get()));
        Grupo grupoSaved = grupoRepository.save(grupo);

        return ResponseEntity.ok(grupoSaved);
    }



    @PostMapping("{id}/preceptor")
    public ResponseEntity<Grupo> addPreceptor(@PathVariable Integer id, @RequestBody Preceptor preceptor){
        Optional<Preceptor> optionalPreceptor = preceptorRepository.findById(preceptor.getId());
        Optional<Grupo> optionalGrupo = grupoRepository.findById(id);
        if (!optionalGrupo.isPresent() || !optionalPreceptor.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Grupo grupo = optionalGrupo.get();
        grupo.setPreceptor(optionalPreceptor.get());
        Grupo grupoSaved = grupoRepository.save(grupo);

        return ResponseEntity.ok(grupoSaved);

    }

    @PostMapping("{id}/disciplina")
    public ResponseEntity<Grupo> addDisciplina(@PathVariable Integer id, @RequestBody Disciplina disciplina){
        Optional<Disciplina> optionalDisciplina = disciplinaRepository.findById(disciplina.getId());
        Optional<Grupo> optionalGrupo = grupoRepository.findById(id);
        if (!optionalGrupo.isPresent() || !optionalDisciplina.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Grupo grupo = optionalGrupo.get();
        grupo.setDisciplina(optionalDisciplina.get());
        Grupo grupoSaved = grupoRepository.save(grupo);

        return ResponseEntity.ok(grupoSaved);
    }

    @PostMapping
    public ResponseEntity<Grupo> create(@RequestBody Grupo grupo){
        if(grupo.getEscala()!=null && grupo.getEscala().getId()!=null) {
            Optional<Escala> optionalEscala = escalaRepository.findById(grupo.getEscala().getId());
            if(optionalEscala.isPresent()) {
                grupo.setEscala(optionalEscala.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        if(grupo.getDisciplina()!=null && grupo.getDisciplina().getId()!=null) {
            Optional<Disciplina> optionalDisciplina = disciplinaRepository.findById(grupo.getDisciplina().getId());
            grupo.setDisciplina(null);
            if (optionalDisciplina.isPresent()) grupo.setDisciplina(optionalDisciplina.get());
        }
        if (grupo.getCampoEstagio()!=null && grupo.getCampoEstagio().getId()!=null){
            Optional<CampoEstagio> optionalCampoEstagio = campoEstagioRepository.findById(grupo.getCampoEstagio().getId());
            grupo.setCampoEstagio(null);
            if(optionalCampoEstagio.isPresent()) grupo.setCampoEstagio(optionalCampoEstagio.get());
        }
        if (grupo.getPreceptor()!=null && grupo.getPreceptor().getId()!=null){
            Optional<Preceptor> optionalPreceptor = preceptorRepository.findById(grupo.getPreceptor().getId());
            grupo.setPreceptor(null);
            if(optionalPreceptor.isPresent()) grupo.setPreceptor(optionalPreceptor.get());
        }

        Grupo grupoSaved = grupoRepository.save(grupo);

        return ResponseEntity.ok(grupoSaved);
    }

    @PutMapping
    public ResponseEntity<Grupo> alter(@RequestBody Grupo grupo){
        Grupo grupoSaved = grupoRepository.save(grupo);
        return ResponseEntity.ok(grupoSaved);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        Optional<Grupo> optionalGrupo = grupoRepository.findById(id);
        if (optionalGrupo.isPresent()){
            grupoRepository.delete(optionalGrupo.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("duplicate/{id}")
    public ResponseEntity<Grupo> duplicate(@PathVariable Integer id) {
        Optional<Grupo> optionalGrupo = grupoRepository.findById(id);
        if (optionalGrupo.isPresent()){
            Grupo grupo = optionalGrupo.get();
            return ResponseEntity.ok(grupoRepository.save(grupo.makeCopy()));
        }
        return ResponseEntity.noContent().build();
    }
}
