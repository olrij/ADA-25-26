package com.ada.apirest.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ada.apirest.entities.Persona;
import com.ada.apirest.services.PersonaService;


@RestController
@RequestMapping("/apirest/personas")
public class PersonaController {

    // Inyección de dependencias
    @Autowired
    public PersonaService personaService;


    @PostMapping
    public ResponseEntity<?> create(@RequestBody Persona persona) {
        
        if (persona == null) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                  .body("El cuerpo de la solicitud no puede estar vacío.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(personaService.save(persona));
    
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> read(@PathVariable(value = "id") Integer personaId) {

        Optional<Persona> oPersona = personaService.findById(personaId);
    
        // Si no hay resultados
        if (!oPersona.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        // Si sí hay
        return ResponseEntity.ok(oPersona);


    }


    @GetMapping
    public ResponseEntity<?> readAll() {
        // Conviertimos los usuarios en una lista
        List<Persona> personas = StreamSupport
                .stream(personaService.findAll().spliterator(), false)
                .collect(Collectors.toList());


        // Verificamos si la lista está vacía
        if (personas.isEmpty()) {
            return ResponseEntity.noContent().build(); // Devuelve 204 No Content
        }


        // Devolvemos la lista con 200 OK
        return ResponseEntity.ok(personas);
    }


    // Update
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Persona personaNueva, @PathVariable(value = "id") Integer personaId) {
        // Buscamos a la persona por el id que nos han pasado
        Optional<Persona> oPersona = personaService.findById(personaId);


        // No me existe la persona que me han pasado
        if (!oPersona.isPresent()) {
            // Salimos devolviendo un 404
            return ResponseEntity.notFound().build();
        }else{ // Sí existía y sí podemos modificarla
        // Modificamos a los datos de la persona a partir de los que nos han pasado en el body
            
            if(personaNueva.getNombre()==null && personaNueva.getEdad()==null && personaNueva.getCorreo()==null ){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El cuerpo no contiene ningún parámetro válido");
        
            }else{
                oPersona.get().setNombre(personaNueva.getNombre());
                oPersona.get().setEdad(personaNueva.getEdad());
                oPersona.get().setCorreo(personaNueva.getCorreo());
    
    
                // Devolvemos el status created y la persona
                return ResponseEntity.status(HttpStatus.CREATED).body(personaService.save(oPersona.get()));
            
            }
        }


    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Integer personaId) {
        // Buscamos a la persona por el id que nos han pasado
        Optional<Persona> oPersona = personaService.findById(personaId);


        //No existe la persona a borrar
        if (!oPersona.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        // Sí existe
        personaService.deleteById(personaId); // La borramos
        return ResponseEntity.noContent().build(); // Mandamos un OK


    }








}
