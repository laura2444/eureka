package com.co.castano.usuarios.service;

import com.co.castano.usuarios.entity.Alumno;
import java.util.Optional;

public interface AlumnoService {

    /**
     * @Iterable: interfaz que representa una colección de elementos que pueden ser iterados o recorridos , en este caso se usa para devolver toda la lista entera de alumnos
     * @Optional: clase que representa que un valor puede estar presente o ausente, en luugar de envolver el objeto que podria ser null se devuelve un optional.empty()
     * si no hay vcalor, o un optional.of(objeto) si si hay un valor, aayuda a evitar errores comunes al manejar valores nulos
     * */
    public Iterable<Alumno> findAll();
    public Optional<Alumno> findById(long id);
    public Alumno save(Alumno alumno); //toma objeto alumno y devuelve un alumno, guardar o actualizar un alumno en la base de datos
    public void deleteById (long id);

}
