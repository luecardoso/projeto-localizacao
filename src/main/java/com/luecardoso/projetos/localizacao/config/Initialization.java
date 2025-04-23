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
        cidadeService.salvarCidade(cidade);
        cidadeService.listarTodasCidades();
        cidadeService.listarCidadePorNome("São Paulo");
    }
}
