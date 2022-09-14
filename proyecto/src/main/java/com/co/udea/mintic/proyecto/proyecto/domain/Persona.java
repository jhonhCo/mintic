package com.co.udea.mintic.proyecto.proyecto.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

 public class Persona {


    @Getter@Setter
    private String nombre;
    @Getter@Setter
    private String apellido;
    @Getter@Setter
    private int edad;
    @Getter@Setter
    private int id;
     @Getter@Setter
    private String doc;


}