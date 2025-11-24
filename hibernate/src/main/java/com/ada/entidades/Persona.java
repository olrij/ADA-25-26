package com.ada.entidades;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "persona")
public class Persona {
	
	@Id
	private int id;
	
	@Column(length = 60, nullable=false)
	private String nombre;
	
	@Column(length=100)
	private String direccion;
	
	@Column(name="fecha_nac")
	private LocalDate f_nac;

	public Persona(int id, String nombre, String direccion, LocalDate f_nac) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.f_nac = f_nac;
	}
	
	public Persona() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalDate getF_nac() {
		return f_nac;
	}

	public void setF_nac(LocalDate f_nac) {
		this.f_nac = f_nac;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", f_nac=" + f_nac + "]";
	}
	
	
	
	
	
	
	
	
	
	

}
