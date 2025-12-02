package com.ada.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Direccion {
	
	private String calle;
	@Column(name = "numero")
	private String num;
	private String provincia;
	private int cod_postal;
	
	public Direccion(String calle, String num, String provincia, int cod_postal) {
		super();
		this.calle = calle;
		this.num = num;
		this.provincia = provincia;
		this.cod_postal = cod_postal;
	}
	
	public Direccion() {
		
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public int getCod_postal() {
		return cod_postal;
	}

	public void setCod_postal(int cod_postal) {
		this.cod_postal = cod_postal;
	}

	@Override
	public String toString() {
		return "Direccion [calle=" + calle + ", num=" + num + ", provincia=" + provincia + ", cod_postal=" + cod_postal
				+ "]";
	}

}
