package com.co.udea.mintic.proyecto.proyecto.controller;

import com.co.udea.mintic.proyecto.proyecto.repository.EntityPersona;
import com.co.udea.mintic.proyecto.proyecto.services.ServicePersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ControllerFrontend {

    @Autowired                                                                           // Sirve para simplificar el new la instanciacion
    ServicePersona servicePersona;



    @GetMapping(path = "/index")

        public String home(Model modelo) {

            modelo.addAttribute("personas", new EntityPersona());

        return "index";
    }


    @GetMapping(path = "/pagina2")
    public String pagina2(Model modelo){

        List<EntityPersona>listPersonas = servicePersona.listarTodosJPA();

        modelo.addAttribute("personas", listPersonas);

        return "pagina2";
    }

}
