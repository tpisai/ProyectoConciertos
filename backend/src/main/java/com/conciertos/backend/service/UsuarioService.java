package com.conciertos.backend.service;

import com.conciertos.backend.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario guardar(Usuario usuario);
    List<Usuario> listar();
    Usuario buscarPorId(Long id);
    Usuario actualizar(Long id, Usuario usuario);
    void eliminar(Long id);
}