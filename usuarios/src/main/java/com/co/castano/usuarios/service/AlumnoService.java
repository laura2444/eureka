package com.co.castano.usuarios.service;


import com.co.castano.MicroservicioCommonsService.entity.Alumno;
import com.co.castano.MicroservicioSpringCommons.service.CommonService;



public interface AlumnoService extends CommonService<Alumno> {

    public Alumno save(Alumno alumno); //toma objeto alumno y devuelve un alumno, guardar o actualizar un alumno en la base de datos
    public void deleteById (Long id);

}
