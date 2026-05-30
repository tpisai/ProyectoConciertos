package com.conciertos.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "compras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    private Double montoTotal;

    private LocalDateTime fechaCompra;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "concierto_id")
    private Concierto concierto;

    @ManyToOne
    @JoinColumn(name = "tipo_entrada_id")
    private TipoEntrada tipoEntrada;
}