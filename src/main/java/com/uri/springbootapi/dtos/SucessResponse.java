package com.uri.springbootapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SucessResponse {

    private Integer httpStatus;
    private String mensagem;
    private Long objectId;

}
