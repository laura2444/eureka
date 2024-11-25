package com.co.castano.MicroservicioSpringCommons.controller;

import com.co.castano.MicroservicioSpringCommons.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


// GENERICOS -> E: entidad , S: Servicio que hereda de la clase generica service
public class CommonController <E,S extends CommonService<E>> {

    @Autowired
    protected S service;

    public CommonController(S service){
        this.service = service;
    }

    // Inyectar la variable de entorno 'BALANCEADOR_TEST' en el controlador
    @Value("${config.balanceador.test}")
    protected String balanceadorTest;

    /* BALANCEADOR TEST

    Un balanceador de carga es un componente que distribuye de forma equitativa el tráfico de red o las solicitudes entre múltiples instancias de un servicio o servidor


    Las múltiples instancias de un servicio son copias independientes del mismo servicio (o aplicación) que se ejecutan en paralelo en diferentes entornos
    (como servidores, máquinas virtuales, o contenedores). Cada instancia es capaz de atender solicitudes de manera independiente, pero todas funcionan juntas
    para aumentar la capacidad y disponibilidad del sistema en general.


    el balanceador test utiliza una variable inyectada (config.balanceador.test) en el controlador para mostrar qué instancia específica del servicio está respondiendo
    a cada solicitud. Esto permite validar que el balanceador de carga distribuye correctamente las solicitudes entre distintas instancias.

    Por ejemplo, si tienes dos instancias del servicio "usuarios" y ejecutas repetidamente el endpoint /balanceador-test, deberías ver que la respuesta alterna entre las dos
     instancias, indicando que el balanceador está funcionando
     *
    * */

    @GetMapping("/balanceador-test")
    public ResponseEntity<?> balanceadorTest(){
        Map<String, Object> response= new HashMap<String, Object>();
        response.put("balanceador",balanceadorTest); //variable que identifica de qué instancia específica de tu servicio "usuarios" proviene la respuesta, permitiéndote saber qué instancia está siendo utilizada en ese momento a través del balanceador de carga.
        response.put("alumno",service.findAll());//Agregamos la lista de alumnos

        return ResponseEntity.ok().body(response); // Retornamos la respuesta HTTP 200 con el body
    }


    @GetMapping("/listar")
    public ResponseEntity<?> listarAlumno(){
        return ResponseEntity.ok().body(service.findAll()); // ResponseEntity.ok para indicar un estado 200, y body para devolver el cuerpo de la solicitud que se llama al metodo fiindAll de la interfaz de AlumnoService
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id){
        Optional<E> ob = service.findById(id);

        if(ob.isEmpty()){  //si esta vacio
            return ResponseEntity.noContent().build(); //respuesta a solicitud exitosa pero no hay contenido para enviar en respuesta, no devuelve datos o no se encuentra, build es para construir la respuesta sin un cuerpo
        }
        return ResponseEntity.ok().body(ob.get()); //metodo get obtiene el objeto alumno
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody E entity){
        E alumnoDb = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
