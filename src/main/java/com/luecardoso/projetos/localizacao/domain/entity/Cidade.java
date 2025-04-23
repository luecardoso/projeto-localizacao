package com.luecardoso.projetos.localizacao.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cidade")
public class Cidade {

    @Id
    @Column(name = "id_cidade")
    private Long id;

    @Column(name = "nome", length = 50)
    private String nome;

    @Column(name = "qtd_habitantes")
    private Long habitantes;

    public Cidade() {}

    public Cidade(Long id, String nome, Long habitantes) {
        this.id = id;
        this.nome = nome;
        this.habitantes = habitantes;
    }

    public Long getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(Long habitantes) {
        this.habitantes = habitantes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "habitantes=" + habitantes +
                ", id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
