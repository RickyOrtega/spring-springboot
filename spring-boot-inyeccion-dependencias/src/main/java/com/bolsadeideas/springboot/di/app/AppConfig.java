package com.bolsadeideas.springboot.di.app;

import com.bolsadeideas.springboot.di.app.models.domain.ItemFactura;
import com.bolsadeideas.springboot.di.app.models.domain.Producto;
import com.bolsadeideas.springboot.di.app.models.services.IServicio;
import com.bolsadeideas.springboot.di.app.models.services.MiServicio;
import com.bolsadeideas.springboot.di.app.models.services.MiServicioComplejo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean("miServicioSimple")
    @Primary
    public IServicio registrarMiServicio(){
        return new MiServicio();
    }
    @Bean("miServicioSimpleComplejo")
    public IServicio registrarMiServicioComplejo(){
        return new MiServicioComplejo();
    }

    @Bean("itemsFactura")
    public List<ItemFactura> registrarItems(){
        Producto producto1 = new Producto("Cámara Sony", 100);
        Producto producto2 = new Producto("Bicicleta Bianchi aro 26", 200);

        ItemFactura linea1 = new ItemFactura(producto1, 2);
        ItemFactura linea2 = new ItemFactura(producto2, 4);

        return Arrays.asList(linea1, linea2);
    }

    @Bean("itemsFacturaOficina")
    @Primary
    public List<ItemFactura> registrarItemsOficina(){
        Producto producto1 = new Producto("Monitor LG LCD 24\"", 250);
        Producto producto2 = new Producto("Notebook Asus TUF GAMING f15", 500);
        Producto producto3 = new Producto("Impresora HP Multifuncional", 80);
        Producto producto4 = new Producto("Escritorio oficina", 300);

        ItemFactura linea1 = new ItemFactura(producto1, 2);
        ItemFactura linea2 = new ItemFactura(producto2, 1);
        ItemFactura linea3 = new ItemFactura(producto3, 1);
        ItemFactura linea4 = new ItemFactura(producto4, 1);

        return Arrays.asList(linea1, linea2, linea3, linea4);
    }
}