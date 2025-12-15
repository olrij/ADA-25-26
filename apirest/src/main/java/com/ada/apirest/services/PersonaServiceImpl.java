package com.ada.apirest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ada.apirest.entities.Persona;
import com.ada.apirest.repositories.PersonaRepository;

@Service
public class PersonaServiceImpl implements PersonaService {


    @Autowired
    PersonaRepository personaRepository;



    @Override
    public Iterable<Persona> findAll() {

        return personaRepository.findAll();

    }

    @Override
    public Optional<Persona> findById(int id) {

        return personaRepository.findById(id);
    }

    @Override
    public Persona save(Persona persona) {
        
        return personaRepository.save(persona);
    }

    @Override
    public void deleteById(int id) {
        personaRepository.deleteById(id);
    }

}
