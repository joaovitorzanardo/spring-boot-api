package com.uri.springbootapi.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaDTO {

    private Long id;

    @NotEmpty
    private String descricao;

    @NotNull
    private LocalDate dataCriacao;

    @NotNull
    private LocalDate dataLimite;

    @NotNull
    private boolean finalizada;

}
