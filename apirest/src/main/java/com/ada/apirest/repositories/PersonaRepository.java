package com.ada.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ada.apirest.entities.Persona;

public interface PersonaRepository extends JpaRepository<Persona,Integer> {

}
