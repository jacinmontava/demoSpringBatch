package com.example.demofaf.processor;

import com.example.demofaf.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.transform.FieldSet;

public class PersonaItemProcessor implements ItemProcessor<FieldSet, Persona> {

    private static final Logger LOG = LoggerFactory.getLogger(PersonaItemProcessor.class);

//    @Override
//    public Persona process(Persona persona) throws Exception {
//        String nombre = persona.getNombre().toUpperCase();
//        String apellido = persona.getApellido().toUpperCase();
//
//        Persona personaNew = new Persona(nombre, apellido, persona.getTelefono());
//
//        LOG.info("Persona: " + persona);
//        LOG.info("Persona New: " + personaNew);
//
//        return personaNew;
//    }

    @Override
    public Persona process(FieldSet item) throws Exception {
        LOG.info("string: " + item);
        return null;
    }
}
