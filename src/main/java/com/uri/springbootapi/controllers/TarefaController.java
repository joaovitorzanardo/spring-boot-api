package com.uri.springbootapi.controllers;

import com.uri.springbootapi.dtos.SucessResponse;
import com.uri.springbootapi.dtos.TarefaDTO;
import com.uri.springbootapi.entities.Tarefa;
import com.uri.springbootapi.services.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/task")
@Tag(name = "API Tarefa")
public class TarefaController {

    @Autowired
    TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Criar Tarefa", description = "Salvar a tarefa no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso!"),
    })
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
    @Operation(summary = "Buscar Tarefas", description = "Buscar todas as tarefas salvas no banco de dados")
    public List<Tarefa> getTarefas() {
        return tarefaService.getTarefas();
    }

    @GetMapping(path = "/{tarefaId}")
    @Operation(summary = "Buscar Tarefas por Id", description = "Busca uma tarefa pelo seu respectivo id")
    public Tarefa getTarefaById(@PathVariable Long tarefaId) {
        return tarefaService.getTarefaById(tarefaId);
    }

    @PutMapping(path = "/{tarefaId}")
    @Operation(summary = "Editar Tarefa", description = "Alterar as informações da tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa alterada com sucesso!"),
            @ApiResponse(responseCode = "410", description = "Tarefa não encontrada.")
    })
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
    @Operation(summary = "Excluir Tarefa", description = "Realiza a exclusão de uma tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa excluida com sucesso!"),
            @ApiResponse(responseCode = "410", description = "Tarefa não encontrada.")
    })
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
