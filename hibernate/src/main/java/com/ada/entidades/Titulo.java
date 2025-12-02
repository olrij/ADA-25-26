package com.ada.entidades;

import java.util.List;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "titulo")
public class Titulo {
	
	@EmbeddedId
	private PkTitulo pk;
	
	private String descripcion;
	
	@ManyToMany(mappedBy = "titulos")
	private List<Persona> personas;
	
	public Titulo() {
		
	}

	public Titulo(PkTitulo pk, String descripcion) {
		super();
		this.pk = pk;
		this.descripcion = descripcion;
	}

	public PkTitulo getPk() {
		return pk;
	}

	public void setPk(PkTitulo pk) {
		this.pk = pk;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	@Override
	public String toString() {
		return "Titulo [pk=" + pk + ", descripcion=" + descripcion + "]";
	}
	

}
