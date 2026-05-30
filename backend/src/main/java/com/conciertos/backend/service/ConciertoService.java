package com.conciertos.backend.service;

import com.conciertos.backend.entity.Concierto;

import java.util.List;

public interface ConciertoService {
    List<Concierto> listar();
    Concierto guardar(Concierto concierto);
    Concierto buscarPorId(Long id);
    Concierto actualizar(Long id, Concierto concierto);
    void eliminar(Long id);
}