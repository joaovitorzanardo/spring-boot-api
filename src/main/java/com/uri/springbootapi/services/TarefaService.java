package com.uri.springbootapi.services;

import com.uri.springbootapi.dtos.TarefaDTO;
import com.uri.springbootapi.entities.Tarefa;
import com.uri.springbootapi.exceptions.TarefaNotFoundException;
import com.uri.springbootapi.repositories.TarefaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    ModelMapper modelMapper;

    public Tarefa criarTarefa(TarefaDTO tarefaDTO) {
        Tarefa tarefa = modelMapper.map(tarefaDTO, Tarefa.class);
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> getTarefas() {
         return tarefaRepository.findAll();
    }

    public Tarefa getTarefaById(Long tarefaId) {
        return tarefaRepository.findById(tarefaId)
                .orElseThrow(TarefaNotFoundException::new);
    }

    public Tarefa editarTarefa(TarefaDTO tarefaDTO) {
        Tarefa tarefa = tarefaRepository.findById(tarefaDTO.getId())
                .orElseThrow(TarefaNotFoundException::new);

        tarefa.setDescricao(tarefaDTO.getDescricao());
        tarefaDTO.setDataCriacao(tarefaDTO.getDataCriacao());
        tarefaDTO.setDataLimite(tarefaDTO.getDataLimite());
        tarefaDTO.setFinalizada(tarefaDTO.isFinalizada());

        return tarefaRepository.save(tarefa);
    }

    public void excluirTarefa(Long tarefaId) {
        Tarefa tarefa = tarefaRepository.findById(tarefaId)
                .orElseThrow(TarefaNotFoundException::new);

        tarefaRepository.delete(tarefa);
    }

}
