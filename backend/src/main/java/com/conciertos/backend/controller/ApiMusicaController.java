package com.conciertos.backend.controller;

import com.conciertos.backend.service.ApiMusicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/artistas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ApiMusicaController {

    private final ApiMusicaService apiMusicaService;

    @GetMapping("/{nombre}")
    public String buscarArtista(@PathVariable String nombre) {

        return apiMusicaService.buscarArtista(nombre);
    }
}