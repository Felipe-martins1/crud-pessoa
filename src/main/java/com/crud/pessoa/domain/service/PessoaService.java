package com.crud.pessoa.domain.service;

import com.crud.pessoa.domain.entity.Contato;
import com.crud.pessoa.domain.entity.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.UUID;

public interface PessoaService {
    Pessoa create(Pessoa pessoa);
    Pessoa update(Pessoa pessoa) ;
    void delete(UUID id);
    Pessoa getById(UUID id);
    Page<Pessoa> getAll(Pageable pageable);
}
