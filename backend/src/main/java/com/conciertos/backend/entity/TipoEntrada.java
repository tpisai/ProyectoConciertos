package com.conciertos.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name="tipos_entrada")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoEntrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreZona;
    private Double precio;
    private Integer stock;
    private Integer entradasVendidas;

    @ManyToOne
    @JoinColumn(name= "concierto_id")
    @JsonBackReference
    private Concierto concierto;
}
