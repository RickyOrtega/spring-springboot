package com.bolsadeideas.springboot.di.app.controllers;

import com.bolsadeideas.springboot.di.app.services.MiServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private MiServicio servicio = new MiServicio();
    @GetMapping({"/index", "/", ""})
    public String index(Model model){
        model.addAttribute("titulo", "Probando los servicios sin inyección de dependencias");
        model.addAttribute("objeto", servicio.operacion());
        return "index";
    }
}