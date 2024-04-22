package com.crud.pessoa.domain.domainService;

import com.crud.pessoa.config.CrudPessoaBusinessException;
import com.crud.pessoa.domain.entity.Contato;
import com.crud.pessoa.domain.entity.Pessoa;
import com.crud.pessoa.domain.repository.ContatoRepository;
import com.crud.pessoa.domain.service.ContatoService;
import com.crud.pessoa.domain.service.PessoaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DomainContatoService implements ContatoService {
    private final ContatoRepository contatoRepository;
    private final PessoaService pessoaService;

    public DomainContatoService(ContatoRepository contatoRepository, PessoaService pessoaService) {
        this.contatoRepository = contatoRepository;
        this.pessoaService = pessoaService;
    }

    @Override
    public Page<Contato> getAllByPessoa(String pessoaId, Pageable pageable) {
        var pessoa = this.pessoaService.getById(UUID.fromString(pessoaId));
        return this.contatoRepository.findAllByPessoa(pessoa, pageable);
    }

    @Override
    public Contato create(Contato contato) {
        return this.contatoRepository.save(contato);
    }

    @Override
    public Contato update(Contato contato) {
        var contatoRecuperado = this.getById(contato.getId());
        contatoRecuperado.mergeForUpdate(contato);
        return this.contatoRepository.save(contatoRecuperado);
    }

    @Override
    public void delete(Long id) {
        this.contatoRepository.deleteById(id);
    }

    @Override
    public Contato getById(Long id) {
        return this.contatoRepository
                .findById(id)
                .orElseThrow(
                        () -> new CrudPessoaBusinessException("Contato não encontrado")
                );
    }
}
