package com.ada.entidades;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "dni")
public class Dni {
	
	@Id
	private String num;
	private Date f_exp;
	private Date f_cad;
	private String nomPadre;
	private String nomMadre;
	
	@OneToOne(mappedBy = "dni",fetch = FetchType.LAZY)
	private Persona persona;
	
	public Dni() {
		
	}

	public Dni(String num, Date f_exp, Date f_cad, String nomPadre, String nomMadre) {
		super();
		this.num = num;
		this.f_exp = f_exp;
		this.f_cad = f_cad;
		this.nomPadre = nomPadre;
		this.nomMadre = nomMadre;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public Date getF_exp() {
		return f_exp;
	}

	public void setF_exp(Date f_exp) {
		this.f_exp = f_exp;
	}

	public Date getF_cad() {
		return f_cad;
	}

	public void setF_cad(Date f_cad) {
		this.f_cad = f_cad;
	}

	public String getNomPadre() {
		return nomPadre;
	}

	public void setNomPadre(String nomPadre) {
		this.nomPadre = nomPadre;
	}

	public String getNomMadre() {
		return nomMadre;
	}

	public void setNomMadre(String nomMadre) {
		this.nomMadre = nomMadre;
	}
	
	

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return "Dni [num=" + num + ", f_exp=" + f_exp + ", f_cad=" + f_cad + ", nomPadre=" + nomPadre + ", nomMadre="
				+ nomMadre + "]";
	}
	

}
