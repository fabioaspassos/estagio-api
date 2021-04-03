package com.github.fabioaspassos;

import com.github.fabioaspassos.model.Escala;
import com.github.fabioaspassos.model.Grupo;
import com.github.fabioaspassos.repository.EscalaRepository;
import com.github.fabioaspassos.repository.GrupoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EstagioRunner implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(EstagioRunner.class);

    @Autowired
    private EscalaRepository escalaRepository;
    @Autowired
    private GrupoRepository grupoRepository;

    @Override
    public void run(String... args) throws Exception {
        Escala e = new Escala();
        e.setNome("TURMA-2101-T");

        Grupo g = new Grupo();
        g.setNome("Grupo 2101 A");
        e.addGrupo(g);
        escalaRepository.save(e);

        e = new Escala();
        e.setNome("TURMA-2201-N");

        g = new Grupo();
        g.setNome("Grupo 2201 A");
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
