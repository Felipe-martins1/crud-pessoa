package com.crud.pessoa.application.dto;

import com.crud.pessoa.domain.entity.Contato;
import com.crud.pessoa.domain.entity.Pessoa;
import jakarta.validation.constraints.*;

import java.util.UUID;

public class CreateContatoDto {
    @Size(min = 3, max = 100, message = "Nome deve ter no mínimo 3 caracteres")
    private String nome;

    @Size(min = 10, max = 11, message = "Telefone deve ter no mínimo 10 caracteres")
    @Digits(integer = 11, fraction = 0)
    private String telefone;

    @Email(message = "Por favor, insira um email válido")
    private String email;

    @org.hibernate.validator.constraints.UUID(message = "pessoaId deve ser um UUID")
    private String pessoaId;

    public CreateContatoDto(String nome, String telefone, String email, String pessoaId) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.pessoaId = pessoaId;
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

    public String getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(String pessoaId) {
        this.pessoaId = pessoaId;
    }

    public Contato toContato(){
        var contato  = new Contato();
        contato.setNome(this.nome);
        contato.setTelefone(this.telefone);
        contato.setEmail(this.email);
        var pessoa = new Pessoa();
        pessoa.setId(UUID.fromString(this.pessoaId));
        contato.setPessoa(pessoa);
        return contato;
    }
}
