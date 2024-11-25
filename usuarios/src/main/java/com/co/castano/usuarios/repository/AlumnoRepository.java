package com.co.castano.usuarios.repository;

import com.co.castano.MicroservicioCommonsService.entity.Alumno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
}
