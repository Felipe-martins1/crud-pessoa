package com.crud.pessoa.domain.domainService;

import com.crud.pessoa.domain.entity.Pessoa;
import com.crud.pessoa.domain.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Objects;


@SpringBootTest
public class DomainPessoaServiceUnitTest {
    private PessoaRepository pessoaRepository;
    private DomainPessoaService domainPessoaService;

    @BeforeEach
    void setUp(){
        pessoaRepository = Mockito.mock(PessoaRepository.class);
        domainPessoaService = new DomainPessoaService(pessoaRepository);
    }

    @Test
    void shouldCreatePessoa_thenSaveIt(){
        var pessoaReq = new Pessoa();
        pessoaReq.setNome("Felipe");
        pessoaReq.setCpf("11988406927");
        pessoaReq.setDataNascimento(LocalDate.parse("2003-05-13"));

        final Pessoa pessoa = domainPessoaService.create(pessoaReq);

        Mockito.verify(pessoaRepository).save(Mockito.any(Pessoa.class));
        Objects.requireNonNull(pessoa);
    }
}
