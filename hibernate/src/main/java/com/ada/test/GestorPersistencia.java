package test;



import java.util.ArrayList;
import java.util.List;

import entidades.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;



public class GestorPersistencia {

		EntityManagerFactory fabrica;
		EntityManager em;
		
		
		public GestorPersistencia() {
			this.fabrica=Persistence.createEntityManagerFactory("miBD");
			this.em=fabrica.createEntityManager();
		}
		
		public void introducirPersona(Persona p) {
	
			EntityTransaction transaccion=em.getTransaction();
			transaccion.begin();
			em.persist(p);
			transaccion.commit();
		}
		
		public List<Persona> listarPersonas(){
			List<Persona> personas=new ArrayList<Persona>();
			personas= (List<Persona>) em.createQuery("select p from persona").getResultList();
			return personas;
			
		}
		
		
		public void cerrar() {
			this.em.close();
			this.fabrica.close();
		}



}
