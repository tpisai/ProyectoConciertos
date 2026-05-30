package com.conciertos.backend.controller;

import com.conciertos.backend.dto.LoginRequest;
import com.conciertos.backend.dto.LoginResponse;
import com.conciertos.backend.entity.Rol;
import com.conciertos.backend.entity.Usuario;
import com.conciertos.backend.repository.UsuarioRepository;
import com.conciertos.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Usuario register(@RequestBody Usuario usuario) {
        if (usuario.getCorreo().equalsIgnoreCase("admin@ticketchain.com")
            || usuario.getCorreo().equalsIgnoreCase("isai@ticketchain.com"))
        {
            usuario.setRol(Rol.ADMIN);
        } else {
            usuario.setRol(Rol.USER);
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        return usuarioRepository.save(usuario);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        Usuario usuario = usuarioRepository.findByCorreo(request.getCorreo())
                .orElseThrow();

        boolean passwordCorrecto = passwordEncoder.matches(
                request.getPassword(),
                usuario.getPassword()
        );

        if (!passwordCorrecto) {
            throw new RuntimeException("Password incorrecto");
        }

        String token = jwtService.generarToken(usuario.getCorreo(), usuario.getRol().name());

        return new LoginResponse(token, usuario.getCorreo(), usuario.getRol().name());
    }
}