package com.uri.springbootapi.exceptions;

public class TarefaNotFoundException extends RuntimeException{
    public TarefaNotFoundException() {
        super("Tarefa não encontrada");
    }
}
