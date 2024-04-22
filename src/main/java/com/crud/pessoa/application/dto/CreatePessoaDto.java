package com.crud.pessoa.application.dto;

import com.crud.pessoa.domain.entity.Pessoa;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public class CreatePessoaDto {

    @NotBlank(message = "Cpf não pode ser vazio!")
    @CPF(message = "Por favor, insira um CPF válido!")
    private String cpf;

    @NotBlank(message = "Nome não pode ser vazio!")
    @Size(min = 3, max = 100, message = "Nome deve ter no mínimo 3 letras")
    private String nome;

    @NotNull(message = "Data de nascimento não pode ser vazia")
    private LocalDate dataNascimento;

    public CreatePessoaDto(String cpf, String nome, LocalDate dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
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
        this.dataNascimento = dataNascimento;
    }

    public Pessoa toPessoa(){
        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(this.cpf);
        pessoa.setNome(this.nome);
        pessoa.setDataNascimento(this.dataNascimento);
        return pessoa;
    }
}
