--DROP TABLE persona IF EXISTS
--
--CREATE TABLE persona(
--    id BIGINT IDENTITY NOT NULL PRIMARY KEY,
--    nombre VARCHAR(20),
--    apellido VARCHAR(20),
--    telefono VARCHAR(20)
--);

DROP TABLE JO002CPUDIARIO IF EXISTS

CREATE TABLE JO002CPUDIARIO (
	JO002ID BIGINT IDENTITY NOT NULL PRIMARY KEY,
	JO002SERVIDOR VARCHAR(256),
	JO002OPERACION VARCHAR(256),
	JO002FECHA DATE,
	JO002HORA TIME,
	JO002TIPO VARCHAR(256),
	JO002TIEMPOMEDIO DECIMAL(10,8),
	JO002PETICIONES INT
);
