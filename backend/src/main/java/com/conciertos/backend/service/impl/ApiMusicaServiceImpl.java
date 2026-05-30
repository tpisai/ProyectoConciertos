package com.conciertos.backend.service.impl;

import com.conciertos.backend.entity.BusquedaArtista;
import com.conciertos.backend.repository.BusquedaArtistaRepository;
import com.conciertos.backend.service.ApiMusicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ApiMusicaServiceImpl implements ApiMusicaService {

    private final RestTemplate restTemplate;
    private final BusquedaArtistaRepository busquedaArtistaRepository;

    @Override
    public String buscarArtista(String nombre) {

        String url =
                "https://itunes.apple.com/search?term=" + nombre + "&entity=musicArtist";

        BusquedaArtista busqueda = BusquedaArtista.builder().nombreArtista(nombre)
                .fechaBusqueda(LocalDateTime.now()).build();

        busquedaArtistaRepository.save(busqueda);

        return restTemplate.getForObject(url, String.class);
    }
}