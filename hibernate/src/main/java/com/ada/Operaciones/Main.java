package com.ada.Operaciones;

import java.time.LocalDate;

import com.ada.entidades.Persona;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
    	EntityManagerFactory emf =Persistence.createEntityManagerFactory("miBD");
    	
    	EntityManager em = emf.createEntityManager();
    	

    	//borrarPersonaX(100,em);
    	
    	Persona personaNueva=new Persona(200,"RRRRRRRRRRRR","UUUUUUUUUUUUU",LocalDate.of(1800,12,12));
    	
    	insertarPersonaConMerge(personaNueva,em);
    	
    	
    	
    	
    	em.close();
    	
    	
  
    	
    
    	
    	
    }
    
    public static void borrarPersonaX(int id,EntityManager em) {
    	
    	
    	EntityTransaction t = em.getTransaction();
    	
    	t.begin();
    	
    	Persona p=em.find(Persona.class, id);
    	
    	if(p!=null) {
    		em.remove(p);
    		System.out.println("Persona borrada");
    	}else {
    		System.out.println("No existe la persona");
    	}
    	
    	t.commit();
    	
    	
    	
    }
    
    public static void insertarPersonaConMerge(Persona p,EntityManager em) {
    	
    	
    	EntityTransaction t = em.getTransaction();
    	
    	t.begin();

    	
    	Persona p2=em.merge(p);
    	
    	
    	p2.setDireccion("XXX");
    	
    	
    	
    	t.commit();
    	
    	
    	
    }
    
    
    
    
    
    
    
    
}