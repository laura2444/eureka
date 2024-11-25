package com.co.castano.usuarios.service;


import com.co.castano.MicroservicioCommonsService.entity.Alumno;
import com.co.castano.usuarios.repository.AlumnoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service //logica del negocio
public class AlumnoServiceImpl implements AlumnoService {


    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override //sobreescribe metodo de la interfaz
    @Transactional(readOnly=true)
    public Iterable<Alumno> findAll() {
        return alumnoRepository.findAll();
    }


    @Override
    @Transactional(readOnly=true)
    public Optional<Alumno> findById(Long id) {
        return alumnoRepository.findById(id);
    }

    @Override
    @Transactional
    public Alumno save(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        alumnoRepository.deleteById(id);
    }




    /*
     * @Transactional: Decorador que gestiona las transacciones, cuando hablamos de transaccciones nos referimos a que cada operación ( varias operaciones) deben completarse con exito para que la
     * transacicón se confirme (commit) y si alguna operación falla todas las demas deben deshacerse(rollback), cuando se aplica el @Transactional spring gestiona estas transacciones para el metodo de la clase
     *
     * algunas propiedades son:
     * -readOnly: indica si la transacción es de solo lectura, si es true entonces spring optimiza la transacicón para operaciones de solo lectura, optimiza el rendimiento para evitar otras operaciones
     * de escritura en la bd
     *
     * */
}