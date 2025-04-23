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

    public void listarCidadePorNomeStartingWith(String nome){
        repository.findByNomeStartingWith(nome).forEach(System.out::println);
    }

    public void listarCidadePorNomeEndingWith(String nome){
        repository.findByNomeEndingWith(nome).forEach(System.out::println);
    }

    public void listarCidadePorNomeContaining(String nome){
        repository.findByNomeContaining(nome).forEach(System.out::println);
    }

    public void listarCidadePorNomeLike(String nome){
        repository.findByNomeLike("%"+nome+"%").forEach(System.out::println);
    }

    public void listarCidadePorHabitantes(Long habitantes){
        repository.findByHabitantes(habitantes).forEach(System.out::println);
    }

    public void listarCidadePorHabitantesLessThan(Long habitantes){
        repository.findByHabitantesLessThan(habitantes).forEach(System.out::println);
    }

    public void listarCidadePorHabitantesGreaterThan(Long habitantes){
        repository.findByHabitantesGreaterThan(habitantes).forEach(System.out::println);
    }

    public void listarCidadePorHabitantesLessThanEqual(Long habitantes){
        repository.findByHabitantesLessThanEqual(habitantes).forEach(System.out::println);
    }

    public void listarCidadePorHabitantesLessThanAndNomeLike(Long habitantes, String nome){
        repository.findByHabitantesLessThanAndNomeLike(habitantes, "%"+nome+"%").forEach(System.out::println);
    }

}
