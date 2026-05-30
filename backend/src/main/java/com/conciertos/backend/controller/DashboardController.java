package com.conciertos.backend.controller;

import com.conciertos.backend.dto.DashboardDTO;
import com.conciertos.backend.repository.BusquedaArtistaRepository;
import com.conciertos.backend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DashboardController {

    private final DashboardService dashboardService;
    private final BusquedaArtistaRepository busquedaArtistaRepository;

    @GetMapping
    public DashboardDTO obtenerDashboard() {
        return dashboardService.obtenerDashboard();
    }
    @GetMapping("/top-artistas")
    public List<Object[]> topArtistas() {
        return busquedaArtistaRepository.topArtistas();
    }
}