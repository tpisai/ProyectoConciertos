package com.conciertos.backend.controller;

import com.conciertos.backend.entity.TipoEntrada;
import com.conciertos.backend.repository.TipoEntradaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-entrada")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TipoEntradaController {

    private final TipoEntradaRepository tipoEntradaRepository;

    @PostMapping
    public TipoEntrada guardar(@RequestBody TipoEntrada tipoEntrada) {
        return tipoEntradaRepository.save(tipoEntrada);
    }

    @GetMapping
    public List<TipoEntrada> listar() {
        return tipoEntradaRepository.findAll();
    }

    @GetMapping("/{id}")
    public TipoEntrada buscarPorId(@PathVariable Long id) {
        return tipoEntradaRepository.findById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        tipoEntradaRepository.deleteById(id);
    }
}