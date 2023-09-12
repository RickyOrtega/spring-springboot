package com.bolsadeideas.springboot.app.controllers;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Factura;
import com.bolsadeideas.springboot.app.models.entity.ItemFactura;
import com.bolsadeideas.springboot.app.models.entity.Producto;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

    @Autowired
    private IClienteService clienteService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/form/{clienteId}")
    public String crear(@PathVariable(value = "clienteId") Long clienteId,
                        Map<String, Object> model,
                        RedirectAttributes flash){

        Cliente cliente = clienteService.findOne(clienteId);

        if(cliente == null){
            flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
            return "redirect:/listar";
        }

        Factura factura = new Factura();
        factura.setCliente(cliente);

        model.put("factura", factura);
        model.put("titulo", "Crear Factura");

        return "factura/form";
    }

    @GetMapping(value = "/cargar-productos/{term}", produces = {"application/json"})
    public @ResponseBody List<Producto> cargarProductos(@PathVariable String term){
        return clienteService.findByNombre(term);
    }

    @GetMapping("/ver/{id}")
    public String ver(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash){

//        Factura factura = clienteService.findFacturaById(id);

        Factura factura = clienteService.fetchByIdWithClienteWithItemFacturaWithProducto(id);

        if(factura == null){
            flash.addFlashAttribute("error", "¡La factura no existe en la base de datos!");
            return "redirect:/listar";
        }

        model.addAttribute("factura", factura);
        model.addAttribute("Titulo", "Factura ".concat(factura.getDescripcion()));

        return "factura/ver";
    }

    @PostMapping("/form")
    public String guardar(@Valid Factura factura, BindingResult result, Model model, @RequestParam(name="item_id[]", required = false) String[] sItemId, @RequestParam(name="cantidad[]", required = false) Integer[] cantidad, RedirectAttributes flash, SessionStatus status) {

        logger.info("Entró");

        //Convirtiendo el item_id a Long
        List<Long> itemId = new ArrayList();

        for (int i = 0; i < sItemId.length; i++) {

            itemId.add(Long.valueOf(sItemId[i].split("_")[1]));

            System.out.println("Valor del Long: " + itemId.get(i));
            System.out.println("Valor del String: " + sItemId[i]);
        }

        if(result.hasErrors()){
            model.addAttribute("titulo", "Crear Factura");
            return "factura/form";
        }

        if(itemId == null || itemId.size() == 0){
            model.addAttribute("titulo", "Crear Factura");
            model.addAttribute("error", "Error: La factura NO puede no tener líneas.");
            return "factura/form";
        }

        for(int i = 0; i < itemId.size(); i++){
            Producto producto = clienteService.findProductoById(itemId.get(i));

            ItemFactura linea = new ItemFactura();
            linea.setCantidad(cantidad[i]);
            linea.setProducto(producto);
            factura.addItemFactura(linea);

            logger.info("ID: ".concat(String.valueOf(itemId.get(i))).concat(", cantidad: ").concat(Arrays.toString(cantidad)));
        }

        clienteService.saveFactura(factura);

        status.setComplete();

        flash.addFlashAttribute("success", "¡Factura creada con éxito!");

        return "redirect:/ver/".concat(String.valueOf(factura.getCliente().getId()));
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id")Long id, RedirectAttributes flash){

        Factura factura = clienteService.findFacturaById(id);

        if(factura!= null){
            clienteService.deleteFactura(id);
            flash.addFlashAttribute("success", "Factura eliminada con éxito.");
            return "redirect:/ver/".concat(String.valueOf(factura.getCliente().getId()));
        }
        flash.addFlashAttribute("error", "La factura no se pudo eliminar");
        return "redirect:/listar";
    }
}