package com.bolsadeideas.springboot.di.app.models.services;

/*@Service("miServicioSimple")
@Primary*/
public class MiServicio implements IServicio{

    @Override
    public String operacion(){
        return "ejecutando algún proceso importante simple...";
    }
}