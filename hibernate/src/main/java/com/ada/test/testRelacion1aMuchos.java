package com.ada.test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.ada.entidades.Direccion;
import com.ada.entidades.Dni;
import com.ada.entidades.Factura;
import com.ada.entidades.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class testRelacion1aMuchos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//insertarFacturas();
		//insertarPersonas();
		
		//insertarDatos();
		
		borrarPersona1();
		//consultarPersona();
		
		
		
		//borrarFactura();
		
		
		//actualizarDesdePersona();
	}

	public static void insertarDatos() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		// Creaci�n persona
		Dni dni1 = new Dni("1", new GregorianCalendar(2020, 12, 1).getTime(),
				new GregorianCalendar(2020, 12, 1).getTime(), "Juan", "Mar�a");

		Direccion direccion1 = new Direccion("Abc", "12 A", "C�rdoba", 14000);

		Persona persona1 = new Persona(1, "Lucas", direccion1, new GregorianCalendar(2020, 12, 1).getTime(), dni1, null, null);

		Dni dni2 = new Dni("2", new GregorianCalendar(2020, 12, 1).getTime(),
				new GregorianCalendar(2020, 12, 1).getTime(), "Juan", "Mar�a");

		Direccion direccion2 = new Direccion("Abc", "12 A", "C�rdoba", 14000);

		Persona persona2 = new Persona(2, "Lucas2", direccion1, new GregorianCalendar(2020, 12, 1).getTime(), dni2, null, null);

		// Creacci�n facturas

		Factura f1 = new Factura(1, 800.0, "Amazon", persona1);

		Factura f2 = new Factura(2, 600.0, "Amazon", persona1);
		Factura f3 = new Factura(3, 100.15, "Decathlon", persona1);
		Factura f4 = new Factura(4, 800.0, "Amazon", persona2);

		// Asignar facturas a personas

		List<Factura> fp1 = new ArrayList<Factura>();

		List<Factura> fp2 = new ArrayList<Factura>();

		fp1.add(f1);

		fp1.add(f2);

		fp1.add(f3);

		fp2.add(f4);
		
		persona1.setFacturas(fp1);
		persona2.setFacturas(fp2);

		// Guardar la persona

		em.getTransaction().begin();

		em.persist(persona1);

		em.persist(persona2);

		em.getTransaction().commit();
	}

	public static void insertarFacturas() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		// Creaci�n persona
		Dni dni1 = new Dni("1", new GregorianCalendar(2020, 12, 1).getTime(),
				new GregorianCalendar(2020, 12, 1).getTime(), "Juan", "Mar�a");

		Direccion direccion1 = new Direccion("Abc", "12 A", "C�rdoba", 14000);

		Persona persona1 = new Persona(1, "Lucas", direccion1, new GregorianCalendar(2020, 12, 1).getTime(), dni1, null, null);

		Dni dni2 = new Dni("2", new GregorianCalendar(2020, 12, 1).getTime(),
				new GregorianCalendar(2020, 12, 1).getTime(), "Juan", "Mar�a");

		Direccion direccion2 = new Direccion("Abc", "12 A", "C�rdoba", 14000);

		Persona persona2 = new Persona(2, "Lucas2", direccion1, new GregorianCalendar(2020, 12, 1).getTime(), dni2, null, null);

		// Creacci�n facturas

		Factura f1 = new Factura(1, 800.0, "Amazon", persona1);

		Factura f2 = new Factura(2, 600.0, "Amazon", persona1);
		Factura f3 = new Factura(3, 100.15, "Decathlon", persona1);
		Factura f4 = new Factura(4, 800.0, "Amazon", persona2);

		// Guardar la facturas

		em.getTransaction().begin();

		em.persist(f1);

		em.persist(f2);

		em.persist(f3);

		em.persist(f4);

		em.getTransaction().commit();
	}

	public static void consultarPersona() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Persona p1 = em.find(Persona.class, 1);

		System.out.println(p1);

		em.getTransaction().commit();
	}

	public static void consultarFactura(int idPersona) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Factura f = em.find(Factura.class, idPersona);

		System.out.println(f.getPersona());

		em.getTransaction().commit();
	}

	public static void actualizarDesdePersona() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Persona p1 = em.find(Persona.class, 1);

		List<Factura> facturas = p1.getFacturas();
		
		for(Factura f:facturas) {
			f.setTotal(f.getTotal()+10);
		}
		
		p1.setNombre("Juana");

		em.getTransaction().commit();

	}

	public static void actualizarDesdeFactura() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Factura f=em.find(Factura.class, 1);

		Persona p=f.getPersona();
		
		f.setTienda("Mercadona");
		p.getDireccion().setNum("200X");

		em.getTransaction().commit();

	}



	public static void borrarPersona1() {
		
		// Borramos una persona y sus facturas
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		//Encontramos
		Persona p1 = em.find(Persona.class, 1);

		
		//Borrar
		em.remove(p1);

		em.getTransaction().commit();

	}
	
	public static void borrarPersona2() {
		
		// Borramos una persona y sus facturas se matienen 
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		//Encontramos
		Persona p1 = em.find(Persona.class, 1);

		
		//Desvincular. Para ello cogemos cada factura y eliminamos su relaci�n con Persona

		List<Factura> facturasP1 = p1.getFacturas();
		
		for(Factura f:facturasP1) {
			f.setPersona(null);
		}
		
		//Borrar
		em.remove(p1);

		em.getTransaction().commit();

	}
	
	public static void borrarFactura() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Factura f = em.find(Factura.class, 4);


		// Borrar
		em.remove(f);

		em.getTransaction().commit();

	}

}
