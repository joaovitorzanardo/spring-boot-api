package com.uri.springbootapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tarefa")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Tarefa {

    @Id
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @Column(name = "data_limite")
    private LocalDate dataLimite;

    @Column(name = "finalizada")
    private boolean finalizada;



}
