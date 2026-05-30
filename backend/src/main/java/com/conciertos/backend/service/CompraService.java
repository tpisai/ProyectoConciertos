package com.conciertos.backend.service;

import com.conciertos.backend.dto.CompraRequest;
import com.conciertos.backend.entity.Compra;

import java.util.List;

public interface CompraService {

    Compra comprar(CompraRequest request);

    List<Compra> listarPorUsuario(Long usuarioID);
}