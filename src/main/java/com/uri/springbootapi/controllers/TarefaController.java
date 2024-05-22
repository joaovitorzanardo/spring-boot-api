package com.uri.springbootapi.controllers;

import com.uri.springbootapi.dtos.SucessResponse;
import com.uri.springbootapi.dtos.TarefaDTO;
import com.uri.springbootapi.entities.Tarefa;
import com.uri.springbootapi.services.TarefaService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/tasks")
public class TarefaController {

    @Autowired
    TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<SucessResponse> criarTarefa(@RequestBody @Valid TarefaDTO tarefaDTO) {
        Tarefa tarefa = tarefaService.criarTarefa(tarefaDTO);
        SucessResponse sucessResponse = SucessResponse.builder()
                .httpStatus(HttpStatus.CREATED.value())
                .mensagem("Tarefa criada com sucesso!")
                .objectId(tarefa.getId())
                .build();
        return ResponseEntity.status(sucessResponse.getHttpStatus()).body(sucessResponse);
    }

    @GetMapping
    public List<Tarefa> getTarefas() {
        return tarefaService.getTarefas();
    }

    @GetMapping(path = "/{tarefaId}")
    public Tarefa getTarefaById(@PathVariable Long tarefaId) {
        return tarefaService.getTarefaById(tarefaId);
    }

    @PutMapping(path = "/{tarefaId}")
    public ResponseEntity<SucessResponse> editarTarefa(@PathVariable Long tarefaId, @RequestBody @Valid TarefaDTO tarefaDTO) {
        tarefaDTO.setId(tarefaId);
        Tarefa tarefa = tarefaService.editarTarefa(tarefaDTO);
        SucessResponse sucessResponse = SucessResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .mensagem("Tarefa alterada com sucesso!")
                .objectId(tarefa.getId())
                .build();
        return ResponseEntity.status(sucessResponse.getHttpStatus()).body(sucessResponse);
    }

    @DeleteMapping(path = "/{tarefaId}")
    public ResponseEntity<SucessResponse> excluirTarefa(@PathVariable Long tarefaId) {
        tarefaService.excluirTarefa(tarefaId);
        SucessResponse sucessResponse = SucessResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .mensagem("Tarefa excluida com sucesso!")
                .objectId(tarefaId)
                .build();
        return ResponseEntity.status(sucessResponse.getHttpStatus()).body(sucessResponse);
    }

    @PutMapping(path = "/{tarefaId}/finalizar")
    public ResponseEntity<SucessResponse> finalizarTarefa(@PathVariable Long tarefaId) {
        tarefaService.finalizarTarefa(tarefaId);
        SucessResponse sucessResponse = SucessResponse.builder()
                .httpStatus(HttpStatus.OK.value())
                .mensagem("Tarefa Finalizada!")
                .objectId(tarefaId)
                .build();
        return ResponseEntity.status(sucessResponse.getHttpStatus()).body(sucessResponse);
    }

}
