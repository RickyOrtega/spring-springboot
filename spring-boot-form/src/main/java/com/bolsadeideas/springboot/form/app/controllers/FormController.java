package com.bolsadeideas.springboot.form.app.controllers;

import com.bolsadeideas.springboot.form.app.models.domain.Usuario;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class FormController {
    @GetMapping("/form")
    public String form(Model model){
        Object usuario = new Usuario();
        model.addAttribute("titulo", "Formulario usuarios");
        model.addAttribute("user", usuario);
        return "form";
    }

    @PostMapping("/form")
    public String procesar(@Valid @ModelAttribute("user") Usuario usuario, BindingResult result, Model model) {

        model.addAttribute("titulo", "Resultado form");

        if(result.hasErrors()){
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(e -> {
                errores.put(e.getField(), "El campo "
                        .concat(e.getField())
                        .concat(" ")
                        .concat(Objects.requireNonNull(e.getDefaultMessage())));
            });
            model.addAttribute("error", errores);
            return "form";
        }

        model.addAttribute("usuario", usuario);

        return "resultado";
    }
}