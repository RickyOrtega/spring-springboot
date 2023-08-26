package com.bolsadeideas.springboot.app.models.entity.dao;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

import java.util.List;

public interface IClienteDao {
    List<Cliente> findAll();
}