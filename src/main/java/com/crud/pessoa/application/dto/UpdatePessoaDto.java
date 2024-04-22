package com.crud.pessoa.application.dto;

import com.crud.pessoa.domain.entity.Pessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class UpdatePessoaDto extends CreatePessoaDto {
    @NotBlank(message = "id deve ser informado!")
    @org.hibernate.validator.constraints.UUID
    private String id;

    public UpdatePessoaDto(String id, String cpf, String nome, LocalDate dataNascimento) {
        super(cpf, nome, dataNascimento);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pessoa toPessoa(){
        var pessoa = super.toPessoa();
        pessoa.setId(UUID.fromString(this.getId()));
        return pessoa;
    }
}
