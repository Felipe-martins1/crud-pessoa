package com.crud.pessoa.domain.repository;

import com.crud.pessoa.domain.entity.Contato;
import com.crud.pessoa.domain.entity.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
    Page<Contato> findAllByPessoa(Pessoa pessoa, Pageable pageable);
}
