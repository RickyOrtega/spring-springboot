package com.bolsadeideas.springboot.di.app;

import com.bolsadeideas.springboot.di.app.services.IServicio;
import com.bolsadeideas.springboot.di.app.services.MiServicio;
import com.bolsadeideas.springboot.di.app.services.MiServicioComplejo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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

}