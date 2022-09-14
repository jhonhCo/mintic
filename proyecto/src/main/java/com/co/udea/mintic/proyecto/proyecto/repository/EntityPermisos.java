package com.co.udea.mintic.proyecto.proyecto.repository;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="permisos")
public class EntityPermisos{

    @Id //
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="lectura")
    private boolean lectura;
    @Column(name="escritura")
    private boolean escritura;
    @Column(name="actualizacion")
    private boolean actualizacion;
    @Column(name="borrar")
    private boolean borrar;

    @JoinColumn(name="id_persona",referencedColumnName = "id")//  colocamos la columna de persona con la cual quiero hacer entidad referencial que seria id---id_persona
    @ManyToOne(optional = false)// por que una persona puede tener muchos permisos el optional es que sea hobligatorio
    private EntityPersona idPersona;

    public EntityPermisos() {
    }

    public EntityPermisos(boolean lectura, boolean escritura, boolean actualizacion, boolean borrar, EntityPersona idPersona) {
        this.lectura = lectura;
        this.escritura = escritura;
        this.actualizacion = actualizacion;
        this.borrar = borrar;
        this.idPersona = idPersona;
    }
}
