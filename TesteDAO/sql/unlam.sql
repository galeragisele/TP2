CREATE DATABASE unlam;
USE unlam;
CREATE TABLE alumnos(
dni integer,
nombre varchar(100),
apellido varchar(100),
sexo char(1),
fechaNac date,
fechaIngreso date,
cantMatAprob int,
promedio decimal(5,2)
);