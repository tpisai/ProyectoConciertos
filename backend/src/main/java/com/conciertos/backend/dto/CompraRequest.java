package com.conciertos.backend.dto;

import lombok.Data;

@Data
public class CompraRequest {

    private Long usuarioId;

    private Long conciertoId;

    private Long tipoEntradaId;

    private Integer cantidad;
}