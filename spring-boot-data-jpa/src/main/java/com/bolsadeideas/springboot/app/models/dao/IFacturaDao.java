package com.bolsadeideas.springboot.app.models.dao;

import com.bolsadeideas.springboot.app.models.entity.Factura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IFacturaDao extends CrudRepository<Factura, Long>{
    @Query("SELECT f from Factura f JOIN FETCH f.cliente c JOIN FETCH f.items l JOIN FETCH  l.producto WHERE f.Id=?1 ")
    public Factura fetchByIdWithClienteWithItemFacturaWithProducto(Long id);
}