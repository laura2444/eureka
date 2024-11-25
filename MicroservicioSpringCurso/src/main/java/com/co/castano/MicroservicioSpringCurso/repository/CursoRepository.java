package com.co.castano.MicroservicioSpringCurso.repository;


import com.co.castano.MicroservicioSpringCurso.entity.Curso;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends CrudRepository<Curso, Long> {
}
