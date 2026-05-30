package com.conciertos.backend.service.impl;

import com.conciertos.backend.dto.DashboardDTO;
import com.conciertos.backend.entity.Concierto;
import com.conciertos.backend.entity.TipoEntrada;
import com.conciertos.backend.repository.ConciertoRepository;
import com.conciertos.backend.repository.UsuarioRepository;
import com.conciertos.backend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UsuarioRepository usuarioRepository;
    private final ConciertoRepository conciertoRepository;

    @Override
    public DashboardDTO obtenerDashboard() {

        long totalUsuarios = usuarioRepository.count();

        long totalConciertos = conciertoRepository.count();

        long entradasVendidas = 0;

        long entradasDisponibles = 0;

        double ingresosTotales = 0;

        String conciertoMasVendido = "Sin datos";

        String conciertoMasRentable = "Sin datos";

        long maxVentas = 0;

        double maxIngresos = 0;

        for (Concierto concierto : conciertoRepository.findAll()) {

            long ventasConcierto = 0;

            double ingresosConcierto = 0;

            if (concierto.getTiposEntrada() == null) {
                continue;
            }

            for (TipoEntrada entrada : concierto.getTiposEntrada()) {

                int vendidas =
                        entrada.getEntradasVendidas() == null
                                ? 0
                                : entrada.getEntradasVendidas();

                int stock =
                        entrada.getStock() == null
                                ? 0
                                : entrada.getStock();

                double precio =
                        entrada.getPrecio() == null
                                ? 0
                                : entrada.getPrecio();

                entradasVendidas += vendidas;

                entradasDisponibles += (stock - vendidas);

                ingresosTotales += (precio * vendidas);

                ventasConcierto += vendidas;

                ingresosConcierto += (precio * vendidas);
            }

            if (ventasConcierto > maxVentas) {

                maxVentas = ventasConcierto;

                conciertoMasVendido =
                        concierto.getNombre();
            }

            if (ingresosConcierto > maxIngresos) {

                maxIngresos = ingresosConcierto;

                conciertoMasRentable =
                        concierto.getNombre();
            }
        }

        double gananciaEmpresa = ingresosTotales * 0.30;

        double costosOperativos = ingresosTotales * 0.70;

        return new DashboardDTO(
                totalUsuarios,
                totalConciertos,
                entradasVendidas,
                entradasDisponibles,
                ingresosTotales,
                gananciaEmpresa,
                costosOperativos,
                conciertoMasVendido,
                conciertoMasRentable
        );
    }
}