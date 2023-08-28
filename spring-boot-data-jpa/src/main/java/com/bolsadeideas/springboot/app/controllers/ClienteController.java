package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.dao.IClienteDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

@Controller
@SessionAttributes("cliente")
public class ClienteController {

    @Autowired
    @Qualifier("clienteDaoJPA")//En este ejemplo no es absolutamente necesario hacerlo así, pero sí se recomienda para brindarle escabilidad a la app
    private IClienteDao clienteDao;

    @GetMapping(value = "/listar")
    public String listar(Model model){
        model.addAttribute("titulo", "Listado de clientes");
        model.addAttribute("clientes", clienteDao.findAll());
        model.addAttribute("size", clienteDao.findAll().size());

        return "listar";
    }

    @GetMapping(value = "/form")
    public String crear(Map<String, Object> model){
        model.put("titulo", "Formulario de Cliente");
        Cliente cliente = new Cliente();
        model.put("cliente", cliente);
        return "form";
    }

    @GetMapping(value = "/form/{id}")
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model){

        Cliente cliente = null;

        if(id>0){
            cliente = clienteDao.findOne(id);
        } else {
            return "redirect:/listar";
        }
        model.put("cliente", cliente);
        model.put("titulo", "Editar cliente");
        return "form";
    }

    @PostMapping(value = "/form")
    public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status){

        if(result.hasErrors()){
            model.addAttribute("titulo", "Formulario de cliente");
            return "form";
        }
        clienteDao.save(cliente);
        status.setComplete();
        return "redirect:listar";
    }

    @GetMapping(value = "/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") Long id){

        if(id > 0){
            clienteDao.delete(id);
        }

        return "redirect:/listar";
    }
}