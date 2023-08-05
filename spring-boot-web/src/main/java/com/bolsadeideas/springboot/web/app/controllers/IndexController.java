package com.bolsadeideas.springboot.web.app.controllers;

import com.bolsadeideas.springboot.web.app.models.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/app")
public class IndexController {

    @Value("${texto.indexcontroller.index.titulo}") private String textoIndex;
    @Value("${texto.indexcontroller.listar.titulo}")private String textoListar;

    @GetMapping ({"/index", "/home", "/", ""})
    public String index(Model model){
        model.addAttribute("titulo", textoIndex);
        return "index";
    }

    @RequestMapping("/perfil")
    public String perfil(Model model){
        Usuario usuario = new Usuario();
        usuario.setNombre("Ricky");
        usuario.setApellido("Ortega");
        usuario.setEmail("ortegavitalricky@gmail.com");
        model.addAttribute("usuario", usuario);
        model.addAttribute("titulo", usuario.getNombre().concat(" ").concat(usuario.getApellido()));
        return "perfil";
    }

    @RequestMapping("/listar")
    public String listar(Model model){
        model.addAttribute("titulo", textoListar);
        return "listar";
    }

    @ModelAttribute("usuarios")
    public List<Usuario> poblarUsuarios(){
        List<Usuario> usuarios = Arrays.asList(new Usuario("Andrés", "Guzmán", "andres@correo.com"),
                new Usuario("Ricky", "Ortega", "ortegavitalricky@gmail.com"),
                new Usuario("John", "Doe", "john@fakemail.com"),
                new Usuario("Pablo", "Popeye", "pablito@fakemail.com"));
        return usuarios;
    }
}