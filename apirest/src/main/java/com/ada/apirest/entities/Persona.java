package com.ada.apirest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "persona")
public class Persona {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer id;
    @Column
    String nombre;
    @Column
    Integer edad;
    @Column
    String correo;

    public Persona(Integer id, String nombre, int edad, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.correo = correo;
    }

    public Persona() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getCorreo() {
        return correo;
    }

    @Override
    public String toString() {
        return "Persona [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", correo=" + correo + "]";
    }


    
}
