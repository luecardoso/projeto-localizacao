package com.luecardoso.projetos.localizacao.config;

import com.luecardoso.projetos.localizacao.domain.entity.Cidade;
import com.luecardoso.projetos.localizacao.domain.service.CidadeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Initialization implements CommandLineRunner {

    private final CidadeService cidadeService;

    public Initialization(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @Override
    public void run(String... args) throws Exception {
        Cidade cidade = new Cidade(1L, "São Paulo", 12396372L );
        Cidade cidade2 = new Cidade(2L, "Rio de Janeiro", 10000000L );
        Cidade cidade3 = new Cidade(3L, "Porto Alegre", 7770000L );
        Cidade cidade4 = new Cidade(4L, "Porto Velho", 4948989L );

        cidadeService.salvarCidade(cidade);
        cidadeService.salvarCidade(cidade2);
        cidadeService.listarTodasCidades();
        cidadeService.listarCidadePorNome("São Paulo");
        cidadeService.listarCidadePorNomeStartingWith("Rio");
        cidadeService.listarCidadePorNomeEndingWith("o");
        cidadeService.listarCidadePorNomeContaining("Porto");
        cidadeService.listarCidadePorNomeLike("Rio");

        cidadeService.listarCidadePorHabitantes(10000000L);
        cidadeService.listarCidadePorHabitantesLessThan(10000000L);
        cidadeService.listarCidadePorHabitantesGreaterThan(550000L);
        cidadeService.listarCidadePorHabitantesLessThanEqual(10000000L);
        cidadeService.listarCidadePorHabitantesLessThanAndNomeLike(20000000L, "Rio");
    }
}
