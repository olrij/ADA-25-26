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
    	
    	Persona persona1=new Persona(100,"Pepito","Calle Esclava del Señor SN",LocalDate.of(2000, 12, 31));
    	
    	EntityTransaction t = em.getTransaction();
    	
    	t.begin();
    	
    	em.persist(persona1);
    	
    	t.commit();
    	
    	em.close();
    	
    	
    	
    	
    	
    	
    
    	
    	
    }
}