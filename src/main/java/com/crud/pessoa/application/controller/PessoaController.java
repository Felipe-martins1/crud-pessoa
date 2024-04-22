package com.crud.pessoa.application.controller;

import com.crud.pessoa.application.dto.CreatePessoaDto;
import com.crud.pessoa.application.dto.UpdatePessoaDto;
import com.crud.pessoa.domain.entity.Contato;
import com.crud.pessoa.domain.entity.Pessoa;
import com.crud.pessoa.domain.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public Page<Pessoa> findAll(Pageable pageable) {
        return pessoaService.getAll(pageable);
    }

    @GetMapping(path = "{id}")
    public Pessoa findById(@PathVariable("id") UUID id) {
        return pessoaService.getById(id);
    }

    @PostMapping
    public Pessoa create(@Valid @RequestBody CreatePessoaDto createPessoaDto) {
        return pessoaService.create(createPessoaDto.toPessoa());
    }

    @PutMapping
    public Pessoa update(@Valid @RequestBody UpdatePessoaDto updatePessoaDto) {
        return pessoaService.update(updatePessoaDto.toPessoa());
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable("id") UUID id) {
        pessoaService.delete(id);
    }
}
