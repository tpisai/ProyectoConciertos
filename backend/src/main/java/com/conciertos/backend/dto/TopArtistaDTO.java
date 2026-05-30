package com.conciertos.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopArtistaDTO {

    private String artista;

    private Long cantidadBusquedas;
}