package com.bolsadeideas.springboot.di.app.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("miServicioPrincipal")
@Primary
public class MiServicio implements IServicio{

    @Override
    public String operacion(){
        return "ejecutando algún proceso importante simple...";
    }
}