package com.conciertos.backend.controller;

import com.conciertos.backend.entity.Concierto;
import com.conciertos.backend.service.ConciertoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conciertos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ConciertoController {

    private final ConciertoService conciertoService;

    @GetMapping
    public List<Concierto> listar() {
        return conciertoService.listar();
    }

    @GetMapping("/{id}")
    public Concierto obtener(@PathVariable Long id) {
        return conciertoService.buscarPorId(id);
    }

    @PostMapping
    public Concierto guardar(@RequestBody Concierto concierto) {
        return conciertoService.guardar(concierto);
    }

    @PutMapping("/{id}")
    public Concierto actualizar(
            @PathVariable Long id,
            @RequestBody Concierto concierto) {

        return conciertoService.actualizar(id, concierto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        conciertoService.eliminar(id);
    }
}