package com.ada.entidades;

import jakarta.persistence.Embeddable;

@Embeddable
public class PkTitulo {
	
	private String nombre;
	private int annio;
	
	public PkTitulo() {
		
	}

	public PkTitulo(String nombre, int annio) {
		super();
		this.nombre = nombre;
		this.annio = annio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAnnio() {
		return annio;
	}

	public void setAnnio(int annio) {
		this.annio = annio;
	}

	@Override
	public String toString() {
		return "PkTitulo [nombre=" + nombre + ", annio=" + annio + "]";
	}
	
	

}
