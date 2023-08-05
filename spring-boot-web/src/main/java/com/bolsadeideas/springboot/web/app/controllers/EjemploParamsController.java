package com.bolsadeideas.springboot.web.app.controllers;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/params")
public class EjemploParamsController {

    @GetMapping("/")
    public String index(){
        return "params/index";
    }

    @GetMapping("/string")
    public String param(@RequestParam(name="texto", required = false, defaultValue = "Algún valor...") String texto, Model model){
        model.addAttribute("resultado", "El texto enviado es: ".concat(texto));
        return "params/ver";
    }

    @GetMapping("/mix-params")
    public String param(@RequestParam String saludo, @RequestParam Integer numero, Model model){
        model.addAttribute("resultado", "El saludo enviado es: ".concat(saludo).concat(" y el número es ").concat(String.valueOf(numero)));
        return "params/ver";
    }

    @GetMapping("/mix-params-request")
    public String param(HttpServletRequest request, Model model){
        String saludo = request.getParameter("saludo");
        Integer numero = null;
        try{
            numero = Integer.parseInt(request.getParameter("numero"));
        } catch (NumberFormatException ex){
            numero = 0;
        }
        model.addAttribute("saludo", "El saludo enviado es: '".concat(saludo).concat("', y el número enviado es: '").concat(String.valueOf(numero)).concat("'"));
        return "params/ver";
    }
}