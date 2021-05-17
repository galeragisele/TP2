CREATE DATABASE unlam;
USE unlam;
CREATE TABLE alumnos(
    dni INTEGER PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    sexo CHAR(1),
    fechaNac DATE,
    fechaIngreso DATE,
    cantMatAprob INTEGER,
    promedio DECIMAL(5,2)
);