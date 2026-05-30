package com.conciertos.backend.repository;

import com.conciertos.backend.entity.TipoEntrada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoEntradaRepository
        extends JpaRepository<TipoEntrada, Long> {
}