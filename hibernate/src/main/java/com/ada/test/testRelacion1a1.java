package com.ada.test;

import java.util.GregorianCalendar;

import com.ada.entidades.Direccion;
import com.ada.entidades.Dni;
import com.ada.entidades.Persona;
import com.ada.entidades.PkTitulo;
import com.ada.entidades.Titulo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityTransaction;

public class testRelacion1a1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//insertarDatos();
		
		//consultarPersona();
		//consultarDni();
		
		//actualizarDesdePersona();
		
		//borrarPersona();
		borrarDni();
	}

	public static void insertarDatos() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		Dni dni1 = new Dni("1", new GregorianCalendar(2020, 12, 1).getTime(),
				new GregorianCalendar(2020, 12, 1).getTime(), "Juan", "Mar�a");

		Direccion direccion1 = new Direccion("Abc", "12 A", "C�rdoba", 14000);

		Persona persona1 = new Persona(1, "Lucas", direccion1, new GregorianCalendar(2020, 12, 1).getTime(), dni1, null, null);

		em.getTransaction().begin();
		
		em.persist(persona1);

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

	public static void consultarDni() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Dni dni1 = em.find(Dni.class, "1");
		
		System.out.println("\t"+dni1);

		//System.out.println(dni1.getPersona());
		

		em.getTransaction().commit();
	}

	public static void actualizarDesdePersona() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Persona p1 = em.find(Persona.class, 1);

		Dni dni1 = p1.getDni();
		
		p1.setNombre("Laura");
		
		dni1.setNomMadre("Juanaaaaaaaaa");

		em.getTransaction().commit();

	}
	
	public static void actualizarDesdeDni() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Dni dni1 = em.find(Dni.class, "1");

		Persona p1 = dni1.getPersona();
		
		p1.setNombre("Laura2");
		
		dni1.setNomMadre("Juana2");
		
		System.out.println(dni1);

		em.getTransaction().commit();
		
		

	}

	// Borrar entidad dependiente
	public static void borrarDni() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Dni dni1 = em.find(Dni.class, "1");
		
		

		//Desvincular 
		Persona p1 = dni1.getPersona();
		p1.setDni(null);
	
		//Borrar
		em.remove(dni1);

		em.getTransaction().commit();

	}
	
	// Borrar entidad propietaria
	public static void borrarPersona() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Persona p1 = em.find(Persona.class, 1);
		
		em.remove(p1);

		em.getTransaction().commit();

	}

}
