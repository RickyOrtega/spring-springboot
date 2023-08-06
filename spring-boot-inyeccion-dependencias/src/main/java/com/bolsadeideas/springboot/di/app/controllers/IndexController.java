package com.bolsadeideas.springboot.di.app.controllers;

import com.bolsadeideas.springboot.di.app.services.IServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    @Qualifier("miServicioPrincipal")
    private IServicio servicio;

/*    public IndexController() {
    }

    public IndexController(IServicio servicio) {
        this.servicio = servicio;
    }*/

    @GetMapping({"/index", "/", ""})
    public String index(Model model){
        model.addAttribute("titulo", "Probando los servicios sin inyección de dependencias");
        model.addAttribute("objeto", servicio.operacion());
        return "index";
    }

/*    @Autowired
    public void setServicio(IServicio servicio) {
        this.servicio = servicio;
    }*/
}