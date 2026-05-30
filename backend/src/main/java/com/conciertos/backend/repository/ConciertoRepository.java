package com.conciertos.backend.repository;

import com.conciertos.backend.entity.Concierto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConciertoRepository extends JpaRepository<Concierto, Long> {
}