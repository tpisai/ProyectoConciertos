package com.conciertos.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDTO {

    private Long totalUsuarios;

    private Long totalConciertos;

    private Long totalEntradasVendidas;

    private Long totalEntradasDisponibles;

    private Double ingresosTotales;

    private Double gananciaEmpresa;

    private Double costosOperativos;

    private String conciertoMasVendido;

    private String conciertoMasRentable;
}