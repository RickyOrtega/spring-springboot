package com.bolsadeideas.springboot.di.app.models.domain;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.List;

@Component
/* @SessionScope
* @ApplicationScope*/
@RequestScope
public class Factura implements Serializable {
    @Value("${factura.definicion}")
    private String descripcion;
    @Autowired
    private Cliente cliente;

    @Autowired
    private List<ItemFactura> items;

    @PostConstruct
    public void init(){
        cliente.setNombre(cliente.getNombre()
                .concat(" ")
                .concat("José"));
        descripcion = descripcion.concat(" del cliente ").concat(cliente.getNombre());
    }

    @PreDestroy
    public void destruir(){
        System.out.println("Factura destruida: ".concat(descripcion));
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemFactura> getItems() {
        return items;
    }

    public void setItems(List<ItemFactura> items) {
        this.items = items;
    }
}