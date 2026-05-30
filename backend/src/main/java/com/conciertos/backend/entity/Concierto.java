package com.conciertos.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "conciertos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Concierto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String artista;
    private LocalDate fecha;
    private String lugar;
    private String imagen;
    @OneToMany(mappedBy = "concierto",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private List<TipoEntrada> tiposEntrada;
}