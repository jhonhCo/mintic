package com.co.udea.mintic.proyecto.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ControllerLegacy {


    @ResponseBody // Respons=entregar analogo a set   request= pedir analog a get
    @RequestMapping(value="/Users/harcr/Documents/proyectoIj/proyecto2")

    public String mensaje (){
        return "Hola Mundo";
    }





}
