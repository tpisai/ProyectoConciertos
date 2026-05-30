package com.conciertos.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "busquedas_artistas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusquedaArtista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreArtista;

    private LocalDateTime fechaBusqueda;
}