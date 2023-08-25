package com.bolsadeideas.springboot.error.app.services;

import com.bolsadeideas.springboot.error.app.models.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    private List<Usuario> lista;

    public UsuarioServiceImpl() {
        this.lista = new ArrayList<>();
        this.lista.add(new Usuario(1, "Andrés", "Guzmán"));
        this.lista.add(new Usuario(2, "Pepa", "García"));
        this.lista.add(new Usuario(3, "Lalo", "Mena"));
        this.lista.add(new Usuario(4, "Luci", "Fernández"));
        this.lista.add(new Usuario(5, "Pato", "González"));
        this.lista.add(new Usuario(6, "Pazo", "Rodríguez"));
        this.lista.add(new Usuario(7, "Juan", "Gómez"));

    }

    @Override
    public List<Usuario> listar() {
        return this.lista;
    }

    @Override
    public Usuario obtenerPorId(Integer id) {
        Usuario res = null;

        for (Usuario usuario: this.lista) {
            if(usuario.getId().equals(id)){
                return usuario;
            }
        }
        return null;
    }
}