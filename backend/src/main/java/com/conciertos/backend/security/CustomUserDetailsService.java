package com.conciertos.backend.security;

import com.conciertos.backend.entity.Usuario;
import com.conciertos.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(
            String correo
    ) throws UsernameNotFoundException {

        Usuario usuario =
                usuarioRepository
                        .findByCorreo(correo)
                        .orElseThrow();

        return User.builder()
                .username(usuario.getCorreo())
                .password(usuario.getPassword())
                .roles(usuario.getRol().name())
                .build();
    }
}