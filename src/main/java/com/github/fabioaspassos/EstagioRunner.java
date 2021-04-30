package com.github.fabioaspassos;

import com.github.fabioaspassos.entity.*;
import com.github.fabioaspassos.repository.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class EstagioRunner implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(EstagioRunner.class);

    private final EscalaRepository escalaRepository;
    private final PreceptorRepository preceptorRepository;
    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final CampoEstagioRepository campoEstagioRepository;

    @Override
    public void run(String... args) throws Exception {

        Preceptor preceptor = new Preceptor();
        preceptor.setNome("Professor Padrao");
        preceptorRepository.save(preceptor);
        LOGGER.info("Preceptor: "+ preceptor.getNome());

        Aluno aluno = new Aluno();
        aluno.setNome("Fabio Passos");
        alunoRepository.save(aluno);
        LOGGER.info("Aluno: "+ aluno.getNome());

        Escala e = new Escala();
        e.setNome("TURMA-2101-T");

        Disciplina disciplina = disciplinaRepository.getOne(9);
        CampoEstagio campoEstagio = campoEstagioRepository.getOne(1);

        Grupo g = new Grupo();
        g.setNome("Grupo 2101 A");
        g.setDataInicio( LocalDate.now() );
        g.setDataFim( LocalDate.of(2021, 12, 25));
        g.addAluno(aluno);
        g.setPreceptor(preceptor);
        g.setDisciplina(disciplina);
        g.setCampoEstagio(campoEstagio);

        e.addGrupo(g);
        escalaRepository.save(e);

        escalaRepository.findAll().forEach( escala -> {
            LOGGER.info("Escala: " + escala.getNome());
            escala.getGrupos().forEach( grupo -> {
                LOGGER.info("   Grupo: " + grupo.getNome());
            });
        });
    }
}
