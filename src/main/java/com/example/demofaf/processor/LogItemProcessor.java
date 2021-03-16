package com.example.demofaf.processor;

import com.example.demofaf.model.LogItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class LogItemProcessor implements ItemProcessor<LogItem, LogItem> {

    private static final Logger LOG = LoggerFactory.getLogger(LogItemProcessor.class);

    /*Override
    public Persona process(Persona persona) throws Exception {
        String nombre = persona.getNombre().toUpperCase();
        String apellido = persona.getApellido().toUpperCase();

        Persona personaNew = new Persona(nombre, apellido, persona.getTelefono());

        LOG.info("Persona: " + persona);
        LOG.info("Persona New: " + personaNew);

        return personaNew;
    }*/

    @Override
    public LogItem process(LogItem item) throws Exception {
        return null;
    }
}
