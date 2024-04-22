package com.crud.pessoa.domain.entity;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "contato")
public class Contato {

    private static final String SEQ_GENERATOR = "contato_id_seq_gen";
    private static final String SEQ_NAME = "contato_id_seq";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_GENERATOR)
    @SequenceGenerator(name = SEQ_GENERATOR, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone", length = 11)
    private String telefone;

    @Column(name = "email")
    private String email;

    @JoinColumn(name = "pessoa_id", nullable = false, updatable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Pessoa pessoa;

    public Contato() {
    }

    public Contato(String nome, String telefone, String email, Pessoa pessoa) {
        this.setNome(nome);
        this.setTelefone(telefone);
        this.setEmail(email);
        this.pessoa = pessoa;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPessoa(Pessoa pessoa){
        this.pessoa = pessoa;
    }

    public void mergeForUpdate(Contato contato){
        this.setNome(Optional.ofNullable(contato.getNome()).orElse(this.getNome()));
        this.setEmail(Optional.ofNullable(contato.getEmail()).orElse(this.getEmail()));
        this.setTelefone(Optional.ofNullable(contato.getTelefone()).orElse(this.getTelefone()));
    }
}
