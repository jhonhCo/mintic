package com.co.udea.mintic.proyecto.proyecto.services;

import com.co.udea.mintic.proyecto.proyecto.domain.Persona;
import com.co.udea.mintic.proyecto.proyecto.repository.EntityPermisos;
import com.co.udea.mintic.proyecto.proyecto.repository.EntityPersona;
import com.co.udea.mintic.proyecto.proyecto.repository.RepositoryPermisos;
import com.co.udea.mintic.proyecto.proyecto.repository.RepositotyPersona;
import com.co.udea.mintic.proyecto.proyecto.util.EnumRol;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//nota:
//1 creamos un  objeto en domain con nombre apellido edad etc
//2.luego creamos una clase que se llama programaAcademico  para comenzar a trabajar  con el objeto persona
//3. luego  creamos una variable para el nombre del programaAcadémico
//4. Creamos un método llamado inscribir con los datos del modelo retorna la variable inscripción
//5. Vamos a controller para hacer el puente entre la vista y services
@Service //  se le entregan datos para que el service los procese.
public class ServicePersona {


    @Autowired
    RepositotyPersona repositotyPersona;
    @Autowired
    RepositoryPermisos  repositotyPermisos;



    ArrayList<Persona> listaP ;


    public ServicePersona(ArrayList<Persona> listaP) {
        this.listaP = listaP;
    }

    @Getter // me trae los Getter
    @Setter // me trae los Setter
    private String nombrePrograma; // tenemos  varios programas academicos y al cual le damos un nombre

    public String inscribirAlumno(Persona alumno){ // se crea esta metodo para inscribir

        String inscripcion = "El alumno"+alumno.getNombre()+""+alumno.getApellido()+"con"+alumno.getEdad()+"años, quedo inscrito en el programa" ;

      return inscripcion;
    }

    public ArrayList doWhile(int a){
        ArrayList<String> objTraza=new ArrayList();
        do{

            System.out.println("hola mundo"+a);
            objTraza.add("hola mundo"+String.valueOf(a));
            a++;

        }while(a<10);

     return objTraza;
    }

    public boolean agregarPersona(Persona persona){


        Persona objPersona = new Persona();// instancio Y CARGO LOS DATOS QUE VIENEN DEL POSMAN
        // ESTA LOGICA DEBE IR EN EL SERVICES
        objPersona.setNombre(persona.getNombre());
        objPersona.setApellido(persona.getApellido());
        objPersona.setEdad(persona.getEdad());
        objPersona.setId(persona.getId());

        listaP.add(objPersona);
        return Boolean.TRUE;
    }


    public ArrayList<Persona> listar (){
        System.out.println("Metodo listar del services");
        return listaP;
    }

 public Persona buscarPersona(int id){// RECIBO UN ID Y UN RETORNO UN OBJETO PERSONA

       Persona persona =null;// INICIALIZO EL OBJETO PERSONA EN NULL
     for (Persona p:listaP) { // busco en la lista la persona con ese id y cuando lo encuentro lo retorno
     if (p.getId()==id){
         return p;
     }
     }
    return persona; // si no le encuentra  en la lista retorna null
 }

 // AGREGAR PERSONA CON CC
 public boolean agregarPersonaCC(Persona persona, String doc){


     Persona objPersona = new Persona();// instancio Y CARGO LOS DATOS QUE VIENEN DEL POSMAN
     // ESTA LOGICA DEBE IR EN EL SERVICES
     objPersona.setNombre(persona.getNombre());
     objPersona.setApellido(persona.getApellido());
     objPersona.setEdad(persona.getEdad());
     objPersona.setId(persona.getId());
     objPersona.setDoc(doc);
     System.out.println("creo la persona  con cedula de ciudadania ");

     listaP.add(objPersona);
     return Boolean.TRUE;
 }

 //AGREGAR PERSONA CON TI
 public boolean agregarPersonaTI(Persona persona, String doc){


     Persona objPersona = new Persona();// instancio Y CARGO LOS DATOS QUE VIENEN DEL POSMAN
     // ESTA LOGICA DEBE IR EN EL SERVICES
     objPersona.setNombre(persona.getNombre());
     objPersona.setApellido(persona.getApellido());
     objPersona.setEdad(persona.getEdad());
     objPersona.setId(persona.getId());
     objPersona.setDoc(doc);

     System.out.println("creo la persona  con tarjeta de identidad ");
     listaP.add(objPersona);
     return Boolean.TRUE;
 }
// BORRAR PERSONA
    public Boolean borrarPersona(Persona persona){

        listaP.remove(persona);
        return Boolean.TRUE;
    }

///////////// ESTE METODO LO UTILIZAMOS PARA EL GETMAPPING//////////////
    public List<EntityPersona> listarTodosJPA(){

       List<EntityPersona> list = repositotyPersona.findAll();

    return list;
    }

    ///////////// ESTE METODO LO UTILIZAMOS PARA EL POSTMAPPING PARA GUARDAR UNA PERSONA//////////////
    public  Boolean insertarPersonaJPA (EntityPersona persona) {

        try {
            repositotyPersona.save(persona); // SI SE EJECUTA BIEN GUADA Y EN LA PARTE INFERIOR RETORNA UN TRE
        } catch (Exception E) {
            return Boolean.FALSE;// SI NO RETORNA FALSE
        }
        return Boolean.TRUE;
    }
    ///////////// ESTE METODO LO UTILIZAMOS PARA EL PUTMAPPING PARA ACTUALIZACION TOTAL  PERSONA//////////////
    public Boolean actualizarTodoJPA(EntityPersona persona){


        try {
            repositotyPersona.save(persona); // SI SE EJECUTA BIEN GUADA Y EN LA PARTE INFERIOR RETORNA UN TRE
        } catch (Exception E) {
            return Boolean.FALSE;// SI NO RETORNA FALSE
        }
        return Boolean.TRUE;
    }

    ///////////// ESTE METODO LO UTILIZAMOS PARA EL PATCHMAPPING PARA ACTUALIZACION PARCIAL  PERSONA//////////////
    // HACEMOS PRIMERO LA BUSQUEDA
    public void actualizarParcialJPA(EntityPersona persona) {
        // VOY A LA BASE DE DATOS Y POR MEDIO DEL ID BUSCO EL DATO SI NO LO ENCUENTRO RETORNO UN NULL
        //SI NO LOS TRAIGO A VARIABLE personaTemporta
        EntityPersona personaTemp = repositotyPersona.findById(persona.getId()).orElse(null);
        if (persona.getNombre() != null) {
            personaTemp.setNombre(persona.getNombre());

        } else if (persona.getApellido() != null) {
            personaTemp.setApellido(persona.getApellido());

        } else if (persona.getEdad() != null) {
            personaTemp.setEdad(persona.getEdad());

        } else if (persona.getDoc() != null) {
            personaTemp.setDoc(persona.getDoc());

        } else if (persona.getPassword() != null) {
            personaTemp.setPassword(persona.getPassword());
        }

        repositotyPersona.save(personaTemp);

    }

   public void borrarPersonaJpa(Long id){

      repositotyPersona.deleteById(id);

   }

public void insertarPersonaRol(EntityPersona persona){

     if(persona.getRol().equals(EnumRol.ADMIN)){

     repositotyPersona.save(persona);// guardo la persona
         EntityPermisos ePerTmp = new EntityPermisos(true, true, true, true, persona);
         repositotyPermisos.save(ePerTmp);
     } else if (persona.getRol().equals(EnumRol.USER)) {
         repositotyPersona.save(persona);// guardo la persona
         EntityPermisos ePerTmp = new EntityPermisos(true, false, false, true, persona);
         repositotyPermisos.save(ePerTmp);

     } else if (persona.getRol().equals(EnumRol.VISITANTE)) {
         repositotyPersona.save(persona);// guardo la persona
         EntityPermisos ePerTmp = new EntityPermisos(true, false, false, false, persona);
         repositotyPermisos.save(ePerTmp);
     } else {

     System.err.println("no se pudo obtener el Rol");
     }

}

}
