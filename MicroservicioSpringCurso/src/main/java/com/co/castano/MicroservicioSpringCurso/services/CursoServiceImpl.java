package com.co.castano.MicroservicioSpringCurso.services;

import com.co.castano.MicroservicioCommonsService.entity.Alumno;
import com.co.castano.MicroservicioSpringCommons.service.CommonService;
import com.co.castano.MicroservicioSpringCommons.service.CommonServiceImpl;
import com.co.castano.MicroservicioSpringCurso.entity.Curso;
import com.co.castano.MicroservicioSpringCurso.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService   {

    @Autowired
    private CursoRepository dao;

    @Override //sobreescribe metodo de la interfaz
    @Transactional(readOnly=true)
    public Iterable<Curso> findAll() {
        return dao.findAll();
    }


    @Override
    @Transactional(readOnly=true)
    public Optional<Curso> findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional
    public Curso save(Curso curso) {
        return dao.save(curso);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        dao.deleteById(id);
    }



}
