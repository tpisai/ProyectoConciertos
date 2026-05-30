package com.conciertos.backend.service.impl;

import com.conciertos.backend.entity.Concierto;
import com.conciertos.backend.repository.ConciertoRepository;
import com.conciertos.backend.service.ConciertoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConciertoServiceImpl implements ConciertoService {

    private final ConciertoRepository conciertoRepository;

    @Override
    public List<Concierto> listar() {
        return conciertoRepository.findAll();
    }

    @Override
    public Concierto guardar(Concierto concierto) {
        return conciertoRepository.save(concierto);
    }

    @Override
    public Concierto buscarPorId(Long id) {
        return conciertoRepository.findById(id).orElse(null);
    }

    @Override
    public Concierto actualizar(Long id, Concierto concierto) {

        Concierto existente = conciertoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Concierto no encontrado"));

        existente.setNombre(concierto.getNombre());
        existente.setArtista(concierto.getArtista());
        existente.setFecha(concierto.getFecha());
        existente.setLugar(concierto.getLugar());
        existente.setImagen(concierto.getImagen());

        return conciertoRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        conciertoRepository.deleteById(id);
    }
}