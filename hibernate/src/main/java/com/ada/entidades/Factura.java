package com.ada.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "factura")
public class Factura {
	
	@Id
	private int cod_factura;
	
	private double total;
	
	private String tienda;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="deudor",referencedColumnName = "id")
	private Persona persona;
	
	public Factura() {
		
	}

	public Factura(int cod_factura, double total, String tienda, Persona persona) {
		super();
		this.cod_factura = cod_factura;
		this.total = total;
		this.tienda = tienda;
		this.persona = persona;
	}

	public int getCod_factura() {
		return cod_factura;
	}

	public void setCod_factura(int cod_factura) {
		this.cod_factura = cod_factura;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getTienda() {
		return tienda;
	}

	public void setTienda(String tienda) {
		this.tienda = tienda;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return "Factura [cod_factura=" + cod_factura + ", total=" + total + ", tienda=" + tienda + "]";
	}
	
	
	

}
