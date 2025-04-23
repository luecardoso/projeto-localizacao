package com.luecardoso.projetos.localizacao.domain.service;

import com.luecardoso.projetos.localizacao.domain.entity.Cidade;
import com.luecardoso.projetos.localizacao.domain.repository.CidadeRepository;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import static com.luecardoso.projetos.localizacao.domain.repository.specs.CidadeSpecs.*;

import java.util.List;

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

    public void listarCidadePorNomeLike(String nome, Sort sort){
        Pageable pageable = PageRequest.of(2, 2, sort);
        repository.findByNomeLike("%%%", pageable).forEach(System.out::println);
    }

    public void listarCidadePorNomeLike(String nome, Pageable pageable){
        repository.findByNomeLike("%%%", pageable).forEach(System.out::println);
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

    public List<Cidade> filtroDinamico(Cidade cidade){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        Example<Cidade> example = Example.of(cidade, matcher);
        return repository.findAll(example);
    }

    public void listarCidadesByNomeSpec(String nome, Long id){
        repository
                .findAll(nomeEqual(nome).and(idEqual(id)))
                .forEach(System.out::println);
    }

    public Specification<Cidade> habitantesGreaterThan(Long value){
        return (root, query, cb) -> cb.greaterThan( root.get("habitantes") , value );
    }

    public Specification<Cidade> habitantesBetween(Long min, Long max){
        return (root, query, cb) -> cb.between( root.get("habitantes"), min, max );
    }

    public Specification<Cidade> idEqual(Long id){
        return (root, query, cb) -> cb.equal( root.get("id") , id );
    }

    public Specification<Cidade> nomeEqual(String nome){
        return (root, query, cb) -> cb.equal( root.get("nome") , nome );
    }

    public Specification<Cidade> nomeLike(String nome){
        return (root, query, cb) -> cb.like( cb.upper(root.get("nome")) , "%" + nome + "%".toUpperCase() );
    }

    public Specification<Cidade> propertyEqual(String prop, Object value){
        return (root, query, cb) -> cb.equal( root.get(prop) , value );
    }

    public Specification<Cidade> propertyLike(String prop, String value){
        return (root, query, cb) -> cb.like( cb.upper(root.get(prop)) , "%" + value + "%".toUpperCase() );
    }

    public Specification<Cidade> propertyBetween(String prop, Long min, Long max){
        return (root, query, cb) -> cb.between( root.get(prop), min, max );
    }

    public void listarCidadesSpecsFiltroDinamico(Cidade filtro){
        Specification<Cidade> specs = Specification.where((root, query, cb) -> cb.conjunction());

        //select * from cidade where 1 = 1

        if(filtro.getId() != null){
            specs = specs.and( idEqual(filtro.getId()) );
        }

        if(StringUtils.hasText(filtro.getNome())){
            specs = specs.and(nomeLike(filtro.getNome()));
        }

        if(filtro.getHabitantes() != null){
            specs = specs.and(habitantesGreaterThan(filtro.getHabitantes()));
        }

        repository.findAll(specs).forEach(System.out::println);
    }

    public void listarCidadesPorNomeSQL(String nome){
        repository
                .findByNomeSqlNativo(nome)
                .stream().map(cidadeProjection -> new Cidade(cidadeProjection.getId(), cidadeProjection.getNome(), null))
                .forEach(System.out::println);
    }

}
