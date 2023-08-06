package com.bolsadeideas.springboot.di.app.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/*@Service("miServicioComplejo")*/
public class MiServicioComplejo implements IServicio{

    @Override
    public String operacion(){
        return "ejecutando algún proceso importante complejo...";
    }
}