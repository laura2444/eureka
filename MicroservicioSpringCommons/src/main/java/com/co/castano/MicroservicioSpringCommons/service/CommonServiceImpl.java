package com.co.castano.MicroservicioSpringCommons.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class CommonServiceImpl<E, R extends CrudRepository<E,Long>> implements CommonService<E> {

    /**
     * Descripción: inyección de dependencias con Autowired para interfaz de tipo generica repository que contiene metodos crud de CrudRepository
     * */
    @Autowired
    private R dao;

    @Override //sobreescribe metodo de la interfaz
    @Transactional(readOnly=true)
    public Iterable<E> findAll() {
        return dao.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<E> findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional
    public E save(E entity) {
        return dao.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        dao.deleteById(id);
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
