package com.bolsadeideas.springboot.di.app.services;

import org.springframework.stereotype.Service;

@Service
public class MiServicio implements IServicio{

    @Override
    public String operacion(){
        return "ejecutando algún proceso importante...";
    }
}