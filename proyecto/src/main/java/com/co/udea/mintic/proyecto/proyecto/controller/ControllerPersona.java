package com.co.udea.mintic.proyecto.proyecto.controller;

import com.co.udea.mintic.proyecto.proyecto.domain.Persona;
import com.co.udea.mintic.proyecto.proyecto.repository.EntityPersona;
import com.co.udea.mintic.proyecto.proyecto.services.ServicePersona;
import com.co.udea.mintic.proyecto.proyecto.util.EnumRol;
import com.co.udea.mintic.proyecto.proyecto.util.utilUtilidadesComunes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;

// 6. Creamos una clase llamada ControllerProgramaAcademico donde se envía los datos al navegador
// 7.  Creamos una método callServicePrograma para llamar al servicio por medio de una instanciación
// 8. el metodo  callServicePrograma va a utilizar a ServiceProgramaAcademico y a su vez ServiceProgramaAcademico
// utiliza datos de persona que se encuentra en domain
// 9. Llamo a services pero como necesito datos de persona tengo que instanciarlo
// 10. Cargamos los datos de persona atreves de los set nombre, apellido
// 11. Para poder mostrar o pintar datos en un navegador se debe marcar con la siguiente notación @GetMapping
// el cual recibe parámetros el path =" ruta por la cual se va a ingresar" y produces = "tipo de salida" puede se html txt e 90% son json
// 12. Revisamos el programa con el martillo en la parte superior derecha y luego nos vamos  a  proyectoAplicaion y de damos run
// 13. Copiamos esta dirección y ya nos debe mostrar la salida que se cargó en services  http://localhost:8080/Users/harcr/Documents/proyectoIj/proyecto



@RestController// Nos da la posibilidad de lanzarlo a un navegador web "thymeleaf  no soporta @Rescontroller"
@CrossOrigin
@RequestMapping(value="/persona")//todos los mappin que se hagan tendran antepuesto el nombre person en la url
public class ControllerPersona {


    // CUANDO QUEREMOS QUE UN METODO ARRANQUE CUANDO SE INICIALIZA EL PROGRAMA UTILIZAMOS UN CONSTRUCTOR
    // public ControllerProgramaAcademico(ArrayList<Persona> listaP) {
    //  this.listaP = listaP;
    // }

    @Autowired                                                                           // Sirve para simplificar el new la instanciacion
    ServicePersona servicePersona;                                    // de est forma se instancia se reemplaza el new
    @Autowired
    utilUtilidadesComunes utilUtilidadesComunes;

    @GetMapping(path = "/Users/harcr/Documents/proyectoIj/proyecto", produces = "application/json")

    public ResponseEntity<String> callServicePrograma() {//ResponserEntity es un tipo de dato que astrae cualquier tipo de objeto
        Persona objPersona = new Persona();

        objPersona.setNombre("caro"); // cargo el nombre de persona
        objPersona.setApellido("Romero");// cargo el apellido
        objPersona.setEdad(34); // cargamos la edad.

        String salida = servicePersona.inscribirAlumno(objPersona);

        return new ResponseEntity<String>(salida, HttpStatus.NOT_FOUND);                      // llamamos el metodo inscribir alumno, como persona no  hace
        // parte de services lo tengo que intanciar para porderlo utiliza
    }

    // http://localhost:8080/Users/harcr/Documents/proyectoIj/proyecto3
    @GetMapping(path = "/Users/harcr/Documents/proyectoIj/proyecto3", produces = "application/json")
    public ArrayList doWhileController() {

        servicePersona.doWhile(7);

        ArrayList<String> salida = new ArrayList<>();

        salida = servicePersona.doWhile(7);

        return salida;
    }

    // primer GET Sin parametros retorna vacio cuando hacemos el Get desde el posman
    // retorna informacion clase 4 se indica la ruta en el path y pe produces retornamos aplication json se utilza la clase mediaType
    @GetMapping(path = "/udea/mintic/listaPersonas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Persona> listaPersonas() {

        utilUtilidadesComunes.mensaje();
        System.out.println("ingreso el metodo listarPersonas");

        return servicePersona.listar();
    }

    // METODO POST PARA ESCRIBIR // DIFETENTE AL GET LO  QUE SE PROCESA ES DE TIPO APLICATION JSON Y ENTRAGA JSON
    // ESTE METODO VA A RECIBIR LO QUE VENGA DEL REQUEST Y SE PASA COMO ARGUMENTO EN EL METODO CREAR PERSONA DESPUES DE LA NOTACION LE DOY UN TIPO Y UN NOMBRE
    @PostMapping(path = "/udea/mintic/crearPersonas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona) {

        boolean salida = servicePersona.agregarPersona(persona);

        if (salida == true) {
            return new ResponseEntity<Persona>(persona, HttpStatus.OK);
        } else {
            return new ResponseEntity("error de ejecucion", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // GET CON PARAMETROS
// se le pasa a la clase buscarPersona el Pathvariable para poder utilizar el id
    @GetMapping(path = "/udea/mintic/buscarPersona/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> buscarPersonas(@PathVariable int id) {

        Persona p = servicePersona.buscarPersona(id);
        if (p != null) {
            return new ResponseEntity<Persona>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity("Error en ejecución", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST CON PARAMETROS
// VAMOS A CREAR UN PERSONA PERO DEPENDIENDO SI ES CC O TI LO VAMOS A MANDAR A HACER ALGO ESPECIFICO
    @PostMapping(path = "/udea/mintic/crearPersonas/{doc}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> crearPersonaCondicional(@RequestBody Persona persona, @PathVariable String doc) {

        //PUEDO CREAR UN BUSCAR ANTES DE CREAR LA PERSONA PARA EVITAR DUPLICADOS Y SI YA EXISTE PUEDO RETORNAR UN MJ QUE YA EXISTE


        switch (doc) {
            case "CC":
                servicePersona.agregarPersonaCC(persona, doc);
                break;
            case "TI":
                servicePersona.agregarPersonaTI(persona, doc);
                break;
            default:
                return new ResponseEntity("error de ejecucion", HttpStatus.INTERNAL_SERVER_ERROR);


        }


        return new ResponseEntity<Persona>(persona, HttpStatus.OK);
    }


    // CREAR UNA PUT ACTALIZACION
    /// vamos a construir los 3 metosdos put patch delete
    //Put= son métodos diseñados para actualizar recursos
    //Patch= hace  solo una actualización
    //Delete= borrar
    //14 nesetiamos saber a quien vamos a actualizar y por que vamos a actualizar
    //15 vamos a cambiar el nombre de maria por el nombre de marta en el  @RequestParam
    @PutMapping(path = "/udea/mintic/actualizarPersona", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Persona> actualizarPersona(@RequestParam int id, @RequestParam String nombreModificado) {

        Persona p = servicePersona.buscarPersona(id);

        p.setNombre(nombreModificado);// RETORNA LA ENTIDAD PERSONA CON EL NOMBRE MODIFICADO SE GUADA EN p
        System.out.println("metodo put");
        return new ResponseEntity<Persona>(p, HttpStatus.OK);
    }

    // CREAR UN PATCH ACTUALIZACION PARCIAS
    @PatchMapping(path = "/udea/mintic/actualizarPP", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> actualizarPersonaParcial() {

        String retorno = "actualizacion parcial de persona";
        System.out.println("OK METODO PATCH ");
        return new ResponseEntity<String>(retorno, HttpStatus.OK);
    }

// CREAR DELETE ELIMINAR

    @DeleteMapping(path = "/udea/mintic/borrarPersona/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> borrarPersona(@PathVariable int id) {


        Persona p = servicePersona.buscarPersona(id);

        Boolean salida = servicePersona.borrarPersona(p);

        return new ResponseEntity<Boolean>(salida, HttpStatus.OK);
    }


    /////////////////INICIO SCRUD CON BASE DE  DATOS ///////////////////////////////////////////////////

    ////////////////      MOETODO GET             ///////////////////////////////////////////////////

    //// estructura de un metodo visibilidad , tipo nombre (argumentos) y body
    @GetMapping(path = "/udea/mintic/listarTodosJPA", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> listarTodos() {

        return new ResponseEntity<Object>(servicePersona.listarTodosJPA(), HttpStatus.OK);

    }

    ////////////////      MOETODO POST GUARDAR INSERTAR ///////////////////////////////////////////////////
    @PostMapping(path = "/udea/mintic/insertarPersonaJPA", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> insertarPersonaJPA(@RequestBody EntityPersona persona) {


        return new ResponseEntity<Boolean>(servicePersona.insertarPersonaJPA(persona), HttpStatus.OK);

    }

    ////////////////      METODO PUT ACTUALIZAR TOTAL ///////////////////////////////////////////////////
    /// PARA ACTUALIZAR LOS DATOS PRIMERO TENGO QUE BUSCAR POR QUE NO PUEDO ACTUALIZAR ALGO QUE NO EXISTE Y LO BUSCAMOS POR EL ID //
    @PutMapping(path = "/udea/mintic/actualizarTodoJPA", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> actualizarTodoJPA(@RequestBody EntityPersona persona) {

        return new ResponseEntity<Boolean>(servicePersona.actualizarTodoJPA(persona), HttpStatus.OK);
    }


    ////////////////      METODO PACTH ACTUALIZAR PARCIAL ///////////////////////////////////////////////////
    /// PARA ACTUALIZAR LOS DATOS PRIMERO TENGO QUE BUSCAR POR QUE NO PUEDO ACTUALIZAR ALGO QUE NO EXISTE Y LO BUSCAMOS POR EL ID //
    @PatchMapping(path = "/udea/mintic/actualizarParcialJPA", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void actualizarParcialJPA(@RequestBody EntityPersona persona) {

        servicePersona.actualizarParcialJPA(persona);

        //return new ResponseEntity<Boolean>(servicePersona.actualizarParcialJPA(persona) ,HttpStatus.OK);
    }

    @DeleteMapping(path = "/udea/mintic/borrarPersonaJPA/{id}")
    public void borrarPersonaJPA(@PathVariable Long id) {

        servicePersona.borrarPersonaJpa(id);

    }

    @PostMapping(path = "/udea/mintic/isertarPersonaRol", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void isertarPersonaRol(@RequestBody EntityPersona persona) { // guarda una persona con el rol y asignarle uno permisos segun el rol

        servicePersona.insertarPersonaRol(persona);
    }
}