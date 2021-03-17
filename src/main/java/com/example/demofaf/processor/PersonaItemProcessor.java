package com.example.demofaf.processor;

import com.example.demofaf.model.LogItem;
import com.example.demofaf.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.transform.FieldSet;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonaItemProcessor implements ItemProcessor<Persona, Persona> {

    private static final Logger LOG = LoggerFactory.getLogger(PersonaItemProcessor.class);

    @Override
    public Persona process(Persona persona) throws Exception {
        String nombre = persona.getNombre().toUpperCase();
        String apellido = persona.getApellido().toUpperCase();

        Persona personaNew = new Persona(nombre, apellido, persona.getTelefono());

        LOG.info("Persona: " + persona);
        LOG.info("Persona New: " + personaNew);

        return personaNew;
    }
}
