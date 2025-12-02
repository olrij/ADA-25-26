package com.ada.entidades;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="persona")
@NamedQuery(name = "consultaTodo",query = "SELECT p FROM Persona p")
public class Persona {
	
	@Id
	private int id;
	@Column(name="nombre",length = 100,unique = true)
	private String nombre;
	@Embedded
	private Direccion direccion;
	@Column(nullable = false)
	private Date f_nac;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="dni",referencedColumnName = "num")
	private Dni dni;
	
	@OneToMany(mappedBy = "persona",fetch = FetchType.LAZY,cascade= CascadeType.ALL)
	private List<Factura> facturas;
	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
	/*@JoinColumns({
        @JoinColumn(name = "annio", referencedColumnName = "annio"),
        @JoinColumn(name = "nombre", referencedColumnName = "nombre")
    })*/
	private List<Titulo> titulos;
	
	
	public Persona(int id, String nombre, Direccion direccion, Date f_nac, Dni dni,List<Factura> facturas,List<Titulo> titulos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.f_nac = f_nac;
		this.dni=dni;
		this.titulos=titulos;
		this.facturas=facturas;
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

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Date getF_nac() {
		return f_nac;
	}

	public void setF_nac(Date f_nac) {
		this.f_nac = f_nac;
	}
	

	public Dni getDni() {
		return dni;
	}

	public void setDni(Dni dni) {
		this.dni = dni;
	}
	

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}
	

	public List<Titulo> getTitulos() {
		return titulos;
	}

	public void setTitulos(List<Titulo> titulos) {
		this.titulos = titulos;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", f_nac=" + f_nac + ", dni="
				+ dni + ", facturas=" + facturas + ", titulos=" + titulos + "]";
	}

}
