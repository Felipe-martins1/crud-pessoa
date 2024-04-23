package com.crud.pessoa.domain.entity;

import com.crud.pessoa.config.CrudPessoaBusinessException;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pessoa", cascade = CascadeType.REMOVE)
    private final List<Contato> contatos = new ArrayList<>();

    public Pessoa() {
    }

    public Pessoa(UUID id, String cpf, String nome, LocalDate dataNascimento) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if(dataNascimento.isAfter(LocalDate.now())) {
            throw new CrudPessoaBusinessException("Data de nascimento não pode ser maior que a data atual");
        }
        this.dataNascimento = dataNascimento;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void mergeForUpdate(Pessoa pessoa){
        this.setNome(Optional.ofNullable(pessoa.getNome()).orElse(this.getNome()));
        this.setCpf(Optional.ofNullable(pessoa.getCpf()).orElse(this.getCpf()));
        this.setDataNascimento(Optional.ofNullable(pessoa.getDataNascimento()).orElse(this.getDataNascimento()));
    }
}
