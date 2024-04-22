package com.crud.pessoa.application.dto;

import com.crud.pessoa.domain.entity.Contato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class UpdateContatoDto extends CreateContatoDto {
    @NotNull(message = "id deve ser informado!")
    private Long id;

    public UpdateContatoDto(Long id, String nome, String telefone, String email, String pessoaId) {
        super(nome, telefone, email, pessoaId);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contato toContato(){
        var contato = super.toContato();
        contato.setId(this.id);
        return contato;
    }
}
