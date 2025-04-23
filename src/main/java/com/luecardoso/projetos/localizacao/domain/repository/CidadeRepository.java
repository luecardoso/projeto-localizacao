package com.luecardoso.projetos.localizacao.domain.repository;

import com.luecardoso.projetos.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

    List<Cidade> findByNome(String nome);
}
