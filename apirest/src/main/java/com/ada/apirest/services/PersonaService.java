package com.ada.apirest.services;

import java.util.Optional;

import com.ada.apirest.entities.Persona;

public interface PersonaService {

    public Iterable<Persona> findAll();

    public Optional<Persona> findById(int id);

    public Persona save(Persona persona);

    public void deleteById(int id);



}
