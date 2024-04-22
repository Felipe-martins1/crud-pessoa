package com.crud.pessoa.application.controller;

import com.crud.pessoa.application.dto.CreateContatoDto;
import com.crud.pessoa.application.dto.UpdateContatoDto;
import com.crud.pessoa.domain.entity.Contato;
import com.crud.pessoa.domain.entity.Pessoa;
import com.crud.pessoa.domain.service.ContatoService;
import com.crud.pessoa.domain.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
    private final ContatoService contatoService;

    @Autowired
    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @GetMapping(path = "/pessoa/{pessoaId}")
    public Page<Contato> findAll(@PathVariable("pessoaId") String pessoaId, Pageable pageable) {
        return contatoService.getAllByPessoa(pessoaId, pageable);
    }

    @GetMapping(path = "{id}")
    public Contato findById(@PathVariable("id") Long id) {
        return contatoService.getById(id);
    }

    @PostMapping
    public Contato create(@Valid @RequestBody CreateContatoDto createContatoDto) {
        return contatoService.create(createContatoDto.toContato());
    }

    @PutMapping
    public Contato update(@Valid @RequestBody UpdateContatoDto updateContatoDto) {
        return contatoService.update(updateContatoDto.toContato());
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") Long id) {
        contatoService.delete(id);
    }
}
