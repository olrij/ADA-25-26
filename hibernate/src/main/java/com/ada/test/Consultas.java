package com.ada.test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import com.ada.entidades.Direccion;
import com.ada.entidades.Dni;
import com.ada.entidades.Factura;
import com.ada.entidades.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Consultas {

	public static void main(String[] args) {

		//insertarPersonas();
		//consultaConJoin(5.0);
		
		//consultaPreparada();
		
		consultaConJoin(0);

	}

	public static void consultaPreparada() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		List<Persona> resultado = em.createNamedQuery("consultaTodo").getResultList();

		for (Persona p : resultado) {
			System.out.println(p);
		}

		em.close();
	}

	public static void consultaNormal() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		List<Persona> resultado = em.createQuery("SELECT DISTINCT(p) FROM Persona p").getResultList();

		for (Persona p : resultado) {
			System.out.println(p);
		}

		em.close();
	}

	public static void consulta2() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		Persona resultado = (Persona) em.createQuery("SELECT p FROM Persona p WHERE p.id=1").getSingleResult();

		System.out.println(resultado);

		em.close();
	}

	public static void consulta2ConParametros(int idPersona) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		Persona resultado = (Persona) em.createQuery("SELECT p FROM Persona p WHERE p.id=:idPersona")
				.setParameter("idPersona", idPersona).getSingleResult();

		System.out.println(resultado);

		em.close();
	}

	public static void consultaConJoin(double valor) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		List resultado = em.createQuery("SELECT p,f FROM Persona p, Factura f WHERE f.persona=p AND f.total>:valor").setParameter("valor", valor)
				.getResultList();

		Iterator it = resultado.iterator();

		while (it.hasNext()) {
			Object[] registro = (Object[]) it.next();
			Persona persona = (Persona) registro[0];
			Factura factura = (Factura) registro[1];

			System.out.println("->"+persona + "\r" + factura);
		}

		em.close();
	}
	
	public static void consultaPedidoMasCaro() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		Double resultado = (Double) em.createQuery("SELECT max(f.total) FROM Factura f").getSingleResult();

		System.out.println(resultado);

		em.close();
	}
	
	public static void consultaPersonaPedidoMasCaro() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		List<Persona> resultado =  em.createQuery("SELECT p FROM Persona p, Factura f WHERE f.persona=p AND f.total=(SELECT max(f.total) FROM Factura f)").getResultList();

		for (Persona p : resultado) {
			System.out.println(p);
		}

		em.close();
	}

	public static void consultaConProyeccion() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("miBD");

		EntityManager em = emf.createEntityManager();

		List resultado = em.createQuery("SELECT p.nombre,p.direccion.provincia,p.direccion.cod_postal FROM Persona p")
				.getResultList();

		Iterator it = resultado.iterator();

		while (it.hasNext()) {
			Object[] registro = (Object[]) it.next();
			String nombre = (String) registro[0];
			String provincia = (String) registro[1];
			int cod_postal = (int) registro[2];

			System.out.println(nombre + " " + provincia + " " + cod_postal);
		}

		em.close();
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

		// Guardar la persona

		em.getTransaction().begin();

		em.persist(f1);

		em.persist(f2);
		
		em.persist(f3);

		em.persist(f4);

		em.getTransaction().commit();

		em.close();
	}

}
