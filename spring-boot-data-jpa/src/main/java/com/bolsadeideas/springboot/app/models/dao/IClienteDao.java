package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import org.springframework.transaction.annotation.Transactional;

public interface IClienteDao extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c LEFT JOIN FETCH c.facturas f WHERE c.id=?1")
    public Cliente fetchByIdWithFacturas(Long id);
}