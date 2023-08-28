package com.bolsadeideas.springboot.app.models.services;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

import java.util.List;

public interface IClienteService {
    List<Cliente> findAll();
    void save(Cliente cliente);

    Cliente findOne(Long id);

    void delete(Long id);
}