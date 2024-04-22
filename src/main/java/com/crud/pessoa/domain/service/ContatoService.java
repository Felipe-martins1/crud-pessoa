package com.crud.pessoa.domain.service;

import com.crud.pessoa.domain.entity.Contato;
import com.crud.pessoa.domain.entity.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContatoService {
    Page<Contato> getAllByPessoa(String pessoaId, Pageable pageable);
    Contato create(Contato contato);
    Contato update(Contato contato);
    void delete(Long id);
    Contato getById(Long id);
}
