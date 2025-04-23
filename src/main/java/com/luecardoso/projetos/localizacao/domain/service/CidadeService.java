package com.luecardoso.projetos.localizacao.domain.service;

import com.luecardoso.projetos.localizacao.domain.entity.Cidade;
import com.luecardoso.projetos.localizacao.domain.repository.CidadeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CidadeService {

    private CidadeRepository repository;

    public CidadeService(CidadeRepository repository){
        this.repository = repository;
    }

    @Transactional
    public void salvarCidade(Cidade cidade){
        repository.save(cidade);
    }

    public void listarTodasCidades(){
        repository.findAll().forEach(System.out::println);
    }

    public void listarCidadePorNome(String nome){
        repository.findByNome(nome).forEach(System.out::println);
    }
}
