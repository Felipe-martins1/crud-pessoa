package com.crud.pessoa.domain.domainService;

import com.crud.pessoa.config.CrudPessoaBusinessException;
import com.crud.pessoa.domain.entity.Pessoa;
import com.crud.pessoa.domain.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DomainPessoaServiceUnitTest {

    private DomainPessoaService domainPessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @BeforeEach
    void setUp() {
        pessoaRepository = mock(PessoaRepository.class);
        domainPessoaService = new DomainPessoaService(pessoaRepository);
    }

    @Test
    void createPessoa() {
        Pessoa pessoaReq = new Pessoa();
        pessoaReq.setNome("Felipe");
        pessoaReq.setCpf("75997925978");
        pessoaReq.setDataNascimento(LocalDate.parse("2003-05-13"));

        UUID id = UUID.randomUUID();
        pessoaReq.setId(id);

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(pessoaReq);

        Pessoa createdPessoa = domainPessoaService.create(pessoaReq);

        assertNotNull(createdPessoa);
        assertEquals("Felipe", createdPessoa.getNome());
        assertEquals("75997925978", createdPessoa.getCpf());
        assertEquals(LocalDate.parse("2003-05-13"), createdPessoa.getDataNascimento());

        verify(pessoaRepository, times(1)).save(any(Pessoa.class));
    }

    @Test
    void updatePessoa() {
        Pessoa pessoaToUpdate = new Pessoa();
        UUID id = UUID.randomUUID();
        pessoaToUpdate.setId(id);
        pessoaToUpdate.setNome("Nome atualizado");

        Pessoa existingPessoa = new Pessoa();
        existingPessoa.setId(id);
        existingPessoa.setNome("Nome original");

        when(pessoaRepository.findById(id)).thenReturn(Optional.of(existingPessoa));
        when(pessoaRepository.save(any(Pessoa.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Pessoa updatedPessoa = domainPessoaService.update(pessoaToUpdate);

        assertNotNull(updatedPessoa);
        assertEquals("Nome atualizado", updatedPessoa.getNome());
        assertEquals(id, updatedPessoa.getId());

        verify(pessoaRepository, times(1)).findById(id);
        verify(pessoaRepository, times(1)).save(any(Pessoa.class));
    }

    @Test
    void deletePessoa() {
        UUID id = UUID.randomUUID();
        doNothing().when(pessoaRepository).deleteById(id);

        assertDoesNotThrow(() -> domainPessoaService.delete(id));

        verify(pessoaRepository, times(1)).deleteById(id);
    }

    @Test
    void getById() {
        UUID id = UUID.randomUUID();
        Pessoa expectedPessoa = new Pessoa();
        expectedPessoa.setId(id);

        when(pessoaRepository.findById(id)).thenReturn(Optional.of(expectedPessoa));

        Pessoa retrievedPessoa = domainPessoaService.getById(id);

        assertNotNull(retrievedPessoa);
        assertEquals(expectedPessoa, retrievedPessoa);

        verify(pessoaRepository, times(1)).findById(id);
    }

    @Test
    void getById_PessoaNotFound() {
        UUID id = UUID.randomUUID();

        when(pessoaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CrudPessoaBusinessException.class, () -> domainPessoaService.getById(id));

        verify(pessoaRepository, times(1)).findById(id);
    }

    @Test
    void getAll() {
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa());
        pessoas.add(new Pessoa());

        Pageable pageable = mock(Pageable.class);
        Page<Pessoa> page = new PageImpl<>(pessoas);

        when(pessoaRepository.findAll(pageable)).thenReturn(page);

        Page<Pessoa> retrievedPage = domainPessoaService.getAll(pageable);

        assertNotNull(retrievedPage);
        assertEquals(2, retrievedPage.getTotalElements());
        assertEquals(pessoas, retrievedPage.getContent());

        verify(pessoaRepository, times(1)).findAll(pageable);
    }
}
