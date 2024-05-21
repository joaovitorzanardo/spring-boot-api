package com.uri.springbootapi;

import com.uri.springbootapi.dtos.ErrorResponse;
import com.uri.springbootapi.exceptions.TarefaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TarefaNotFoundException.class)
    public ResponseEntity<com.uri.springbootapi.dtos.ErrorResponse> handleTarefaNotFoundException(TarefaNotFoundException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .mensagem(ex.getMessage())
                .httpStatus(HttpStatus.GONE.value()).build();
        return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
    }

}
