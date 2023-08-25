package com.bolsadeideas.springboot.error.app.services;

import com.bolsadeideas.springboot.error.app.models.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listar();
    Usuario obtenerPorId(Integer id);
    Optional<Usuario> obtenerPorIdOptional(Integer id);
}