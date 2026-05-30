package com.conciertos.backend.service.impl;

import com.conciertos.backend.dto.CompraRequest;
import com.conciertos.backend.entity.*;
import com.conciertos.backend.repository.*;
import com.conciertos.backend.service.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompraServiceImpl implements CompraService {

    private final UsuarioRepository usuarioRepository;
    private final CompraRepository compraRepository;
    private final ConciertoRepository conciertoRepository;
    private final TipoEntradaRepository tipoEntradaRepository;

    @Override
    public Compra comprar(CompraRequest request) {

        Usuario usuario = usuarioRepository.findById(request.getUsuarioId()).orElseThrow();

        Concierto concierto = conciertoRepository.findById(request.getConciertoId()).orElseThrow();

        TipoEntrada entrada = tipoEntradaRepository.findById(request.getTipoEntradaId()).orElseThrow();

        int disponibles = entrada.getStock() - entrada.getEntradasVendidas();

        if (request.getCantidad() > disponibles) {
            throw new RuntimeException("Stock insuficiente");
        }

        entrada.setEntradasVendidas(entrada.getEntradasVendidas() + request.getCantidad());

        tipoEntradaRepository.save(entrada);
        double monto = entrada.getPrecio() * request.getCantidad();
        Compra compra = Compra.builder()
                .usuario(usuario)
                .concierto(concierto)
                .tipoEntrada(entrada)
                .cantidad(request.getCantidad())
                .montoTotal(monto)
                .fechaCompra(LocalDateTime.now())
                .build();
        return compraRepository.save(compra);
    }
    @Override
    public List<Compra> listarPorUsuario(Long usuarioId) {
        return compraRepository.findByUsuarioId(usuarioId);
    }
}
