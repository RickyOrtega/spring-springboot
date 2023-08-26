package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.dao.IClienteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class ClienteController {

    @Autowired
    @Qualifier("clienteDaoJPA")//En este ejemplo no es absolutamente necesario hacerlo así, pero sí se recomienda para brindarle escabilidad a la app
    private IClienteDao clienteDao;

    @GetMapping(value = "/listar")
    public String listar(Model model){
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clienteDao.findAll());
        return "listar";
    }

    @GetMapping(value = "/form")
    public String crear(Map<String, Object> model){
        model.put("titulo", "Formulario de Cliente");
        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        return "form";
    }

    @PostMapping(value = "/form")
    public String crear(Cliente cliente){

        clienteDao.save(cliente);

        return "redirect:listar";
    }
}