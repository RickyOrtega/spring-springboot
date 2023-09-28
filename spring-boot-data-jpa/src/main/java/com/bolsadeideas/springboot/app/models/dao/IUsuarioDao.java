package com.bolsadeideas.springboot.app.models.dao;

import com.bolsadeideas.springboot.app.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao extends CrudRepository <Usuario, Long> {
    Usuario findByUsername(String username);
}