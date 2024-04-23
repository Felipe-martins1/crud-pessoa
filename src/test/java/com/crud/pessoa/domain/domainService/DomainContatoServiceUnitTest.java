package com.crud.pessoa.domain.domainService;

import com.crud.pessoa.config.CrudPessoaBusinessException;
import com.crud.pessoa.domain.entity.Contato;
import com.crud.pessoa.domain.entity.Pessoa;
import com.crud.pessoa.domain.repository.ContatoRepository;
import com.crud.pessoa.domain.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DomainContatoServiceUnitTest {

    private DomainContatoService domainContatoService;

    @Mock
    private ContatoRepository contatoRepository;

    @Mock
    private PessoaService pessoaService;

    @BeforeEach
    void setUp() {
        domainContatoService = new DomainContatoService(contatoRepository, pessoaService);
    }

    @Test
    void getAllByPessoa() {
        String pessoaId = "123e4567-e89b-12d3-a456-556642440000"; // Example UUID string
        Pageable pageable = mock(Pageable.class);

        Page<Contato> expectedPage = new PageImpl<>(new ArrayList<>());
        UUID uuid = UUID.fromString(pessoaId);
        when(pessoaService.getById(uuid)).thenReturn(new Pessoa(uuid));
        when(contatoRepository.findAllByPessoa(any(Pessoa.class), eq(pageable))).thenReturn(expectedPage);

        Page<Contato> resultPage = domainContatoService.getAllByPessoa(pessoaId, pageable);

        assertNotNull(resultPage);
        assertEquals(expectedPage, resultPage);

        verify(pessoaService, times(1)).getById(uuid);
        verify(contatoRepository, times(1)).findAllByPessoa(any(Pessoa.class), eq(pageable));
    }

    @Test
    void createContato() {
        Contato contato = new Contato();

        when(contatoRepository.save(any(Contato.class))).thenReturn(contato);

        Contato createdContato = domainContatoService.create(contato);

        assertNotNull(createdContato);
        assertEquals(contato, createdContato);

        verify(contatoRepository, times(1)).save(contato);
    }

    @Test
    void updateContato() {
        Contato contatoToUpdate = new Contato();
        contatoToUpdate.setId(1L);

        Contato existingContato = new Contato();
        existingContato.setId(1L);
        existingContato.setEmail("emailantigo@gmail.com");

        when(contatoRepository.findById(1L)).thenReturn(Optional.of(existingContato));
        when(contatoRepository.save(any(Contato.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Contato updatedContato = domainContatoService.update(contatoToUpdate);

        assertNotNull(updatedContato);
        assertEquals(contatoToUpdate.getId(), updatedContato.getId());

        verify(contatoRepository, times(1)).findById(1L);
        verify(contatoRepository, times(1)).save(any(Contato.class));
    }

    @Test
    void deleteContato() {
        Long id = 1L;
        doNothing().when(contatoRepository).deleteById(id);

        assertDoesNotThrow(() -> domainContatoService.delete(id));

        verify(contatoRepository, times(1)).deleteById(id);
    }

    @Test
    void getById() {
        Long id = 1L;
        Contato expectedContato = new Contato();
        expectedContato.setId(id);

        when(contatoRepository.findById(id)).thenReturn(Optional.of(expectedContato));

        Contato retrievedContato = domainContatoService.getById(id);

        assertNotNull(retrievedContato);
        assertEquals(expectedContato, retrievedContato);

        verify(contatoRepository, times(1)).findById(id);
    }

    @Test
    void getById_ContatoNotFound() {
        Long id = 1L;

        when(contatoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CrudPessoaBusinessException.class, () -> domainContatoService.getById(id));

        verify(contatoRepository, times(1)).findById(id);
    }
}
