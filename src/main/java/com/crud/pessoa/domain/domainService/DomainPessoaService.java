package com.crud.pessoa.domain.domainService;

import com.crud.pessoa.config.CrudPessoaBusinessException;
import com.crud.pessoa.domain.entity.Contato;
import com.crud.pessoa.domain.entity.Pessoa;
import com.crud.pessoa.domain.repository.PessoaRepository;
import com.crud.pessoa.domain.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DomainPessoaService implements PessoaService {
    private final PessoaRepository pessoaRepository;

    @Autowired
    public DomainPessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public Pessoa create(Pessoa pessoa) {
        pessoa.setId(UUID.randomUUID());
        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa update(Pessoa pessoa) {
        var pessoaRecuperada = this.getById(pessoa.getId());
        pessoaRecuperada.mergeForUpdate(pessoa);

        return pessoaRepository.save(pessoaRecuperada);
    }

    @Override
    public void delete(UUID id) {
        pessoaRepository.deleteById(id);
    }

    @Override
    public Pessoa getById(UUID id) {
        return pessoaRepository
                .findById(id)
                .orElseThrow(() -> new CrudPessoaBusinessException("Pessoa não encontrada"));
    }

    @Override
    public Page<Pessoa> getAll(Pageable pageable) {
        return pessoaRepository.findAll(pageable);
    }
}
