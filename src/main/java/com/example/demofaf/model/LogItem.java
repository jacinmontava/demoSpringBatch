package com.example.demofaf.model;

import java.sql.Time;
import java.util.Date;
import java.util.zip.DataFormatException;

public class LogItem {

    /*
    CREATE TABLE `JO002CPUDIARIO` (
	`JO002ID` INT NOT NULL AUTO_INCREMENT,
	`JO002SERVIDOR` VARCHAR(256),
	`JO002OPERACION` VARCHAR(256),
	`JO002FECHA` DATE,
	`JO002HORA` TIME,
	`JO002TIPO` VARCHAR(256),
	`JO002TIEMPOMEDIO` DECIMAL(10,8),
	`JO002PETICIONES` INT,
	PRIMARY KEY (`JO002ID`)
);
     */

    private String JO002SERVIDOR;
    private String JO002OPERACION;
    private Date JO002FECHA;
    private String JO002HORA;
    private String JO002TIPO;
    private Double JO002TIEMPOMEDIO;
    private int JO002PETICIONES;

    public LogItem() {
        super();
    }

    public LogItem(String JO002SERVIDOR, String JO002OPERACION, Date JO002FECHA, String JO002HORA, String JO002TIPO, Double JO002TIEMPOMEDIO, int JO002PETICIONES) {
        super();
        this.JO002SERVIDOR = JO002SERVIDOR;
        this.JO002OPERACION = JO002OPERACION;
        this.JO002FECHA = JO002FECHA;
        this.JO002HORA = JO002HORA;
        this.JO002TIPO = JO002TIPO;
        this.JO002TIEMPOMEDIO = JO002TIEMPOMEDIO;
        this.JO002PETICIONES = JO002PETICIONES;
    }

    public String getJO002SERVIDOR() {
        return JO002SERVIDOR;
    }

    public void setJO002SERVIDOR(String JO002SERVIDOR) {
        this.JO002SERVIDOR = JO002SERVIDOR;
    }

    public String getJO002OPERACION() {
        return JO002OPERACION;
    }

    public void setJO002OPERACION(String JO002OPERACION) {
        this.JO002OPERACION = JO002OPERACION;
    }

    public Date getJO002FECHA() {
        return JO002FECHA;
    }

    public void setJO002FECHA(Date JO002FECHA) {
        this.JO002FECHA = JO002FECHA;
    }

    public String getJO002HORA() {
        return JO002HORA;
    }

    public void setJO002HORA(String JO002HORA) {
        this.JO002HORA = JO002HORA;
    }

    public String getJO002TIPO() {
        return JO002TIPO;
    }

    public void setJO002TIPO(String JO002TIPO) {
        this.JO002TIPO = JO002TIPO;
    }

    public Double getJO002TIEMPOMEDIO() {
        return JO002TIEMPOMEDIO;
    }

    public void setJO002TIEMPOMEDIO(Double JO002TIEMPOMEDIO) {
        this.JO002TIEMPOMEDIO = JO002TIEMPOMEDIO;
    }

    public int getJO002PETICIONES() {
        return JO002PETICIONES;
    }

    public void setJO002PETICIONES(int JO002PETICIONES) {
        this.JO002PETICIONES = JO002PETICIONES;
    }

    @Override
    public String toString() {
        return "JO002SERVIDOR: " + JO002SERVIDOR +
                " - JO002OPERACION: " + JO002OPERACION +
                " - JO002FECHA: " + JO002FECHA +
                " - JO002HORA: " + JO002HORA +
                " - JO002TIPO: " + JO002TIPO +
                " - JO002TIEMPOMEDIO: " + JO002TIEMPOMEDIO +
                " - JO002PETICIONES: " + JO002PETICIONES;
    }
}
