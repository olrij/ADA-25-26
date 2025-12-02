package com.ada.test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.ada.entidades.Direccion;
import com.ada.entidades.Dni;
import com.ada.entidades.Persona;
import com.ada.entidades.PkTitulo;
import com.ada.entidades.Titulo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class testRelacionMuchosaMuchos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//insertarPersonas();
		
		//borrarPersona();
		
		
		borrarTitulo();
		
		
		
		
		//busquedaPersona();

	}
	
	public static void busquedaPersona() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Persona p1 = em.find(Persona.class, 2);

		System.out.println(p1);

		em.getTransaction().commit();
	}

	public static void insertarPersonas() {
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

		// Creacci�n titulos

		Titulo t1=new Titulo(new PkTitulo("DAM",2020),"blabna");
		Titulo t2=new Titulo(new PkTitulo("DAW",2020),"blabna");
		Titulo t3=new Titulo(new PkTitulo("ASIR",2020),"blabna");
		Titulo t4=new Titulo(new PkTitulo("SMR",2020),"blabna");

		// Asignar titulos a personas

		List<Titulo> titulosP1 = new ArrayList<Titulo>();

		List<Titulo> titulosP2 = new ArrayList<Titulo>();

		titulosP1.add(t1);
		titulosP1.add(t2);
		titulosP1.add(t3);
		titulosP1.add(t4);
		
		titulosP2.add(t1);
		titulosP2.add(t4);
		
		
		persona1.setTitulos(titulosP1);
		persona2.setTitulos(titulosP2);

		// Guardar la persona

		em.getTransaction().begin();

		em.persist(persona1);

		em.persist(persona2);

		em.getTransaction().commit();
	}

	

	public static void consultarPersona() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Persona p1 = em.find(Persona.class, 1);

		System.out.println(p1.getTitulos());

		em.getTransaction().commit();
	}

	public static void consultarTitulo() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Titulo t = em.find(Titulo.class, new PkTitulo("DAM",2020));

		System.out.println(t.getPersonas());

		em.getTransaction().commit();
	}

	public static void actualizarDesdePersona() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Persona p1 = em.find(Persona.class, 1);

		List<Titulo> titulos = p1.getTitulos();
		
		for(Titulo t:titulos) {
			t.setDescripcion("Muy completo");
		}
		
		p1.setNombre("Juana");

		em.getTransaction().commit();

	}



	public static void borrarPersona() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		//Encontramos
		Persona p1 = em.find(Persona.class, 1);

		
		//Desvincular. Para ello congemos cada factura y eliminamos su relaci�n con Persona		
		//p1.setTitulos(null);
	
		//Borrar
		em.remove(p1);

		em.getTransaction().commit();

	}
	
	
	
	public static void borrarTitulo() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Titulo t=em.find(Titulo.class, new PkTitulo("DAM",2020));

		//Desvincular para no perder el registro de la persona

		List<Persona> personas = t.getPersonas();
		
		for(Persona p:personas) {
			List<Titulo> titulosPersona = p.getTitulos();
			
			titulosPersona.remove(t);
		}

		// Borrar
		em.remove(t);

		em.getTransaction().commit();

	}

}
