package com.co.udea.mintic.proyecto.proyecto.repository;


// esta clase se va a comportar como el mapeo relacional de datos desde mi codigo hacia la base de datos
// se convierte esta clase en una entity clase para esto se apoya en jpa con anotaciones parecido a lombok

import com.co.udea.mintic.proyecto.proyecto.util.EnumRol;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data   // PARA PODER INGRESAR A LOS DATOS LOS DEBE ENCAPSULAR Y PARA ELLOR NECESITO LOS SETT Y GETTER
@Entity
@Table (name="persona")  //la clase se va a mapear contra la tabla persona
public class EntityPersona {

    @Id // ESTA ANOTACION CONVIETE LA COLUNA COMO PRIMARY KEY
    @GeneratedValue (strategy = GenerationType.AUTO) // ESTA NOTACION SE VA A  AUTOGENERAR DINAMICAMENTE  SEGUN EL VALOR DEL Id
    private Long id; // PARA EL Id JPA NO RECONOCE LOS DATOS PRIMITIVOS  POR ESO SE CAMBIA A LONG

    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="edad")
    private Long edad;
    @Column(name="doc")
    private String doc;
    @Column(name="pasword")
    private String password;

    @Column(name="rol")
    @Enumerated(EnumType.STRING)// INIDICA EL TIPO DE CAST DE EL VALOR ROL PARA QUE LO TOME COMO UN ENTERO
    private EnumRol rol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPersona")
    @JsonIgnore
    private Collection<EntityPermisos> permisosColletion;
}
