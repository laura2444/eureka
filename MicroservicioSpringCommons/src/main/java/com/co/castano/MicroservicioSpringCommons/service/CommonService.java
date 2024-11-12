package com.co.castano.MicroservicioSpringCommons.service;

import java.util.Optional;

public interface CommonService<E> {

    /**
     * @Genericos : hay situaciones en las que se desea reutilizar una misma funcion, metodo pero cambiar el tipo de dato que se recibe, para eso java ofrece los genericos
     * @E: representa un elemento comun en colecciones, sin especificar que tipo de dato es, por ejemplo aqui podria ser alumno pero tambien podria ser de otro tipo
     *
     * @Iterable: interfaz que representa una colección de elementos que pueden ser iterados o recorridos , en este caso se usa para devolver toda la lista entera de un generico de java
     * @Optional: clase que representa que un valor puede estar presente o ausente, en luugar de envolver el objeto que podria ser null se devuelve un optional.empty()
     * si no hay vcalor, o un optional.of(objeto) si si hay un valor, aayuda a evitar errores comunes al manejar valores nulos
     * */
    public Iterable<E> findAll();
    public Optional<E> findById(Long id);
    public E save(E entity); //toma objeto entity y devuelve un tipo de dato E "puede ser cualquiera", guardar o actualizar ese tipo de dato en la base de datos
    public void deleteById (Long id);

}
