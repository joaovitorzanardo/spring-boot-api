package com.uri.springbootapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name="tarefa")
@Table(name = "tarefa")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
