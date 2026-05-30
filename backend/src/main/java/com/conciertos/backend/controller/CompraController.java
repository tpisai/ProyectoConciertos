package com.conciertos.backend.controller;

import com.conciertos.backend.dto.CompraRequest;
import com.conciertos.backend.entity.Compra;
import com.conciertos.backend.service.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CompraController {

    private final CompraService compraService;

    @PostMapping
    public Compra comprar(
            @RequestBody CompraRequest request) {

        return compraService.comprar(request);
    }

    @GetMapping("/usuario/{id}")
    public List<Compra> comprasPorUsuario(
            @PathVariable Long id) {

        return compraService.listarPorUsuario(id);
    }
}