package com.bolsadeideas.springboot.error.app.services;

import com.bolsadeideas.springboot.error.app.models.domain.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> listar();
    Usuario obtenerPorId(Integer id);
}