package com.co.castano.usuarios.repository;

import com.co.castano.usuarios.entity.Alumno;
import org.springframework.data.repository.CrudRepository;


/**
 * Descripcion: interfaz que extiende de crudRepository, hereda las funcionalidades basicas de repositorio de datos, poporciona metodos prefefinidos para un CRUD
 *@Alumno,Long>: Indica que el repositorio manejará objetos de tipo Alumno y que el tipo de dato de la clave primaria (ID) es Long.
 * */
public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
}
