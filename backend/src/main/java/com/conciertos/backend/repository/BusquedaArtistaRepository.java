package com.conciertos.backend.repository;

import com.conciertos.backend.entity.BusquedaArtista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BusquedaArtistaRepository extends JpaRepository<BusquedaArtista, Long> {
    @Query("""
            SELECT b.nombreArtista,
            COUNT(b)
            FROM BusquedaArtista b
            GROUP BY b.nombreArtista
            ORDER BY COUNT(b) DESC
            """)
    List<Object[]> topArtistas();
}