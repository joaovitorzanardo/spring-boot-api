package com.uri.springbootapi.services;

import com.uri.springbootapi.dtos.TarefaDTO;
import com.uri.springbootapi.entities.Tarefa;
import com.uri.springbootapi.exceptions.TarefaNotFoundException;
import com.uri.springbootapi.repositories.TarefaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TarefaService tarefaService;

    private TarefaDTO tarefaDTO;
    private Tarefa tarefa;

    @BeforeEach
    public void setUp() {
        tarefaDTO = new TarefaDTO();
        Date localDate = new Date();
        tarefaDTO.setId(1L);
        tarefaDTO.setDescricao("Test Description");
        tarefaDTO.setDataCriacao(LocalDate.of(2023,10,1));
        tarefaDTO.setDataLimite(LocalDate.of(2023,10,10));
        tarefaDTO.setFinalizada(false);

        tarefa = new Tarefa();
        tarefa.setId(1L);
        tarefa.setDescricao("Test Description");
        tarefa.setDataCriacao(LocalDate.of(2023,10,1));
        tarefa.setDataLimite(LocalDate.of(2023,10,10));
        tarefa.setFinalizada(false);
    }

    @Test
    public void testCriarTarefa() {
        when(modelMapper.map(any(TarefaDTO.class), any(Class.class))).thenReturn(tarefa);
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefa);

        Tarefa createdTarefa = tarefaService.criarTarefa(tarefaDTO);

        assertNotNull(createdTarefa);
        assertEquals(tarefaDTO.getDescricao(), createdTarefa.getDescricao());
        verify(tarefaRepository, times(1)).save(tarefa);
    }

    @Test
    public void testGetTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        tarefas.add(tarefa);
        when(tarefaRepository.findAll()).thenReturn(tarefas);

        List<Tarefa> retrievedTarefas = tarefaService.getTarefas();

        assertNotNull(retrievedTarefas);
        assertEquals(1, retrievedTarefas.size());
        verify(tarefaRepository, times(1)).findAll();
    }

    @Test
    public void testGetTarefaById() {
        when(tarefaRepository.findById(anyLong())).thenReturn(Optional.of(tarefa));

        Tarefa retrievedTarefa = tarefaService.getTarefaById(1L);

        assertNotNull(retrievedTarefa);
        assertEquals(tarefa.getDescricao(), retrievedTarefa.getDescricao());
        verify(tarefaRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetTarefaByIdNotFound() {
        when(tarefaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(TarefaNotFoundException.class, () -> {
            tarefaService.getTarefaById(1L);
        });

        verify(tarefaRepository, times(1)).findById(1L);
    }

    @Test
    public void testEditarTarefa() {
        when(tarefaRepository.findById(anyLong())).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefa);

        Tarefa updatedTarefa = tarefaService.editarTarefa(tarefaDTO);

        assertNotNull(updatedTarefa);
        assertEquals(tarefaDTO.getDescricao(), updatedTarefa.getDescricao());
        verify(tarefaRepository, times(1)).findById(tarefaDTO.getId());
        verify(tarefaRepository, times(1)).save(tarefa);
    }

    @Test
    public void testExcluirTarefa() {
        when(tarefaRepository.findById(anyLong())).thenReturn(Optional.of(tarefa));
        doNothing().when(tarefaRepository).delete(any(Tarefa.class));

        tarefaService.excluirTarefa(1L);

        verify(tarefaRepository, times(1)).findById(1L);
        verify(tarefaRepository, times(1)).delete(tarefa);
    }

    @Test
    public void testFinalizarTarefa() {
        when(tarefaRepository.findById(anyLong())).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefa);

        tarefaService.finalizarTarefa(1L);

        assertTrue(tarefa.isFinalizada());
        verify(tarefaRepository, times(1)).findById(1L);
        verify(tarefaRepository, times(1)).save(tarefa);
    }
}
