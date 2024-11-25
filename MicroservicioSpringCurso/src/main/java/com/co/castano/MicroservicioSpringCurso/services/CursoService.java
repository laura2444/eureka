package com.co.castano.MicroservicioSpringCurso.services;

import com.co.castano.MicroservicioSpringCommons.service.CommonService;
import com.co.castano.MicroservicioSpringCurso.entity.Curso;

public interface CursoService extends CommonService<Curso> {
    public Curso save(Curso curso);
    public void deleteById (Long id);


}
