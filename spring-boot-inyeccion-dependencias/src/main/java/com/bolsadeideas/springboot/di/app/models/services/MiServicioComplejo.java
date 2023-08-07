package com.bolsadeideas.springboot.di.app.models.services;

/*@Service("miServicioComplejo")*/
public class MiServicioComplejo implements IServicio{

    @Override
    public String operacion(){
        return "ejecutando algún proceso importante complejo...";
    }
}